package com.bitter.esb.model.demo;

import com.bitter.esb.model.ESBRequest;
import com.bitter.esb.model.ESBResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="message")
public class UserResponse extends ESBResponse {

    private String name;
    private int age;
    private String address;

    @XmlElement
    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    private int amt;

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
