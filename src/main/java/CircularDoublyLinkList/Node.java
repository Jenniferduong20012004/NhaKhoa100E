package CircularDoublyLinkList;

import Entity.Picture;

public class Node {
    private Picture picture;
    private Node prev;
    private Node next;

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Picture getPicture() {
        return picture;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }
    public Node (Picture picture){
        this.picture = picture;
        this.next = null;
        this.prev = null;
    }
}
