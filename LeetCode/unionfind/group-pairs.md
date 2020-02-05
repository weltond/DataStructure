## [Group Pairs](https://leetcode.com/discuss/interview-question/354150/Microsoft-or-Onsite-or-Group-Pairs)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

There is series of integer pairs like (1,3),(7,10),(5,7),(4,1). Create unique grouping if any value in pair matches with any value of other pairs. 

Example:

```
Input: (1,3),(7,10),(5,7),(4,1)

Output: (1,3,4) and (7,10,5)
```

## Answer
### Method 1 - Union Find

#### Approach 2
```java
public List<List<Integer>> unionFind(List<int[]> input) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Map<Integer, Integer> parent = new HashMap<Integer, Integer>();

    for (int[] inp : input) {
        int lp = getParent(inp[0], parent);
        int rp = getParent(inp[1], parent);
        parent.put(lp, rp);
        if (!parent.containsKey(rp))
            parent.put(rp, rp);
    }

    Map<Integer, List<Integer>> groups = new HashMap<Integer, List<Integer>>();
    for (Integer key : parent.keySet()) {
        int currentParent = parent.get(key);
        if(!groups.containsKey(currentParent))
            groups.put(currentParent,  new ArrayList<Integer>());
        groups.get(currentParent).add(key);
    }
    
    for(Integer key: groups.keySet())
        result.add(groups.get(key));
    
    return result;
}

private int getParent(int i, Map<Integer, Integer> parent) {
    while (parent.containsKey(i) && parent.get(i) != i)
        i = parent.get(i);
    return i;
}

public static void main(String[] args) {
    List<int[]> input = new ArrayList<int[]>();
    input.add(new int[] { 1, 3 });
    input.add(new int[] { 7, 10 });
    input.add(new int[] { 5, 7 });
    input.add(new int[] { 4, 1 });
    UF u = new UF();
    System.out.println(u.unionFind(input));

}
```

#### Approach 1

```java
    List<int[]> input = new ArrayList<int[]>();
    input.add(new int[] { 1, 3 });
    input.add(new int[] { 7, 10 });
    input.add(new int[] { 5, 7 });
    input.add(new int[] { 4, 1 });
    Solution u = new Solution();
    System.out.println(u.unionFind(input));

}

private List<List<Integer>> unionFind(List<int[]> input) {
	parentMap = new HashMap<Integer, Integer>();
	for(int[] node: input) {
		parentMap.putIfAbsent(node[0],node[0]);
		parentMap.putIfAbsent(node[1],node[1]);
		union(node[0],node[1]);
	}
	Map<Integer,List<Integer>> resMap= new HashMap<Integer, List<Integer>>();
	for(int n:parentMap.keySet()) {
		int parent= find(n);
		resMap.putIfAbsent(parent, new ArrayList<Integer>());
		List<Integer> list= resMap.get(parent);
		list.add(n);
	}
	List<List<Integer>> res=new ArrayList<>();
	for(int n: resMap.keySet()) {
		res.add(resMap.get(n));
	}
	return res;
	
}
int find(int node) {
	while(node!=parentMap.get(node)) {
		node=parentMap.get(node);
	}
	return node;
}
 void union(int node1,int node2) {
	 int parent_node1=parentMap.get(node1);
	 int parent_node2=parentMap.get(node2);
	 if(parent_node1!=parent_node2) {
		parentMap.put(parent_node2, parent_node1);
	 }
}
```
