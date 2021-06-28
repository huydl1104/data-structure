package com.yudl.sturcture.hafuman;

/**
 * @author yudongliang
 * create time 2021-06-10
 * describe :
 * 实现Comparable接口，使用优先队列
 *      默认的优先队列是优先级最高的先push
 *      而这里，我们需要：优先级（权重）
 *      最小的先push.
 */
public class HufNode<A> implements Comparable<HufNode>{

    private HufNode leftChild = null;   //左孩子
    private HufNode rightChild = null;  //右孩子
    private int weight = 0;             //频率/权重
    private A object = null;            //结点存储的对象，如果没有，约定为空。

    public HufNode(HufNode leftChild, HufNode rightChild, int weight, A object) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.weight = weight;
        this.object = object;
    }

    public HufNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HufNode leftChild) {
        this.leftChild = leftChild;
    }

    public HufNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(HufNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public A getObject() {
        return object;
    }

    public void setObject(A object) {
        this.object = object;
    }

    @Override
    public int compareTo(HufNode o) {
        if (this.weight < o.weight)
            return -1;
        else if (this.weight > o.weight)
            return 1;
        else
            return 0;
    }
}
