package com.weltond.service.impl;

import com.weltond.dao.UserDao;
import com.weltond.service.UserService;
import com.weltond.dao.impl.UserDaoImpl;
import com.weltond.domain.User;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/21/2019
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) throws Exception {
        userDao.addUser(user);
    }
}
