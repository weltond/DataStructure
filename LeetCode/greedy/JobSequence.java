package com.weltond.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class JobSequence implements Comparator<JobSequence> {
    char id;
    int dead, profit;

    @Override
    public int compare(JobSequence o1, JobSequence o2) {
        return o1.profit > o2.profit ? -1 : 1;
    }

    public JobSequence(char id, int dead, int profit) {
        this.id = id;
        this.dead = dead;
        this.profit = profit;
    }

    public JobSequence() {}

    static void print(ArrayList<JobSequence> arr, int n) {
        Collections.sort(arr, new JobSequence());

        int res[] = new int[n];
        List<JobSequence> r = new ArrayList<>();
        boolean slot[] = new boolean[n];

        // iterate all given jobs
        for (int i = 0; i < n; i++) {
            // find a free slot for this job (start from the last possible slot)
            for (int j = Math.min(n, arr.get(i).dead) - 1; j >= 0; j--) {
                // free slot found
                if (!slot[j]) {
                    res[j] = i; // add this job to result
                    r.add(arr.get(i)); // add this job to another result
                    slot[j] = true; // make this slot occupied
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (slot[i]) {
                System.out.println(arr.get(res[i]).id + " ");
            }
        }
        System.out.println("=====");
        for (JobSequence s : r) {
            System.out.println(s.id + " ");
        }
    }

    public static void testJobSequence() {
        ArrayList<JobSequence> arr=new ArrayList<JobSequence>();
        arr.add(new JobSequence('a',2,100));
        arr.add(new JobSequence('b',1,19));
        arr.add(new JobSequence('c',2,27));
        arr.add(new JobSequence('d',1,25));
        arr.add(new JobSequence('e',3,15));
        System.out.println("Following jobs need to be "+
                "executed for maximum profit");
        JobSequence.print(arr, arr.size());
    }

    public static void main(String[] args) {
        testJobSequence();
    }
}
