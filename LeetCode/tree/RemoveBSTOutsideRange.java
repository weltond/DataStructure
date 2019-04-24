// https://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/


// A Java program to remove BST 
// keys outside the given range 
import java.math.BigDecimal; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Scanner; 
  
class Node 
{ 
    int key; 
    Node left; 
    Node right; 
} 
  
class GFG 
{ 
    // Removes all nodes having value 
    // outside the given range and  
    // returns the root of modified tree 
    private static Node removeOutsideRange(Node root,  
                                           int min, int max)  
    { 
        // BASE CASE 
        if(root == null) 
        { 
            return null; 
        } 
          
        // FIRST FIX THE LEFT AND 
        // RIGHT SUBTREE OF ROOT 
        root.left = removeOutsideRange(root.left,  
                                       min, max); 
        root.right = removeOutsideRange(root.right,  
                                        min, max); 
          
        // NOW FIX THE ROOT. THERE ARE  
        // TWO POSSIBLE CASES FOR THE ROOT 
        // 1. a) Root's key is smaller than 
        // min value(root is not in range) 
        if(root.key < min)  
        { 
            Node rchild = root.right; 
            root = null; 
            return rchild; 
        } 
          
        // 1. b) Root's key is greater than  
        // max value (Root is not in range) 
        if(root.key > max)  
        { 
            Node lchild = root.left; 
            root = null; 
            return lchild; 
        } 
          
        // 2. Root in range 
        return root; 
    } 
  
    public static Node newNode(int num)  
    { 
        Node temp = new Node(); 
        temp.key = num; 
        temp.left = null; 
        temp.right = null; 
        return temp; 
    } 
  
    public static Node insert(Node root, 
                              int key)  
    { 
        if(root == null)  
        { 
            return newNode(key); 
        } 
        if(root.key > key)  
        { 
            root.left = insert(root.left, key); 
        } 
        else 
        { 
            root.right = insert(root.right, key); 
        } 
        return root; 
    } 
      
    private static void inorderTraversal(Node root) 
    { 
        if(root != null)  
        { 
            inorderTraversal(root.left); 
            System.out.print(root.key + " "); 
            inorderTraversal(root.right); 
        } 
    } 
      
    // Driver code 
    public static void main(String[] args) 
    { 
        Node root = null; 
        root = insert(root, 6); 
        root = insert(root, -13); 
        root = insert(root, 14); 
        root = insert(root, -8); 
        root = insert(root, 15); 
        root = insert(root, 13); 
        root = insert(root, 7); 
          
        System.out.print("Inorder Traversal of " +  
                           "the given tree is: "); 
        inorderTraversal(root); 
          
        root = removeOutsideRange(root, -10, 13); 
          
        System.out.print("\nInorder traversal of " +  
                           "the modified tree: "); 
        inorderTraversal(root); 
    } 
} 
  
// This code is contributed  
// by Divya 
