package io.myzticbean;

import io.myzticbean.dsaprograms.list.LinkedList;

import java.util.Random;

public class Main {

    static final Random random = new Random();
    public static void main(String[] args) {
        consoleLog("============");
        consoleLog("DSA Programs");
        consoleLog("============\n");


        testNormalLLImpl();

        testLoopyLL();


    }

    private static int getRandomNumber() {
        return random.nextInt(1000);
    }

    private static void testNormalLLImpl() {
        consoleLog("Testing Normal LinkedList implementations...");
        LinkedList<Integer> linkedList = new LinkedList<>(129);
        linkedList.append(120);
        linkedList.append(getRandomNumber());
        linkedList.append(getRandomNumber());
        linkedList.append(getRandomNumber());
        linkedList.append(getRandomNumber());
        linkedList.append(getRandomNumber());
        linkedList.append(getRandomNumber());

        linkedList.printList();

        linkedList.prepend(130);
        linkedList.prepend(140);
        linkedList.prepend(150);

        linkedList.printList();

        consoleLog("Removed element: " + linkedList.removeLast());

        linkedList.printList();

        linkedList.append(120);
        linkedList.printList();
        consoleLog("Removed element: " + linkedList.removeLastAsPerTutorial());

        linkedList.printList();

        linkedList.reverse();
        linkedList.printList();
        consoleLog(String.valueOf(linkedList.getLength()));
    }

    private static void testLoopyLL() {
        consoleLog("Testing LinkedList has loop or not...");

        LinkedList<Integer> myLinkedList = new LinkedList<>(getRandomNumber());
        myLinkedList.append(getRandomNumber());
        myLinkedList.append(getRandomNumber());
        myLinkedList.append(getRandomNumber());
        myLinkedList.append(getRandomNumber());

        myLinkedList.printList();
        // create a loop by connecting the tail to the second node
        myLinkedList.getTail().setNext(myLinkedList.getHead().getNext());
        // myLinkedList.getTail().setNext(myLinkedList.getHead().getNext().getNext());
        // myLinkedList.getTail().setNext(myLinkedList.getTail());


        consoleLog("Has Loop?");
        consoleLog(String.valueOf(myLinkedList.hasLoop()));
    }

    public static void consoleLog(String text) {
        System.out.println(text);
    }
}