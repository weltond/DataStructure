package com.weltond.unionfind;

/**
 * https://www.geeksforgeeks.org/disjoint-set-data-structures-java-implementation/
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class DisjointUnionSets {
    int[] rank, parent;
    int n;

    public DisjointUnionSets(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;

        makeSet();
    }

    // Creates n sets with single item in each
    void makeSet() {
        for (int i = 0; i < n; i++) {
            // Initially, all elements are in their own set
            parent[i] = i;
        }
    }

    // Returns representative of x's set
    int find(int x) {
        // Finds the representative of the set that x is an element of
        if (parent[x] != x) {
            // if x is not the parent of itself,
            // then x is not the representative of his set
            parent[x] = find(parent[x]);

            // so we recursively call Find on its parent
            // and move i's node directly under the representative of this set
        }
        return parent[x];
    }

    // Unites the set that includes x and the set that includes y
    void union(int x, int y) {
        // find representatives of two sets
        int xRoot = find(x);
        int yRoot = find(y);

        // if elements are in the same set, no need to unite anything
        if (xRoot == yRoot) return;

        // if x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot]) {
            // then move x under y so that depth of tree remains less
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            // if rank is the same
            // then move y under x (doesn't matter here)
            parent[yRoot] = xRoot;

            // and increment the result tree's rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
