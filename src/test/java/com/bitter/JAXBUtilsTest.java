package com.bitter;



import com.bitter.esb.model.SYSHead;
import com.bitter.esb.model.demo.*;
import com.bitter.utils.JAXBUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JAXBUtilsTest {

    @Test
    public void testModelToStringXML() {

        UserRequest userRequest = new UserRequest();


        List<SYSHead> sysHeadList = new ArrayList<>();


        SYSHead sysHead = new SYSHead();

        SYSHead.ServiceScene serviceScene = new SYSHead.ServiceScene();
        serviceScene.setAttr("a");
        serviceScene.setValue("11");

        SYSHead.ServiceCode serviceCode = new SYSHead.ServiceCode();
        serviceCode.setOperation("a");
        serviceCode.setValue("02002000019");

        sysHead.setServiceScene(serviceScene);
        sysHead.setServiceCode(serviceCode);
        sysHeadList.add(sysHead);

        List<UserRequest.Body> bodyList = new ArrayList<>();
        UserRequest.Body body = new UserRequest.Body();
        body.setAddress("1");
        body.setName("1");
        body.setAge(1);

        bodyList.add(body);

        userRequest.setSYSHeadList(sysHeadList);
        userRequest.setBodyList(bodyList);


        String xml = JAXBUtils.object2Xml(userRequest);
        System.out.println(xml);
    }

    /*@Test
    public void testXmlToModel() {
        String xmlStr = "<root> <name>王利峰</name><age>18</age> <address>上海浦东</address>  </root>";

        UserRequest userRequest = new UserRequest();
        userRequest = (UserRequest) JAXBUtils.xml2Object(userRequest,xmlStr);
        System.out.println(userRequest.getAddress());
    }*/
}