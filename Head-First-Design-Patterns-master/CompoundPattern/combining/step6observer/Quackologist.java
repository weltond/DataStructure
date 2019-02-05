package com.weltond.CompoundPattern.combining.step6observer;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class Quackologist implements Observer {
    @Override
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");

    }
}
