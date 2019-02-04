package com.weltond.CompoundPattern.combining.step3decorator;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class MallardDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
