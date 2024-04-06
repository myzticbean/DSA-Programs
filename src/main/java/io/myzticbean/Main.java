package io.myzticbean;

import io.myzticbean.dsaprograms.list.DoublyLinkedList;
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

        testNormalDLL();

    }

    private static int getRandomNumber() {
        return random.nextInt(1000);
    }

    private static void testNormalDLL() {
        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(2);
        myDLL.append(3);
        myDLL.append(4);
        myDLL.append(5);
        // 1 2 3 4 5
        // 2 1 3 4 5
        // 2 1 4 3 5
        System.out.println("DLL:");
        myDLL.printList();

        System.out.println("\nmyDLL isPalindrome:");
        System.out.println( myDLL.isPalindrome() );

        myDLL.swapFirstLast();

        System.out.println("\nDLL after swap:");
        myDLL.printList();

        myDLL.reverse();

        System.out.println("\nDLL after reverse:");
        myDLL.printList();

        myDLL.swapPairs();

        System.out.println("\nmyDll after swapPairs:");
        myDLL.printList();
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