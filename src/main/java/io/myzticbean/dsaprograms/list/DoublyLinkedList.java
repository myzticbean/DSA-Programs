package io.myzticbean.dsaprograms.list;

public class DoublyLinkedList {
    Node head;
    Node tail;
    int length;

    public class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    public DoublyLinkedList(int value) {
        Node node = new Node(value);
        head = node;
        tail = node;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node node = new Node(value);
        if(head == null) {
            head = node; tail = node;
            length = 1;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
        length++;
    }

    public Node removeLast() {
        printAll();
        if(tail == null) {
            return null;
        }
        Node returnNode = tail;
        length--;
        if(length == 0) {
            head = null; tail = null;
            return returnNode;
        }
        tail.prev.next = null;
        tail = tail.prev;
        return returnNode;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode; tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if(head == null)
            return null;
        Node removedNode = head;
        length--;
        if(length == 0) {
            head = null; tail = null;
        } else {
            head = head.next;
            head.prev = null;
            removedNode.next = null;
        }
        return removedNode;
    }

    public Node get(int index) {
        if(index < 0 || index >= length) {
            return null;
        }
        Node iter = null;
        if(index < length/2) {
            iter = head;
            for(int i = 0; i < index; i++) {
                iter = iter.next;
            }
        } else {
            iter = tail;
            for(int i = length-1; i > index; i--) {
                iter = iter.prev;
            }
        }
        return iter;
    }

    public boolean set(int index, int value) {
        Node nodeEdit = get(index);
        if(nodeEdit == null)
            return false;
        nodeEdit.value = value;
        return true;
    }

    // index 4 means insert after index 3 !!!
    public boolean insert(int index, int value) {
        if(index < 0 || index > length)
            return false;
        if(index == 0) {
            prepend(value);
        } else if(index == length) {
            append(value);
        } else {
            Node newNode = new Node(value);
            Node before = get(index - 1);
            Node after = before.next;
            before.next = newNode;
            newNode.prev = before;
            newNode.next = after;
            if(after != null)
                after.prev = newNode;
            length++;
        }
        return true;
    }

    public Node remove(int index) {
        if(index < 0 || index >= length)
            return null;
        if(index == 0)
            return removeFirst();
        else if(index == length - 1)
            return removeLast();
        else {
            // index is not 0 or length, means length is at least 3
            Node toRemove = get(index);
            Node before = toRemove.prev;
            Node after = toRemove.next;
            before.next = after;
            after.prev = before;
            toRemove.next = null;
            toRemove.prev = null;
            length--;
            return toRemove;
        }
    }

}
