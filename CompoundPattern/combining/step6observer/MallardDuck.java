package com.weltond.CompoundPattern.combining.step6observer;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class MallardDuck implements Quackable {
    Observable observable;

    public MallardDuck() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
