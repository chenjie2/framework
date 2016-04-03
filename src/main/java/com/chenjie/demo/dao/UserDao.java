package com.chenjie.demo.dao;

import org.springframework.stereotype.Repository;

import com.chenjie.demo.dao.IUserDao;
import com.chenjie.demo.entity.User;

@Repository("usersDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

    public UserDao() {
        super();
        
        setClazz(User.class);
    }
}