package com.weltond.Proxy.DynamicProxy;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public interface PersonBean {
    String getName();
    String getGender();
    String getInterests();
    int getHotOrNotRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setHotOrNotRating(int rating);
}
