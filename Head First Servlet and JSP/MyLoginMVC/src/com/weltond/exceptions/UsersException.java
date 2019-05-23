package com.weltond.exceptions;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/22/2019
 */
public class UsersException extends RuntimeException{
    private String msg;
    public UsersException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
