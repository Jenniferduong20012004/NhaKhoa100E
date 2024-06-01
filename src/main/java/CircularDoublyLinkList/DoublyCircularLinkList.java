package CircularDoublyLinkList;

import Entity.Picture;

public class DoublyCircularLinkList {
    private Node head;
    private int size;

    public Node getHead() {
        return head;
    }

    public DoublyCircularLinkList(){
        head = null;
    }
    public void insert (Picture picture){
        Node newNode = new Node (picture);
        if (head == null){
            head = newNode;
            newNode.setNext(head);
            newNode.setPrev(head);
        }
        else{
            Node temp = head.getPrev();
            temp.setNext(newNode);
            newNode.setPrev(temp);
            newNode.setNext(head);
            head.setPrev(newNode);
        }
    }
    public Node getNode(int index) {
        if (head == null || index < 0) {
            return null;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
            if (current == head) {
                return null; // Index out of bounds
            }
        }
        return current;
    }
    public void clear() {
        head = null;
    }
    public Node getPrevious(Node node){
        return node.getPrev();
    }
    public Node getNext(Node node){
        return node.getNext();
    }
}
