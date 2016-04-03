package com.chenjie.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chenjie.demo.dao.IOperations;
import com.chenjie.demo.dao.IUserDao;
import com.chenjie.demo.entity.User;
import com.chenjie.demo.service.IUserService;

@Service("userService")
public class UserService extends AbstractService<User> implements IUserService {

    @Resource(name="usersDao")
    private IUserDao dao;
    
    public UserService() {
        super();
    }

    @Override
    protected IOperations<User> getDao() {
        return this.dao;
    }
}