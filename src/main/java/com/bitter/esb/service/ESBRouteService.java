package com.bitter.esb.service;

import com.bitter.esb.model.CommonRequest;
import com.bitter.esb.model.ESBRequest;
import com.bitter.esb.model.ESBResponse;
import com.bitter.utils.JAXBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ESBRouteService implements BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(ESBRouteService.class);

    private BeanFactory factory;

    private Map<String,ESBServiceCache> serviceCache = Collections.synchronizedMap(new HashMap<String,ESBServiceCache>());


    public String exec(String message) {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest = (CommonRequest) JAXBUtils.xml2Object(commonRequest,message);

        String serviceCode = commonRequest.getSYSHeadList().get(0).getServiceCode().getValue();
        String serviceScene = commonRequest.getSYSHeadList().get(0).getServiceScene().getValue();

        String beanId = serviceCode + "_" + serviceScene;

        ESBServiceCache serviceCached = getESBService(beanId);

        if(serviceCached == null){
            logger.error("交易码:{}，场景ID：{}暂无正确的服务实现，请联系系统开发人员,消费流水号：{}", serviceCode,serviceScene,"2342134234234");
            return "";
        }
        ESBRequest req = null;
        try {
            req = (ESBRequest)JAXBUtils.xml2Object(serviceCached.getReqClass().newInstance(), message);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ESBResponse response = null;
        try {
            response = (ESBResponse)serviceCached.getPerformMethod().invoke(serviceCached.getBean(), req);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


       /* ESBResponse result = null;
        try{
            ESBUtils.getLogger(serviceCode,serviceScene).debug("收到ESB请求:{}",new String(PackUtil.pack(request),"UTF8"));
            ESBRequest req = (ESBRequest)esbUtils.compositDataToBean(data, serviceCached.getReqClass());

            //处理local_head
            if(rsp_local_head != null){

                Class<?> localHeadType = esbUtils.getLoclHeadType(serviceCached.getReqClass());

                if(localHeadType != null){
                    req.setLocalHead(esbUtils.compositDataToBean(rsp_local_head, localHeadType));
                }else{
                    logger.warn("请求报文中有LOCAL_HEAD,但{}中未定义",serviceCached.getReqClass().getName());
                }
            }

            //处理app_head
            if(rsp_app_head != null){

                Class<?> appHeadType = esbUtils.getAppHeadType(serviceCached.getReqClass());

                if(appHeadType != null){
                    req.setAppHead(esbUtils.compositDataToBean(rsp_app_head, appHeadType));
                }else{
                    logger.warn("请求报文中有APP_HEAD,但{}中未定义",serviceCached.getReqClass().getName());
                }
            }


            req.setConsumerSeqNo(consumerSeqNo);
            req.setBusinessSeqNo(businessSeqNo);
            req.setTranDate(tranDate);
            result = (ESBResponse)serviceCached.getPerformMethod().invoke(serviceCached.getBean(), req);

        }catch(Exception e){
            logger.error("调用服务发生异常,交易码：{},场景码：{},请联系系统开发人员,消费流水号：{},错误信息:{}",serviceCode,serviceScene,consumerSeqNo,e);
           // appendErrorMessage(sys_head_resp, ret, ESBReturnCode.RET_CODE_INVOKE_FAILED, ESBReturnCode.RET_MSG_INVOKE_FAILED);
        }

        if(result != null){
            if(result.resultSize() > 0){
                for(int index = 0 ; index < result.resultSize() ; index++){
                    appendErrorMessage(sys_head_resp,ret,result.getErrorCode(index),result.getErrorMessage(index));
                }
            }else{
                appendSuccessMessage(sys_head_resp,ret);
            }
        }

        CompositeData body = (result != null) ? esbUtils.beanToCompositeData(result):new CompositeData();

        sys_head_resp.addArray("RET", ret);
        resp.addStruct("SYS_HEAD", sys_head_resp);
        resp.addStruct("BODY", body);

        if(result != null && result.getLocalHead() != null){
            CompositeData local_head = esbUtils.beanToCompositeData(result.getLocalHead());
            resp.addStruct("LOCAL_HEAD", local_head);
        }

        if(result != null && result.getAppHead() != null){
            CompositeData app_head = esbUtils.beanToCompositeData(result.getAppHead());
            resp.addStruct("APP_HEAD", app_head);

        }

        try{
            ESBUtils.getLogger(serviceCode,serviceScene).debug("返回ESB报文:{}",new String(PackUtil.pack(resp),"UTF8"));
        }catch(Exception e){
            logger.error("不支持的编码:",e);
        }*/
        return JAXBUtils.object2Xml(response);
    }


    @SuppressWarnings("unchecked")
    private ESBServiceCache getESBService(String beanId) {

        ESBServiceCache serviceCached = serviceCache.get(beanId);

        if(serviceCached != null)
            return serviceCached;

        ESBService<? extends ESBRequest,? extends ESBResponse> service = null;

        try{
            service = (ESBService<? extends ESBRequest,? extends ESBResponse>)factory.getBean(beanId);
        }catch(Exception e){
            logger.error("获取Bean失败，id:{},错误信息:{}",beanId,e);
            return null;
        }
        if(service == null)
            return null;

        @SuppressWarnings("rawtypes")
        Class<? extends ESBService> serviceClass = service.getClass();


        Class<?> reqClass = null;
        Class<?> respClass = null;


        for(Type type : serviceClass.getGenericInterfaces()){

            if(type.getTypeName().startsWith("com.bitter.esb.service.ESBService") && type instanceof ParameterizedType){

                Type[] params = ((ParameterizedType)type).getActualTypeArguments();
                if(params.length < 2){
                    logger.error("未正确实现ESB的接口,实现类要实现ESBService接口，两个泛型参数都要明确,beanId:{}",beanId);
                    return null;
                }else{
                    reqClass = (Class<?>)params[0];
                    respClass = (Class<?>)params[1];
                }
                break;
            }

        }

        if(reqClass == null || respClass == null){
            logger.error("未正确实现ESB的接口,实现类要实现ESBService接口，两个泛型参数都要明确,beanId:{}",beanId);
            return null;
        }

        Method performMethod = null;
        try{
            performMethod = serviceClass.getMethod("perform", reqClass);
        }catch(Exception e){
            logger.error("ESB的实现类一定要实现ESBService接口中的perform方法",e);
            return null;
        }

        if(performMethod == null){
            logger.error("ESB的实现类一定要实现ESBService接口中的perform方法,beanId:{}",beanId);
            return null;
        }

        serviceCached = new ESBServiceCache(reqClass,respClass,service,performMethod);
        serviceCache.put(beanId, serviceCached);

        return serviceCached;

    }

    public void setBeanFactory(BeanFactory factory){
        this.factory = factory;
    }
}
