package CTCI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Trees and Graphs
public class ChapterFour {
    class Node{
        int data;
        HashSet<Node> next;
    }

    class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
    }

    //4.1 - bi-directional search
    public boolean routeBetweenTwoNodes(Node s, Node e){
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(s);
        visited.add(e);
        queue.add(s);
        queue.add(e);

        while(!queue.isEmpty()){
            Node currentNode = queue.remove();
            visited.add(currentNode);
            for(Node n : currentNode.next){
                if(!visited.contains(n) && )
            }
        }
    }

    //4.2
    public Node minimalTree(int[] sorted){
        return null;
    }

    //4.3
    public ArrayList<LinkedList<Node>> listOfDepths(Node root){
        return null;
    }

    //4.4
    public boolean checkBalanced(Node root){
        return false;
    }

    //4.5
    public boolean validateBST(Node root){
        return false;
    }

    //4.6


}
