package com.chenjie.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenjie.demo.dao.IUserDao;
import com.chenjie.demo.entity.User;


@Service("testService")
@Transactional
public class TestService {
	
    @Resource(name="usersDao")
    private IUserDao dao;
    
    public TestService() {
    }
    
    public String test(){
    	return "test";
    }
    
    public List<User> listUser(){
    	List<User> users = dao.findAll();
    	return users;
    }
    
}