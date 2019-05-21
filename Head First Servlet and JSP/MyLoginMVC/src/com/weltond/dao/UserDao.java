package com.weltond.dao;

import com.weltond.domain.User;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/21/2019
 */
public interface UserDao {
    /**
     * Add user info
     * @param user
     * @throws Exception
     */
    public void addUser(User user) throws Exception;

}
