import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Linked Lists
public class ChapterTwo {
    class Node {
        Node next = null;
        int data;

        public Node(int d){
            data = d;
        }

        void appendToTail(int d){
            Node end = new Node(d);
            Node n = this;
            while(n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }

    //2.1
    public void removeDuplicates(Node root){
        Node current = root;
        HashSet<Integer> set = new HashSet<>();
        while(current != null){
            set.add(current.data);
            while(current.next != null && set.contains(current.next.data))
                current.next = current.next.next;

            current = current.next;
        }
    }

    //2.2 - doesn't work if the list is shorter than k
    public Node kthToLast(Node root, int k){
        Node current = root;
        Queue<Node> queue = new LinkedList<>();
        while(current != null){
            queue.add(current);
            if(queue.size() > k)
                queue.remove();
            current = current.next;
        }

        return queue.remove();
    }

    //2.3
    public void deleteMiddleNode(Node node){
        node.data = node.next.data;
        node.next = node.next.next;
    }


    //2.4
    //this approach doesn't have to rewrite elements that are in the right order, but moving elements is 2x as expensive
    //the book version prepending the head and appending the tail is probably better
    public void partition(Node root, int x){
        //p1 is the end of the lessThan partition
        //p2 is the end of the greaterThan partition
        Node p1 = root;
        Node p2 = root;
        if(root.data < x){
            while(p1.next.data < x)
                p1 = p1.next;

            p2 = p1.next;
        } else {
            while(p2.next.data >= x)
                p2 = p2.next;

            p1 = p2.next;
            p2.next = p1.next;
            p1.next = root;
        }

        Node temp;
        while(p2.next != null){
            if(p2.next.data < x){
                temp = p2.next;
                p2.next = p2.next.next;
                temp.next = p1.next;
                p1.next = temp;
            } else
                p2 = p2.next;
        }
    }


    //2.5
    public Node sumLists(Node root1, Node root2){
        Node current1 = root1;
        Node current2 = root2;
        Node result = new Node(0);
        Node current3 = result;
        while(current1 != null && current2 != null){
            current3.data = current3.data + current1.data + current2.data;
            if(current3.data >= 10){
                current3.data -= 10;
                current3.next = new Node(1);
            } else {
                current3.next = new Node(0);
            }
        }

        if(current1 == null) {
            current3.data = current3.data + current2.data;
            if (current3.data >= 10) {
                current3.data -= 10;
                current3.next = new Node(1);
            } else {
                current3.next = new Node(0);
            }
        }

        if(current2 == null) {
            current3.data = current3.data + current1.data;
            if (current3.data >= 10) {
                current3.data -= 10;
                current3.next = new Node(1);
            } else {
                current3.next = new Node(0);
            }
        }

        return result;

    }
    //if we did this with the ones place at the tail it's basically the same, but we need to find the lengths of the list first
    //(also, it's a really stupid problem, because in that case converting would be like WAAAAAYYYYY better)

    //2.6
    public boolean palindrome(Node root){
        Node p1 = root;
        Node p2 = root;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //at this point, if p2 != null we have an odd number of elements, and p1 will be the middle element which is unique.
        //so skip it
        if(p2 != null)
            p1 = p1.next;

        Stack<Node> stack = new Stack<>();
        while(p1 != null) {
            stack.add(p1);
            p1 = p1.next;
        }

        while(!stack.isEmpty()){
            if(p2.data != stack.pop().data)
                return false;
            else
                p2 = p2.next;
        }

        return true;

    }

    //2.7
    public Node listIntersection(Node root1, Node root2){
        //easiest method is using a hashset, but I might run out of memory
        //I used the hints for this one... half credit
        Node current1 = root1; Node current2 = root2;
        int length1 = 0; int length2 = 0;

        while(current1 != null){
            length1++;
            current1 = current1.next;
        }

        while(current2 != null){
            length2++;
            current2 = current2.next;
        }

        current1 = root1; current2 = root2;
        if(length1 > length2){
            for(int i = 0; i < length1 - length2; i++){
                current1 = current1.next;
            }

        } else if(length2 > length1){
            for(int j = 0; j < length2 - length1; j++){
                current2 = current2.next;
            }
        }

        while(current1 != null){
            if(current1 == current2)
                return current1;
            else {
                current1 = current1.next;
                current2 = current2.next;
            }
        }

        return null;

    }

    //2.8
    public Node loopDetection(Node root) {
        Node p1 = root;
        Node p2 = root;

        return root;
    }

}
