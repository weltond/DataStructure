package com.weltond.CompoundPattern.combining.step6observer;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class RedheadDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
