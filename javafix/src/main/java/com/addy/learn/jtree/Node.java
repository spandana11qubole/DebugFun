package com.addy.learn.jtree;

import java.util.List;

public class Node<NodeType> {
    public static int MAX_LINKS = 0;
    private List<Node<NodeType>> links;
    private int linkLimit;
    private  int nodeIndex;
    private NodeType data;

    public List<Node<NodeType>> getLinks() {
        return links;
    }

    public void setLink(Node<NodeType> link,int linkIndex) throws  IllegalArgumentException{
        if  ( linkLimit !=0 ){
            if(linkIndex > linkLimit){
                throw new IllegalArgumentException("Node can only have upto " + Integer.toString(linkLimit));
            }
        }
        this.links.add(linkIndex,link);
    }

    public int getLinkLimit() {
        return linkLimit;
    }

    public void setLinkLimit(int linkLimit) {
        this.linkLimit = linkLimit;
    }

    public NodeType getData() {
        return data;
    }

    public void setData(NodeType data) {
        this.data = data;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}
