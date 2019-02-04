package com.weltond.CompoundPattern.combining.step4factory;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class GooseAdapter implements Quackable {
    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }
}
