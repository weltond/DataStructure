# Trie
Trie is an efficient information re**Trie**val data structure.

Using Trie, seach complexities can be broght to optimal limit (key length). 

If we store keys in binary search tree,
a well balanced BST will need time proportional to **M * logN**, where M is maximum string length and N is number of keys in tree.

Using Trie, we can search the key in O(M) time. However the penalty is on Trie storage requirement. 

Every node of Trie consists of multiple branches. Each branch represents a possible character of keys. We need to mark the last node of every key
as end of word node. A Trie node field `isEndOfWord` is used to distinguish the node as end of word node.

A simple structure to represent nodes of English alphabet can be as following.

```java
//Trie node
class TrieNode 
{
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];
    
    boolean isEndofWord;
}
```
## Insert 
Inserting a key into Trie is simple approach. Every character of input key is inserted as an individual Trie node. 

Note that the children is an array of pointers(or references) to next level Trie nodes.

The key character acts as an index into the array *children*. If the input key is new or an extension of existing key, we need to 
construct non-existing nodes of the key, and mark end of word for last node.

If the input key is prefix of existing key in Trie, we simply mark the last node of key as end of word.

The key length determines Trie depth.

## Search
Searching for a key is similar to insert operation, however, we only compare the characters and move down.

The search can terminate due to end of string or lack of key in Trie. In the former case, if the `isEndOfWord` field of last node is true,
then the key exists in Trie. In the second case, the search terminates without examining all the characters of key, since the key
is not present in Trie.

## Time and Space Complexity
Insert and Search costs **O(key_length)**, however, the memory requirements of Trie is **O(ALPHABET_SIZE * key_length * N) where
N is number of keys in Trie. 

There are efficient representation of Trie Nodes (e.g. **compressed trie**, **ternary search tree**, etc) to minimize memory requirements of trie

## Delete
During delete operation, we delete the key in bottom up manner using recursion. 

The following are possible conditions when deleting key from Trie:

1. Key may not be there in Trie. Delete operation should not modify Trie.
2. Key present as unique key (no part of key contains another key(prefix), nor the key itself is prefix of another key in Trie). Delete all the nodes.
3. Key is prefix key of another long key in Trie, Unmark the leaf node.
4. Key present in Trie, having at least one other key as prefix key. Delete nodes from end of key until first leaf node of longest prefix key.
## Applications
### Auto Complete
Given a query prefix, we search for all words having this query:
1. Search for given query using standard Trie seach algorithm.
2. If query prefix itself is not present, return -1 to indicate the same.
3. If query is present and is end of word in Trie, print query. This can quickly checked by seeing if last matching node has `isEndWord`flag set. We use this flag in Trie to mark end of word nodes for purpose of searching.
4. If last matching node of query has no children, return.
5. Else recursively print all nodes under subtree of last matching node.

* How can we improve this?

    - The number of matches might just be too large so we have to be selective while displaying them.
    - We can restric ourselves to diplay only relevant results. By relevant, we can consider the past search history and show only the most searched matching strings as relevant results.
    - Store another value for the each node where `isLeaf` is true which contains the number of hits for that query search. 
      - E.g. If "hat" is searched for 10 times, then we store this `10` at the last node for "hat". Now when we want to show the recommandations, we display the top k matches with the highest hits.
