// Solution 3 PriorityQueue
// Idea:
//	1. Based on solution 2
//	2. Use PriorityQueue to get the smallest number every time
//  3. PriorityQueue can return min/max number in O(logn) time.

// Time = O(nlogk), Space = O(k)
class Solution3 {
	static class MyComparator implements Comparator<ListNode> {
		public int compare(ListNode l1, ListNode l2) {
			return l1.val - l2.val;
		}
	}
	
	public ListNode mergeKList(ListNode[] lists) {
		if (lists == null || lists.length == 0) return;
		
		ListNode res = new ListNode(0);	// dummy node
		ListNode cur = res;
		PriorityQueue<ListNode> heap = new PriorityQueue(lists.length, new MyComparator());
		
		// Insert the head of all lists
		for (ListNode listHead: lists) {
			if (listHead != null) {
				heap.add(listHead);
			}
		}
		
		while (!heap.isEmpty()) {
			ListNode popNode = heap.poll();
			cur.next = popNode;
			cur = cur.next;
			if (popNode.next != null) {
				heap.add(popNode.next);
			}
		}
		
		return res.next;
	}
}

// Solution 2 Comparison/Pointers
// Idea:
// 	1. Compare every k nodes (head of every linked list) and get the node with the smallest value.
//	2. Extend the final sorted linked list with the selected nodes.

// Time = O(nk), Space = O(1)

class Solution2 {
	public ListNode mergeKList(ListNode[] lists) {
		if (lists == null || lists.length == 0) return;
		
		ListNode res = new ListNode(0);	// dummy node
		ListNode cur = res;
		ListNode tmp = null;
		
		// tmp is for checking whether all list nodes are traversed.
		do {
			tmp = findMinAndMove(lists);
			cur.next = tmp;
			cur = cur.next;
		} while (tmp != null);
		
		return res.next;
	}
	
	// Find the smallest node in all of linked list's head/pointer
	private ListNode findMinAndMove(ListNode[] lists) {
		int minVal = Integer.MAX_VALUE;
		int retIdx = -1;
		
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] == null) continue;
			if (lists[i].val < minVal) {
				minVal = lists[i].val;
				retIdx = i;
			}
		}
		
		ListNode retNode = null;
		if (retIdx != -1) {
			retNode = lists[retIdx];
			lists[retIdx] = lists[retIdx].next;
		}
		return retNode;
	}
}

// Solution 1 Brute Force
// Time = O(nklog(nk)), Space = O(nk)
class Solution1 {
	public ListNode mergeKList(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		
		List<Integer> all_nodes = new ArrayList<Integer>();
		for (ListNode node : lists) {
			while (node != null) {
				all_nodes.add(node.val);
				node = node.next;
			}
		}
		
		Collections.sort(all_nodes);
		
		//Dummy Node
		ListNode ret = new ListNode(0);
		ListNode cur = ret;
		for (Integer nodeVal : all_nodes) {
			ListNode tmp = new ListNode(nodeVal);
			cur.next = tmp;
			cur = cur.next;
		}
		
		return ret.next;
	}
}
