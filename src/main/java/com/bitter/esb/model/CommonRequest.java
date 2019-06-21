package com.bitter.esb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础框架用，开发人员勿用
 */
@XmlRootElement(name="service")
public class CommonRequest extends ESBRequest {

    List<SYSHead> sysHeadList = new ArrayList<>();
    @XmlElement(name = "SYS_HEAD")
    public List<SYSHead> getSYSHeadList() {
        return sysHeadList;
    }
    public void setSYSHeadList(List<SYSHead> sysHeadList) {
        this.sysHeadList = sysHeadList;
    }

}
