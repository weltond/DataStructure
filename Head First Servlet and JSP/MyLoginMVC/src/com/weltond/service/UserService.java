package com.weltond.service;

import com.weltond.domain.User;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/21/2019
 */
public interface UserService {
    /**
     * Register
     * @param user
     * @throws Exception
     */
    public void register(User user) throws Exception;

    /**
     * Log in
     * @param user
     * @return
     */
    public User login(User user);
}
