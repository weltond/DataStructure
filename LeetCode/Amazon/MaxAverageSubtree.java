package com.weltond.amazon;

import java.util.ArrayList;

/**
 * Poblem: Given a n-ary tree that represents company construct. Each node represents an employee which contains
 *          the months he has worked and his employees. Find a subtree such that: the average months among the subtree
 *          is the maximum. (excluding leaf node as he doesn't have any employees).
 *
 * @author weltond
 * @project LeetCode
 * @date 3/26/2019
 */
public class MaxAverageSubtree {

    private static double resAve = Double.MIN_VALUE;
    private static Node result;

    public static Node getHighAve(Node root){
        if (root == null) return null;
        dfs(root);
        return result;
    }

    private static SumCount dfs(Node root) {
        if (root == null || root.children.isEmpty()) return new SumCount(root.month, 1);

        int sum = root.month;
        int cnt = 1;
        for (Node child : root.children) {
            SumCount sumCount = dfs(child);
            sum += sumCount.sum;
            cnt += sumCount.count;
        }

        double curAvg = (double) sum / cnt;

        if (curAvg > resAve) {
            resAve = curAvg;
            result = root;
        }

        return new SumCount(sum, cnt);
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        Node l21 = new Node(2);
        Node l22 = new Node(3);
        Node l23 = new Node(4);
        Node l31 = new Node(5);
        Node l32 = new Node(5);
        Node l33 = new Node(5);
        Node l34 = new Node(5);
        Node l35 = new Node(5);
        Node l36 = new Node(5);

        l21.children.add(l31);
        l21.children.add(l32);

        l22.children.add(l33);
        l22.children.add(l34);

        l23.children.add(l35);
        l23.children.add(l36);

        root.children.add(l21);
        root.children.add(l22);
        root.children.add(l23);

        Node res = getHighAve(root);
        System.out.println(res.month + " " + resAve);   //4 4.666666666666667
    }
}

class SumCount{
    int sum;
    int count;
    public SumCount(int sum, int count){
        this.sum = sum;
        this.count = count;
    }
}

class Node {
    int month;
    ArrayList<Node> children;

    public Node(int val){
        this.month = val;
        children = new ArrayList<Node>();
    }
}