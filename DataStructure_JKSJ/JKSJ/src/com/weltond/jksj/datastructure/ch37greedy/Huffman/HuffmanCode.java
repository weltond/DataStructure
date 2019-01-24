package com.weltond.jksj.datastructure.ch37greedy.Huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class HuffmanCode {
    public CodeNode root;

    public class CodeNode {
        public char data;
        public int freq;
        public CodeNode left;
        public CodeNode right;
        public CodeNode parent;
        // Flag of whether it is original node
        public boolean isNotOrigin;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CodeNode{");
            sb.append("data=").append(data);
            sb.append(", frequence=").append(freq);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Encode huffman
     * @param map Compute each data and its frequency
     * @return encoded huffman code
     */
    public Map<Character, String> getHuffmanCode(Map<Character, Integer> map) {
        if (null != map && !map.isEmpty()) {
            // Store data using min heap
            PriorityQueue<CodeNode> nodePriorityQueue =
                    new PriorityQueue<>(
                            (o1, o2) -> Integer.compare(o1.freq, o2.freq)
                    );

            CodeNode nodeTmp = null;

            // 1. Put data into heap
            for (Map.Entry<Character, Integer> item : map.entrySet()) {
                nodeTmp = new CodeNode();
                nodeTmp.data = item.getKey();
                nodeTmp.freq = item.getValue();

                nodePriorityQueue.offer(nodeTmp);
            }
            int queueSize = nodePriorityQueue.size();

            // 2. encode
            for (int i = 1; i < queueSize; i++) {
                CodeNode left = nodePriorityQueue.poll();
                CodeNode right = nodePriorityQueue.poll();

                CodeNode sumNode = new CodeNode();
                sumNode.freq = left.freq + right.freq;
                sumNode.data = (char) ((int) left.data + (int) right.data);
                sumNode.isNotOrigin = true;

                sumNode.left = left;
                sumNode.right = right;

                left.parent = sumNode;
                right.parent = sumNode;

                nodePriorityQueue.offer(sumNode);
            }

            // 3. Get huffman tree root
            root = nodePriorityQueue.poll();

            // 4. Create Huffman code
            Map<Character, String> result = this.buildCode(root);

            return result;
        }
        return null;
    }

    public Map<Character, String> buildCode(CodeNode root) {
        Map<Character, String> result = new HashMap<>();
        StringBuilder code = new StringBuilder();
        this.helper(code, root, result);

        return result;
    }

    /**
     *  Use recursion to build huffman code
     * @param code each node's huffman code
     * @param node current node
     * @param result result
     */
    public void helper(StringBuilder code, CodeNode node, Map<Character, String> result) {
        if (null == node) return;

        if (!node.isNotOrigin) {
            result.put(node.data, code.toString());
        }

        if (node.left != null) {
            code.append("0");
            this.helper(code, node.left, result);
            code.deleteCharAt(code.length() - 1);
        }
        if (node.right != null) {
            code.append("1");
            this.helper(code, node.right, result);
            code.deleteCharAt(code.length() - 1);
        }
    }

    public String parseHuffman2(String src, Map<Character, String> huffman) {
        StringBuilder out = new StringBuilder();
        char[] hufSrcs = src.toCharArray();

        for (char hufs : hufSrcs) {
            out.append(huffman.get(hufs));
        }

        return out.toString();
    }

    public String decodeHuffman(String hufStr, CodeNode root) {
        char[] hubs = hufStr.toCharArray();
        int index = 0;

        StringBuilder resultMsg = new StringBuilder();

        while (index < hubs.length) {
            CodeNode node = root;
            do {
                if (hubs[index] == '0') {
                    node = node.left;
                } else if (hubs[index] == '1') {
                    node = node.right;
                }
                index++;
            } while (index < hubs.length && (node.left != null || node.right != null));
            resultMsg.append(node.data);
        }
        return resultMsg.toString();

    }

}
