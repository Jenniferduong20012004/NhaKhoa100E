package CircularDoublyLinkList;

import Entity.Picture;

public class DoublyCircularLinkList {
    private Node head;
    private int size;
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
}
