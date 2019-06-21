package com.bitter.esb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  wanglf1207
 */
public class SYSHead {

    private ServiceScene serviceScene;

    private  ServiceCode serviceCode;

    public void setServiceScene(ServiceScene serviceScene) {
        this.serviceScene = serviceScene;
    }

    @XmlElement(name = "SERVICE_SCENE")
    public ServiceScene getServiceScene() {
        return serviceScene;
    }

    @XmlElement(name = "SERVICE_CODE")
    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }

    public static class ServiceCode {

        private String operation;
        private String value;

        @XmlAttribute(name = "attr")
        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
        @XmlValue
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ServiceScene {

        private String attr;
        private String value;

        @XmlAttribute(name = "attr")
        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        @XmlValue
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

