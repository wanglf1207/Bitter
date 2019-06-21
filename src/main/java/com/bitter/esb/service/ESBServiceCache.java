package com.bitter.esb.service;

import java.lang.reflect.Method;

public class ESBServiceCache {

    private Class<?> reqClass;
    private Class<?> respClass;
    private ESBService<?,?> bean;
    private Method performMethod;

    public ESBServiceCache(Class<?> reqClass,Class<?> respClass,ESBService<?,?> bean,Method performMethod){
        this.reqClass = reqClass;
        this.respClass = respClass;
        this.bean = bean;
        this.performMethod = performMethod;
    }

    public Class<?> getReqClass() {
        return reqClass;
    }
    public Class<?> getRespClass() {
        return respClass;
    }
    public ESBService<?, ?> getBean() {
        return bean;
    }
    public Method getPerformMethod() {
        return performMethod;
    }

}