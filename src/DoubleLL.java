/**
 * @author Xavier Akers
 * 
 * @version Last Updated
 * 
 * @since 11-17-2023
 * 
 * 
 * 
 */
public class DoubleLL {
    private GraphNode head;
    private GraphNode tail;
    private int size;

    public DoubleLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    public void append(int data) {
        GraphNode newNode = new GraphNode(data);

        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }


    public void remove(int data) {
        GraphNode current = head;

        while (current != null && !(current.getData() == data)) {
            current = current.getNext();
        }

        if (current != null) {
            if (current.getPrev() == null) {
                head = current.getNext();
                if (head != null) {
                    head.setPrev(null);
                }
            }
            else {
                current.getPrev().setNext(current.getNext());
            }

            if (current.getNext() == null) {
                tail = current.getPrev();
                if (tail != null) {
                    tail.setNext(null);
                }
            }
            else {
                current.getNext().setPrev(current.getPrev());
            }
            size--;
        }
    }


    public int getSize() {
        return size;
    }
}
