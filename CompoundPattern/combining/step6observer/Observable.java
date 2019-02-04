package com.weltond.CompoundPattern.combining.step6observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class Observable implements QuackObservable {
    ArrayList<com.weltond.CompoundPattern.combining.step6observer.Observer> observers = new ArrayList<>();
    QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(com.weltond.CompoundPattern.combining.step6observer.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iterator = observers.listIterator();
        while (iterator.hasNext()) {
            Observer observer = (Observer) iterator.next();
            observer.update(duck);
        }
    }
}
