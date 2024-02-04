import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
   
  	public static void preOrder( Node root ) {
      
    	if( root == null)
        	return;
      
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
     
    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

	public static Node insert(Node root,int data) {

    	if (root==null)
            return new Node(data);
        else if(root.data < data)
        {
            root.right = insert(root.right, data);
            return root;
        }
        else
        {
            root.left = insert(root.left, data);
            return root;
        }
    }

	public static void main(String[] args) {
