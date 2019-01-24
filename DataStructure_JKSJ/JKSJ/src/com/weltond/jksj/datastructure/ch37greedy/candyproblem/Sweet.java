package com.weltond.jksj.datastructure.ch37greedy.candyproblem;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Sweet {
    private String name;
    private int num;    // total number of candy
    private int type;   // candy size, type

    public Sweet(String name, int num, int type) {
        this.name = name;
        this.num = num;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sweet{");
        sb.append("name='").append(name).append('\'');
        sb.append(", num=").append(num);
        sb.append(", type=").append(type);
        sb.append("}");
        return sb.toString();
    }
}
