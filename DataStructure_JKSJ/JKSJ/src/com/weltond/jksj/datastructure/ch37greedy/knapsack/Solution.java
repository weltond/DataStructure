package com.weltond.jksj.datastructure.ch37greedy.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Max weight = 100kg. Optimize the total value.
 *
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Solution {
    private PriorityQueue<Goods> GoodsList =
            new PriorityQueue<>(
                    (o1, o2) -> {
                        if (o1.getUnitPrice() < o2.getUnitPrice()) {
                            return -1;
                        } else if (o1.getUnitPrice() > o2.getUnitPrice()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
            );

    public void addGoods(Goods gs) {
        this.GoodsList.add(gs);
    }

    public List<Goods> getMaxValueGoods(int weight) {
        List<Goods> list = new ArrayList<>();

        //initialize
        int surplus = weight;

        while (!GoodsList.isEmpty()) {
            Goods goodItem = GoodsList.poll();

            if (surplus >= goodItem.getWeight()) {
                surplus -= goodItem.getWeight();
                list.add(goodItem);
            } else {
                int surplusVals = goodItem.getWeight() - surplus;
                goodItem.setWeight(surplusVals);
                GoodsList.offer(goodItem);
                Goods result =
                        new Goods(goodItem.getName(), surplus, goodItem.getValue(), goodItem.getUnitPrice());
                list.add(result);
                break;
            }
        }

        return list;
    }

}
