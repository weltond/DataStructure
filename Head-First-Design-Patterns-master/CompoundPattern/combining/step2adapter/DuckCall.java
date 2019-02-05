package com.weltond.CompoundPattern.combining.step2adapter;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class DuckCall implements Quackable {
    @Override
    public void quack() {
        System.out.println("Kwak");
    }
}
