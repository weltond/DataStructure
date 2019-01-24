package com.weltond.jksj.datastructure.ch37greedy.candyproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Solution {
    /*Sort by candy type(size) to satisfy each child*/
    private PriorityQueue<Sweet> sweetsQueue =
            new PriorityQueue<>(
                    (o1, o2) -> {
                        if (o1.getType() > o2.getType()) {
                            return 1;
                        } else if (o1.getType() < o2.getType()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
            );

    /*Sort by each child satisfication*/
    private PriorityQueue<Child> childQueue =
            new PriorityQueue<>(
              (o1, o2) -> {
                  if (o1.getSize() > o2.getSize()) {
                      return 1;
                  } else if (o1.getSize() < o2.getSize()) {
                      return -1;
                  } else {
                      return 0;
                  }
              }
            );

    public void addSweet(String name, int num, int type) {
        this.sweetsQueue.offer(new Sweet(name, num, type));
    }

    public void addChild(String name, int type) {
        this.childQueue.offer(new Child(name, type));
    }

    public List<Child> alloc() {
        List<Child> childList = new ArrayList<>();

        // In order to optimize the result to satisfy as many child as possible
        // We sort the sweet size and child satisfication size from small to large
        while (!sweetsQueue.isEmpty()) {
            Sweet sweetItem = sweetsQueue.poll();

            if (sweetItem.getNum() > 0) {
                while (!childQueue.isEmpty()) {
                    Child childItem = childQueue.poll();

                    if (sweetItem.getType() == childItem.getSize()) {
                        childItem.setSweet(sweetItem);
                        childList.add(childItem);
                        break;
                    }
                }

                sweetItem.setNum(sweetItem.getNum() - 1);
                sweetsQueue.offer(sweetItem);
            }
        }
        return childList;
    }
}
