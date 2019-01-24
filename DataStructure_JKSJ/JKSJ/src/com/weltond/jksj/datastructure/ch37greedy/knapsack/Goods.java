package com.weltond.jksj.datastructure.ch37greedy.knapsack;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Goods {
    private String name;
    private int weight;
    private float value;    // total value
    private float unitPrice;

    public Goods(String name, int weight, float value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.unitPrice = value / weight;
    }

    public Goods(String name, int weight, float value, float unitPrice) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Goods{");
        sb.append("name='").append(name).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", value=").append(value);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append("}");
        return sb.toString();
    }
}
