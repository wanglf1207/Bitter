package com.bitter.utils;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author  wanglf1207
 */
public class JAXBUtils {

    @SuppressWarnings("unused")
    private static final JAXBContext getJAXBContext(Class<?> c) {
        JAXBContext jaxbContext=null;
        try {
            jaxbContext = JAXBContext.newInstance(c);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return jaxbContext;
    }

    public static final Object xml2Object(Object obj,String xmlStr){
        if(xmlStr == null){
            return null;
        }
        JAXBContext jaxbContext = getJAXBContext(obj.getClass());
        try {
            //得到反序列化实例Unmarshaller
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            obj= unmarshaller.unmarshal(new StringReader(xmlStr));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 将实体类转序列化为对应String类型xml节点
     * @param obj
     * @return
     */
    public static final String object2Xml(Object obj) {

        StringWriter writer= new StringWriter();
        JAXBContext jaxbContext = getJAXBContext(obj.getClass());
        try {

            Marshaller marshaller = jaxbContext.createMarshaller();
            //设置序列化的编码格式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息

            //设置格式化输出
            marshaller.marshal(obj, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();

    }

    /**
     * 将实体类转序列化为对应node节点
     * @param obj   实体类
     * @param node  创建的新节点
     * @return
     */
    public static final Node modelToNode(Object obj,Node node){
        JAXBContext jaxbContext = getJAXBContext(obj.getClass());
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, node);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return node;

    }

}
