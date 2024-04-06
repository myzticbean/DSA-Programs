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

    public void swapFirstLast() {
        if(length < 2)
            return;
        int temp = head.value;
        head.value = tail.value;
        tail.value = temp;
        // I though swapping the head and tail would work, but I was wrong
        //        Node temp = head;
        //        head = tail;
        //        tail = temp;
    }

    public void reverse() {
        if(length < 2)
            return;
        // start from the tail
        Node temp = tail;
        head = tail;
        while(temp != null) {
            // swap the next and prev pointers
            Node tempPointer = temp.next;
            temp.next = temp.prev;
            temp.prev = tempPointer;
            // move the temp pointer left
            temp = temp.next;
            // if temp is not null, move the tail pointer as well
            if(temp != null)
                tail = temp;
        }
    }

    public boolean isPalindrome() {
        StringBuilder fwd = new StringBuilder("");
        StringBuilder bwd = new StringBuilder("");
        for(Node temp = head; temp != null; temp = temp.next) {
            fwd.append(temp.value);
        }
        for(Node temp = tail; temp != null; temp = temp.prev) {
            bwd.append(temp.value);
        }
        System.out.println("Forward: " + fwd.toString());
        System.out.println("Backward: " + bwd.toString());
        if(fwd.toString().equals(bwd.toString()))
            return true;
        return false;
    }

    public void swapPairs() {
        if(head == null || head.next == null)
            return;

        Node prevTemp = head.prev;
        Node nextTemp = head.next.next;
        Node temp1 = head;
        Node temp2 = head.next;
        head = temp2;
        int i = 0;
        while(temp1 != null && temp2 != null) {
            if(i == 6)
                return;
            System.out.println("Before swapping:: prevTemp: " + (prevTemp != null ? prevTemp.value : "null")
                + " temp1: " + (temp1 != null ? temp1.value : "null")
                + " temp2: " + (temp2 != null ? temp2.value : "null")
                + " nextTemp: " + (nextTemp != null ? nextTemp.value : "null")
                + " || head: " + head.value);
            temp1.next = nextTemp;
            temp1.prev = temp2;
            temp2.next = temp1;
            temp2.prev = prevTemp;
            if(prevTemp != null)
                prevTemp.next = temp2;
            if(nextTemp != null)
                nextTemp.prev = temp1;
            // moving the pointers two blocks ahead
            if(nextTemp != null && nextTemp.next != null) {
                prevTemp = temp1;
                temp1 = prevTemp.next;
                temp2 = temp1.next;
                nextTemp = temp2.next;
            } else {
                System.out.println("After swapping:: prevTemp: " + (prevTemp != null ? prevTemp.value : "null")
                        + " temp1: " + (temp1 != null ? temp1.value : "null")
                        + " temp2: " + (temp2 != null ? temp2.value : "null")
                        + " nextTemp: " + (nextTemp != null ? nextTemp.value : "null"));
                break;
            }
            System.out.println("After swapping:: prevTemp: " + (prevTemp != null ? prevTemp.value : "null")
                    + " temp1: " + (temp1 != null ? temp1.value : "null")
                    + " temp2: " + (temp2 != null ? temp2.value : "null")
                    + " nextTemp: " + (nextTemp != null ? nextTemp.value : "null"));
        }
    }

}
