package com.weltond.jksj.datastructure.ch37greedy.Intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class Solution {
    private PriorityQueue<ScopeBusi> scopeList =
            new PriorityQueue<>((o1, o2) -> compare(o1.getEnd(), o2.getEnd()));

    private int compare(int val1, int val2) {

        return Integer.compare(val1, val2);
        //return val1 > val2 ? 1 : val1 < val2 ? -1 : 0;

        /*
        if (val1 > val2) return 1;
        else if (val1 < val2) return -1;
        else return 0;
        */
    }

    public void add(int start, int end) {
        scopeList.offer(new ScopeBusi(start, end));
    }

    /**
     *  1) Build a min heap
     *  2) Find rest from min heap where start time > min heap top's end time
     * @return
     */
    public List<ScopeBusi> coutScope(){
        ScopeBusi scoupStartTmp = scopeList.poll(); // this one has the smallest end time

        List<ScopeBusi> result = new ArrayList<>();
        result.add(scoupStartTmp);

        while (!scopeList.isEmpty()) {
            ScopeBusi endBusi = scopeList.peek();

            if (endBusi.getStart() >= scoupStartTmp.getEnd()) {
                result.add(endBusi);
                scoupStartTmp = endBusi;
            }
            scopeList.poll();
        }
        return result;
    }


}
