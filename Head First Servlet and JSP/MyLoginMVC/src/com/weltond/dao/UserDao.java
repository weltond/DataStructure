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

    /**
     * Find User from DB based on username and password
     * @param user
     * @return
     * @throws Exception
     */
    public User findUser(User user) throws Exception;

}
