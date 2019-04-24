// https://www.geeksforgeeks.org/remove-bst-keys-in-a-given-range/


// Java  implementation of the approach 
import java.util.*; 
class Solution 
{ 
    
    
static class BSTnode {  
  
    int data;  
    BSTnode left, right;  
    BSTnode(int data)  
    {  
        this.data = data;  
        this.left = this.right = null;  
    }  
} 
    
// A Utility function to find leftMost node  
static BSTnode leftMost(BSTnode root)  
{  
    if (root==null)  
        return null;  
    while (root.left!=null)  
        root = root.left;  
    return root;  
}  
    
// A Utility function to delete the give node  
static BSTnode deleteNode(BSTnode root)  
{  
    // node with only one chile or no child  
    if (root.left==null) {  
        BSTnode child = root.right;  
        root = null;  
        return child;  
    }  
    else if (root.right==null) {  
        BSTnode child = root.left;  
        root = null;  
        return child;  
    }  
    
    // node with two children: get inorder successor  
    // in the right subtree  
    BSTnode next = leftMost(root.right);  
    
    // copy the inorder successor's content to this node  
    root.data = next.data;  
    
    // delete the inorder successor  
    root.right = deleteNode(root.right);  
    
    return root;  
}  
    
// function to find node in given range and delete  
// it in preorder manner  
static BSTnode removeRange(BSTnode node, int low, int high)  
{  
    
    // Base case  
    if (node==null)  
        return null;  
    
    // First fix the left and right subtrees of node  
    node.left = removeRange(node.left, low, high);  
    node.right = removeRange(node.right, low, high);  
    
    // Now fix the node.  
    // if given node is in Range then delete it  
    if (node.data >= low && node.data <= high)  
        return deleteNode(node);  
    
    // Root is out of range  
    return node;  
}  
    
// Utility function to traverse the binary tree  
// after conversion  
static void inorder(BSTnode root)  
{  
    if (root!=null) {  
        inorder(root.left);  
        System.out.print( root.data + " ");  
        inorder(root.right);  
    }  
}  
    
// Driver Program to test above functions  
public static void main(String args[]) 
{  
    /* Let us create following BST  
             50  
          /     \  
         30      70  
        /  \    /  \  
      20   40  60   80 */
    BSTnode root = new BSTnode(50);  
    root.left = new BSTnode(30);  
    root.right = new BSTnode(70);  
    root.left.right = new BSTnode(40);  
    root.right.right = new BSTnode(80);  
    root.right.left = new BSTnode(60);  
    root.left.left = new BSTnode(20);  
    
    System.out.print( "Inorder Before deletion: ");  
    inorder(root);  
    
    root = removeRange(root, 50, 70);  
    
    System.out.print( "\nInorder After deletion: ");  
    inorder(root);  
    
  
}  
} 
//contributed by Arnab Kundu 
