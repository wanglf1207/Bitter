package com.bitter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglf
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = {"/hello"},method = RequestMethod.GET)
    public String say(){
        return "hello world!";
    }
}