package com.weltond.CompoundPattern.combining.step5composite;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public abstract class AbstractDuckFactory {
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedheadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
