package com.yudl.sturcture.queue;



/**
 * @author yudongliang
 * create time 2021-05-27
 * describe : 数组内部实现的 队列
 */
public class MyArrayQueue {

    public int front = -1;
    public int rear = -1;
    public int maxSize = 4;
    public int[] srcArr ;
    public int size = 0;

    public MyArrayQueue(int maxSize){
        this.maxSize = maxSize;
        srcArr = new int[maxSize];
        front = 0;
        rear = -1;
//        Character.isDigit()
    }

    /* 检车数组中的数组是否已经满 */
    public boolean isFill(){
        System.out.println("isFill rear ->"+rear+" ,maxSize ->"+maxSize);
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return size == 0; //或者 rear == -1 为空
    }

    public void addQueue(int element){
        if (isFill()){
            System.out.println("addQueue 已经满了 ");
            return;
        }
        srcArr[++rear] = element;
        listIterator();
        size++;
    }
    /* 从头部取出第一个的数据 */
    public int removeQueue(){
        if(isEmpty()){
            System.out.println("数组已经为空 -- ");
            return -2;
        }
        int oldValue = srcArr[front];
        int numMoved = size - 1;
        if (numMoved > 0)
            System.arraycopy(srcArr, 1, srcArr, 0, numMoved);
        srcArr[--size] = 0;
        rear--;
        System.out.println("isFill rear ->"+rear+" ,size ->"+size);
        listIterator();
        return oldValue;
    }

    public void listIterator(){
        for (int i : srcArr) {
            System.out.printf("元素->%d\t",i);
        }
        System.out.println();
    }
}
