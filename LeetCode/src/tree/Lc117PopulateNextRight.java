package tree;

public class Lc117PopulateNextRight {

    public void connect(TreeLinkNode root) {
        if(root == null) return;

        TreeLinkNode head = root;
        while(head != null){
            head = linkNextLevel(head);
        }
    }

    private TreeLinkNode linkNextLevel(TreeLinkNode preHead){
        TreeLinkNode curHead = new TreeLinkNode(-1);
        TreeLinkNode cur = curHead;

        while(preHead != null){
            if(preHead.left == null && preHead.right == null) preHead = preHead.next;
            else break;
        }

        while(preHead != null){
            if(preHead.left != null){
                cur.next = preHead.left;
                cur = cur.next;
            }
            if(preHead.right != null){
                cur.next = preHead.right;
                cur = cur.next;
            }
            preHead = preHead.next;
        }
        return curHead.next;
    }

    class TreeLinkNode{
        int val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
        TreeLinkNode(int x ) {
            val = x;
        }
    }
}
