package com.weltond.jksj.datastructure.ch37greedy.candyproblem;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Child {
    private String name;
    // candy size
    private int size;
    private Sweet sweet;

    public Child(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sweet getSweet() {
        return sweet;
    }

    public void setSweet(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Child{");
        sb.append("name='").append(name).append('\'');
        sb.append(", size=").append(size);
        sb.append(", sweet=").append(sweet);
        sb.append('}');
        return sb.toString();
    }
}
