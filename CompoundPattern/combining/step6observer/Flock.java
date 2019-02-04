package com.weltond.CompoundPattern.combining.step6observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class Flock implements Quackable {
    ArrayList<Quackable> quackers = new ArrayList<>();

    public void add(Quackable quacker) {
        quackers.add(quacker);
    }

    @Override
    public void quack() {
        Iterator iterator = quackers.listIterator();
        while (iterator.hasNext()) {
            Quackable quacker = (Quackable)iterator.next();
            quacker.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
