package com.bitter.esb.model.demo;

import com.bitter.esb.model.ESBRequest;
import com.bitter.esb.model.SYSHead;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="service")
public class UserRequest extends ESBRequest {

    List<SYSHead> sysHeadList = new ArrayList<>();
    @XmlElement(name = "SYS_HEAD")
    public List<SYSHead> getSYSHeadList() {
        return sysHeadList;
    }
    public void setSYSHeadList(List<SYSHead> sysHeadList) {
        this.sysHeadList = sysHeadList;
    }

    // 报文体
    List<Body> bodyList = new ArrayList<>();
    @XmlElement(name = "BODY")
    public List<Body> getBodyList() {
        return bodyList;
    }
    public void setBodyList(List<Body> bodyList) {
        this.bodyList = bodyList;
    }

    // 拼接body报文
    public static class Body {
        private String name;
        private int age;
        private String address;

        @XmlElement
        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }

        @XmlElement
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @XmlElement
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}


