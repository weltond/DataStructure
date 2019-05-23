package com.weltond.service.impl;

import com.weltond.dao.UserDao;
import com.weltond.exceptions.UserExistException;
import com.weltond.exceptions.UsersException;
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

    @Override
    public User login(User user) throws UsersException{
        User u = null;
        try {
            u = userDao.findUser(user);
//            if (u == null) {
//                throw new UsersException("Username or Password is wrong!");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            // write logs

        }
        return u;
    }

    @Override
    public boolean findUserByName(String name) throws UserExistException {
        boolean isExists = userDao.findUserByName(name);
        if (isExists) {
            throw new UserExistException("User already EXISTS!");
        }
        return isExists;
    }
}
