package com.example.leetcode.link

import java.util.LinkedHashSet

/**
 * @author yudongliang
 * create time 2021-07-06
 * describe : 环形链表
 * 题目：给定一个链表，判断链表中是否有环，有环返回true，否则false
 */
fun main() {
    val nodes = getRingNodes()
    val ring = isRing(nodes)
    val ring1 = isRing2(nodes)
    println(ring)
    println(ring1)
}

//1、采用hashSet，在往set中添加数据时，判断是否成功。
private fun isRing(node: Node): Boolean {
    val hashSet = LinkedHashSet<Int>()
    var head : Node?= node
    while (head?.next!=null){
        if (!hashSet.add(head.data)){
            return true
        }
        head = head.next
    }
    return false
}

//2、快慢指针
private fun isRing2(node: Node): Boolean {
    if (node.next == null){
        return false
    }
    var low :Node? = node
    var fast = node.next
    while (low != fast){
        if (fast?.next == null){
            return false
        }
        low = fast.next
        fast = fast.next?.next
    }
    return true
}


private fun getRingNodes():Node{
    val node5 =  Node(5)
    val node4 =  Node(4,node5)
    val node3 =  Node(3,node4)
    val node2 =  Node(2,node3)
//    node5.next = node3
    return Node(1,node2)
}

private class Node(var data:Int, var next:Node?=null)