package com.weltond.CompoundPattern.combining.step4factory;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class QuackCounter implements Quackable {
    Quackable duck;
    static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }
    public static int getQuacks() {
        return numberOfQuacks;
    }
}
