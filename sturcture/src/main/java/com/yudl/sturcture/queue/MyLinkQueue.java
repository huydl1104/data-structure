package com.yudl.sturcture.queue;

import java.util.Stack;

/**
 * @author yudongliang
 * create time 2021-05-27
 * describe : 单链表去实现队列的功能实现
 */
public class MyLinkQueue {

    public int size;
    public Node head = new Node(-1);

    /* 从尾到头的打印单链表（反向遍历，stack栈） */
    public void  reservePrintln(){
        if (head.next == null || head.next.next == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node next = head.next;
        while (next != null){
            stack.push(next);
            next = next.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop().data);
        }
    }

    /* 反转 整个单链表 */
    public void reserveLink(){
        if (head.next == null || head.next.next == null){
            return;
        }
        Node curNode = head.next;
        Node next;
        Node newNode = new Node(-1);

        while (curNode != null){
            next = curNode.next;
            curNode.next = newNode.next;
            newNode.next = curNode;
            curNode = next;
        }
        head.next = newNode.next;
        listIterator();
    }

    /* 像链表的最后一位加数据 */
    public void addLastData(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        size++;
        listIterator();
    }

    /* 得到链表最后 k 的数据 */
    public Node getLastKNode(int index){
        int originIndex = getSimpleLinkLength() - index;
        Node cur = head.next;
        for (int i = 0; i < originIndex; i++) {
            cur = cur.next;
        }
        System.out.println("getLastKNode ->"+cur);
        return cur;
    }
    /* 得到链表的长度 */
    public int getSimpleLinkLength(){
        if (isEmpty()){
            return 0;
        }
        int length = 0;
        Node currentNode = head.next;
        while (currentNode != null){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }
    /* 得到链表是否为空 */
    public boolean isEmpty(){
        return head.next == null;
    }
    /* 将数据查到中间的部分 */
    public void addOrderData(int index, Node node) {
        if (index == 0) {
            addFirst(node);
        } else if (index == size) {
            addLastData(node);
        } else {
            if (index > 0 && index < size){
                Node temp = getNode(index);
                if (temp != null){
                    Node next = temp.next;
                    temp.next = node;
                    node.next = next;
                    listIterator();
                }
            }
        }
    }

    public Node getNode(int index) {
        Node x = head.next;
        for (int i = 0; i < index-1; i++){
            x = x.next;
        }
        return x;
    }

    public void addFirst(Node node) {
        Node first = head.next;
        head.next = node;
        node.next = first;
        listIterator();
    }

    public Node removeNode() {
        if (isEmpty()){
            return null;
        }
        Node first = head.next;
        head.next = first.next;
        listIterator();
        return first;
    }

    public Node removeNode(int index){
        if (isEmpty()){
            return null;
        }
        Node leftNode = getNode(index);
        Node deleteNode = leftNode.next;
        leftNode.next = deleteNode.next;
        listIterator();
        return deleteNode;
    }

    public void listIterator() {
        Node node = head.next;
        while (node != null) {
            System.out.printf("元素数据 %d \t", node.data);
            node = node.next;
        }
        System.out.println();
    }

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

}



