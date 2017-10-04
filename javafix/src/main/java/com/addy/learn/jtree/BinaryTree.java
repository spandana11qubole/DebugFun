package com.addy.learn.jtree;
import java.util.List;
public class BinaryTree<Type> {
   public static int MAX_LINKS = 2;
   public static int LEFT_CHILD = 0;
   public static int RIGHT_CHILD = 1;

   private Node<Type> rootNode;
   private int treeDepth;

   public BinaryTree(){
      rootNode = new Node<Type>();
      rootNode.setLinkLimit(2);
      treeDepth = 0;

   }


   private Node<Type> getChild(int depth,int childIndex){
      Node<Type> child  = null;
      if (depth < treeDepth){
        Node<Type>  begin = rootNode;
        for(int i = 0; i < depth; i++){
             List<Node<Type>> links  = begin.getLinks();

        }
      }
      //if invalid depth return null instead of raising an exception
      return child;
   }

   public void AddRightChild(Type data,int depth){

   }

   public void AddLeftChild(Type data,int depth){

   }

   public void removeLeftChild(int depth){

   }

   public void removeRightChild(int depth){

   }

   public Type getLeftChildData(int depth){
       Type data=null;
       return data;
   }

   public Type getRightChildData(int depth){
      Type data=null;
      return data;
   }
}
