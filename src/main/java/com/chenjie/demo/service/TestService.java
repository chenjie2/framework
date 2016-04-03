package com.chenjie.demo.service;

import org.springframework.stereotype.Service;


@Service("testService")
public class TestService {
    
    public TestService() {
    }
    
    public String test(){
    	return "test";
    }
}