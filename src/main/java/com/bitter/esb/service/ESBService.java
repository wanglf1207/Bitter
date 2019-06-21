package com.bitter.esb.service;

/**
 * @author wanglf1207
 * @param <T>
 * @param <V>
 */
public interface ESBService<T,V> {

    public V perform(T req);

}
