package com.weltond.unionfind;

import java.util.ArrayList;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class Main {
    public static void main(String[] args) {
        testDisjointUnionSets();
        testDetectCycleInUnGraph();
        testJobSequence();
    }

    public static void testDisjointUnionSets() {
        // 5 person with ids as 0 1 2 3 4
        int n = 5;
        DisjointUnionSets dus = new DisjointUnionSets(n);

        // 0 is a friend of 2
        dus.union(0, 2);

        // 4 is a friend of 2
        dus.union(4, 2);

        // 3 is a friend of 1
        dus.union(3, 1);

        // check if 4 is a friend of 0
        if (dus.find(4) == dus.find(0)) {
            System.out.println("4 is a friend of 0 -> YES");
        } else {
            System.out.println("4 is a friend of 0 -> NO");
        }

        // check if 1 is a friend of 0
        if (dus.find(1) == dus.find(0)) {
            System.out.println("1 is a friend of 0 -> YES");
        } else {
            System.out.println("1 is a friend of 0 -> NO");
        }
    }

    public static void testDetectCycleInUnGraph() {
        /**
         * 0
         * | \
         * |  \
         * 1 - 2
         */

        int V = 3, E = 3;
        DetectCycleInUnGraph graph = new DetectCycleInUnGraph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph)) System.out.println("contains cycle");
        else System.out.println("no cycle");
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
        JobSequence.printJobSchedule(arr);
    }
}
