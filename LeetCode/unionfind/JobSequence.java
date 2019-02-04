package com.weltond.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** https://www.geeksforgeeks.org/job-sequencing-using-disjoint-set-union/
 * see also com.weltond.greedy for another solution
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class JobSequence implements Comparator<JobSequence> {
    // each job has a unique id, profit and deadline
    char id;
    int deadline, profit;

    public JobSequence() {
    }

    public JobSequence(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    // get the max deadline
    public static int findMaxDeadline(ArrayList<JobSequence> arr) {
        int ans = Integer.MIN_VALUE;
        for (JobSequence tmp : arr) {
            ans = Math.max(tmp.deadline, ans);
        }
        return ans;
    }

    // get optimal job sequence
    public static void printJobSchedule(ArrayList<JobSequence> arr) {
        // sort jobs in descending order on the basis of their profit
        Collections.sort(arr, new JobSequence());

        // find the max deadline among all jobs
        // and create a disjoint set data structure with maxDeadline disjoint sets initially
        int maxDeadline = findMaxDeadline(arr);
        DisjointSet dsu = new DisjointSet(maxDeadline);

        // traverse through all the jobs
        for (JobSequence tmp : arr) {
            // find the max available free slot for this job (corresponding to its deadline)
            int avaliableSlot = dsu.find(tmp.deadline);

            // if maximum available free slot is greater then 0, then free slot available
            if (avaliableSlot > 0) {
                // this slot is taken by the job 'i'
                // so we need to update the greatest free slot.
                // note that, in union, we make first parameter as parent of second.
                // so future queries for available slot will return maximum slot from set of 'availableSlot - 1'
                dsu.union(dsu.find(avaliableSlot - 1), avaliableSlot);
                System.out.println(tmp.id + " ");
            }
        }
        System.out.println();
    }

    // Use to sort in descending order on the basis of profit for each job
    @Override
    public int compare(JobSequence o1, JobSequence o2) {
        return o1.profit > o2.profit ? -1 : 1;
    }
}

class DisjointSet {
    int parent[];

    public DisjointSet(int n) {
        this.parent = new int[n + 1];

        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }
    }

    // Path compression
    int find(int s) {
        // make the parent of the nodes in the path from u-->parent[u] point to parent[u]
        if (s == parent[s]) {
            return s;
        }
        return parent[s] = find(parent[s]);
    }

    // make u as parent of v
    void union(int u, int v) {
        parent[v] = u;
    }
}