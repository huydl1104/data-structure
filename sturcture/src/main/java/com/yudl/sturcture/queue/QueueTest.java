package com.yudl.sturcture.queue;


/**
 * @author yudongliang
 * create time 2021-05-27
 * describe : 使用 数组结构实现 队列；
 *      数组是连续的一快内存，使用角标的 取得数组
 *      也可以是用 单链表 实现
 *      1、求单链表中节点的个数
 *      2、查找单链表中的倒数第k个节点
 *      3、单链表的反转
 *      4、从尾到头的打印单链表（反向遍历，stack栈）
 *      5、合并两个有序的单链表
 */
public class QueueTest {


    public static void main(String[] args) {




        //----------- 单链表实现队列 -------------------
/*        MyLinkQueue linkQueue = new MyLinkQueue();
        linkQueue.addLastData(new MyLinkQueue.Node(3));
        linkQueue.addLastData(new MyLinkQueue.Node(5));
        linkQueue.addLastData(new MyLinkQueue.Node(10));
        linkQueue.addLastData(new MyLinkQueue.Node(12));
        linkQueue.addOrderData(2,new MyLinkQueue.Node(17));
        linkQueue.reservePrintln();

        linkQueue.reserveLink();
        linkQueue.removeNode(1);

        linkQueue.getLastKNode(0);*/


        // --------------- 数据实现队列 -------------
/*        MyArrayQueue myArrayQueue = new MyArrayQueue(5);
        System.out.println("------ add  ----- ");
        myArrayQueue.addQueue(1);
        myArrayQueue.addQueue(3);
        myArrayQueue.addQueue(5);
        myArrayQueue.addQueue(7);
        myArrayQueue.addQueue(9);
        myArrayQueue.addQueue(13);

        System.out.println("------ remove  ----- ");
        myArrayQueue.removeQueue();
        myArrayQueue.removeQueue();
        myArrayQueue.removeQueue();
        myArrayQueue.removeQueue();
        myArrayQueue.removeQueue();
        myArrayQueue.removeQueue();*/


    }


}



