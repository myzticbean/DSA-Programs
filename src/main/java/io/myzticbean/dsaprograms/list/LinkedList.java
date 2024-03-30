package io.myzticbean.dsaprograms.list;

import io.myzticbean.Main;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    private int length;


    public class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }
    }

    public LinkedList(T value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    /**
     * Appends to the end of the list
     * @param value Value to add
     */
    public void append(T value) {
        Node node = new Node(value);
        if(tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        length++;
    }

    /**
     * Appends to the head of the list
     * @param value Value to add
     */
    public void prepend(T value) {
        Node node = new Node(value);
        if(head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        length++;
    }

    public T removeLast() {
        if(length == 0)
            return null;
        Node preNode = head;
        while(preNode.next != null && preNode.next.next != null) {
            preNode = preNode.next;
        }
        Node removedNode = null;
        if(head == tail) {
            removedNode = head;
            head = tail = null;
        } else {
            removedNode = preNode.next;
            preNode.next = null;
            tail = preNode;
        }
        length--;

        return removedNode.value;
    }

    public T removeLastAsPerTutorial() {
        if(length == 0)
            return null;
        Node pre = head;
        Node temp = head;
        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if(length == 0) {
            head = tail = null;
        }
        return temp.value;
    }

    public T removeFirst() {
        // if length is 0
        if(length == 0) {
            return null;
        }

        Node removedNode = head;
        head = head.next;
        removedNode.next = null;


        length--;
        if(length == 0) {
            tail = null;
        }
        return removedNode.value;
    }

    public Node get(int index) {
        if(index < 0 || index > length) {
            return null;
        }
        Node temp = head;
        for(int i=0; i<index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, T value) {
        Node temp = get(index);
        if(temp == null)
            return false;
        temp.value = value;
        return true;
    }

    public boolean insert(int index, T value) {
        if((index > length || index < 0)) {
            return false;
        }

        if(index == 0) {
            prepend(value);
            return true;
        }

        if(index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node nodeAtPrevIndex = get(index-1);
        newNode.next = nodeAtPrevIndex.next;
        nodeAtPrevIndex.next = newNode;
        length++;
        return true;
    }

    public T remove(int index) {
        if(index < 0 || index > length) return null;
        if(index == 0) return removeFirst();
        if(index == length-1) return removeLast();

        Node prev = get(index-1);
        Node temp = prev.next;
        prev.next = temp.next;
        length--;
        if(length == 0) {
            head = tail = null;
        }
        return temp.value;
    }

    public void reverse() {
        // Reverse the head and tail
        Node temp = head;
        head = tail;
        tail = temp;

        Node before = null, after = temp.next;
        // Now iterate and reverse each Node
        for(int i = 0; i<length; i++) {
            after = temp.next;
            // reverse the node next to previous
            temp.next = before;
            // move the before pointer to right
            before = temp;
            // move the temp pointer to right
            temp = after;
        }
    }

    /**
     * Find the middle node in a linked list without using length attribute.
     * This method follows Floyd's Tortoise and Hare algorithm.
     * @return middle node
     */
    public Node findMiddleNode() {
        if(head == null) return null;
        Node slow = head, fast = head;
        while(slow.next != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // when loop breaks, slow should be at middle node
        return slow;
    }

    /**
     * Finds if the LinkedList has a loop or not due to some incorrect implementation.
     * This method follows Floyd's Tortoise and Hare algorithm.
     * @return
     */
    public boolean hasLoop() {
        if(head == null) return false;
        Node slow = head, fast = head;
        while(slow != null && fast != null && fast.next != null) {
            Main.consoleLog("slow: " + slow.value + " fast: " + fast.value);
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                Main.consoleLog("slow = fast: " + slow.value + " " + fast.value);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the k'th node from the end of the LinkedList
     * Uses a common technique in computer science known as the
     * 'fast-pointer / slow-pointer' or 'runner' technique, and
     * it's a neat way of finding a position relative to the end
     * of a list in a single pass.
     * @param k index from the end
     * @return k'th node from the end
     */
    public Node findKthFromEnd(int k) {
        if(head == null)
            return null;
        Node slow = head;
        Node fast = head;
        // move fast k steps
        for(int i = 0; i<k; i++) {
            if(fast != null) {
                fast = fast.next;
            } else {
                // LL smaller than k nodes
                return null;
            }
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



    public void printList() {
        System.out.print("LinkedList: ");
        Node temp = this.head;
        while(temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        Main.consoleLog("");
    }

    public int getLength() {
        return this.length;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }



}
