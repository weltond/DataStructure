Table of Content
================
  * [Introduction](#Introduction)
  * [Insert](#Insert)
  * [Search](#Search)
  * [Time & Space Complexity](#Time-and-Space-Complexity)
  * [Delete](#Delete)
  * [Efficient Way](#Efficient-Way)
  * [Advantages of Trie Data Structure](#Advantages)
  * [Applications](#Applications)
  
## Introduction
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

## Efficient Way
The implementation above uses an array of alphabet size with every node. We can use a `Hash Map` to store children of nodes. Now we allocate memory only for alphabets in used, and don't waste space storing `null pointers`.

## Advantages
**Trie** is a tree that stores Strings. Maximum number of children of a node is equal to size of alphabet. Trie supports search, insert and delete operations in O(L) time where L is length of key.

#### Hashing
In hashing, we convert key to a small value and the value is used to index data. Hashing supports search, insert and delete operations in *O(L)* time on average.

#### Self Balancing BST
The time complexity of search, insert and delete oprations in a **self-balancing Binary Search Tree(BST)** (like **Red-Black Tree**, **AVL Tree**, **Splay Tree**, etc) is *O(Llogn)* where `n` is total number words and `L` is length of a word.

The advantage of self-balancing BSTs is that they maintain order which makes operations like **minimum, maximum, closest(floor or ceiling) and K-th largest** FASTER. 

Please refer to [Advantages of BST over Hash Table](https://www.geeksforgeeks.org/advantages-of-bst-over-hash-table/) for details.

#### Trie
- Why Trie?
  - With Trie, we can insert and find Strings in *O(L)* time where `L` is the length of a word. This is obviously faster than BST. This is also faster than Hashing because of the ways it is implemented: we don't need to compute any hash function. No collision handling is required(like **open addressing** and **separate chaining**)
  - Another advantage of Trie is, we can [easily print all words in alphabetical order](https://github.com/weltond/DataStructure/blob/master/LeetCode/trie/SortArrayOfStrings.java) which is not easily possible with hashing.
  - We can efficiently do [prefix seach (or auto-complete)](https://github.com/weltond/DataStructure/blob/master/LeetCode/trie/AutoComplete.java) with Trie.

- Issues with Trie
  - The main **disadvantage** of Trie is that they need lot of memory for storing the string. For each node we have too many node pointers(equal to number of characters of alphabet), If space is concern, then **Ternary Search Tree** can be preffered for dictionary implementations.
    - Ternary Search Tree
      - Time complexity of search is O(h) where `h` is height of the tree.
      - It also supports other operations supported by Trie like prefix search, alphabetical order printing and nearest neighbor search.
  
## Applications
### [Auto Complete](https://github.com/weltond/DataStructure/blob/master/LeetCode/trie/AutoComplete.java)
Given a query prefix, we search for all words having this query:
1. Search for given query using standard Trie seach algorithm.
2. If query prefix itself is not present, return -1 to indicate the same.
3. If query is present and is end of word in Trie, print query. This can quickly checked by seeing if last matching node has `isEndWord`flag set. We use this flag in Trie to mark end of word nodes for purpose of searching.
4. If last matching node of query has no children, return.
5. Else recursively print all nodes under subtree of last matching node.

* How can we improve this?

    - The number of matches might just be too large so we have to be selective while displaying them.
    - We can restrict ourselves to diplay only relevant results. By relevant, we can consider the past search history and show only the most searched matching strings as relevant results.
    - Store another value for the each node where `isLeaf` is true which contains the number of hits for that query search. 
      - E.g. If "hat" is searched for 10 times, then we store this `10` at the last node for "hat". Now when we want to show the recommandations, we display the top k matches with the highest hits.
### Word Break

### [Print Sorted String Array](https://github.com/weltond/DataStructure/blob/master/LeetCode/trie/SortArrayOfStrings.java)
