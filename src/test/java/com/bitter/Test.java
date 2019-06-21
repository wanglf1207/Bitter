package com.bitter;

public class Test {
    public static  void main(String [] args) {
        String str = System.getProperty("host", "127.0.0.1");
        System.out.println(str);
    }
}
