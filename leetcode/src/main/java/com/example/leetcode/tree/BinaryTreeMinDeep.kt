package com.example.leetcode.tree

import android.sax.RootElement
import java.util.*

/**
 * @author yudongliang
 * create time 2021-07-07
 * describe : 二叉树的最小深度
 * 题目：给定一个二叉树，找出树的最小深度
 * 最小深度是以根节点到最近叶子节点的最短路径上的节点数量。
 * 考察深度优先，广度优先
 *
 *          10
 *       8      12
 *    7    9  11  13
 *  6
 *
 */
fun main() {
    val nodes = getNodes()
    val minDeep = minDeep(nodes)
    val minDeep1 = minDeep1(nodes)
    println(minDeep)
    println(minDeep1)

}

//比较节点的两个子树的 深度 取最小 (深度优先)
private fun minDeep(rootNode: Node?) :Int{
    if (rootNode == null){
        return 0
    }
    if (rootNode.left == null && rootNode.right == null){
        return 1
    }
    var min = Integer.MAX_VALUE
    if (rootNode.left != null){
        min = minDeep(rootNode.left).coerceAtMost(min)
    }
    if (rootNode.right != null){
        min = minDeep(rootNode.right).coerceAtMost(min)
    }
    return min + 1
}

//使用队列 循环 （广度优先）
private fun minDeep1(rootNode: Node?): Int {
    if (rootNode == null){
        return 0
    }
    val linkedList = LinkedList<Node>()
    rootNode.deep = 1
    linkedList.add(rootNode)
    while (linkedList.size != 0){
        val node = linkedList.poll() ?: break
        if (node.left == null && node.right == null){
            return node.deep
        }
        if (node.left != null){
            node.left!!.deep = node.deep + 1
            linkedList.offer(node.left)
        }
        if (node.right != null){
            node.right!!.deep = node.deep + 1
            linkedList.offer(node.right)
        }
    }
    return 0
}
private fun getNodes():Node{
    val node8 =  Node(data = 6)
    val node7 =  Node(data = 13)
    val node6 =  Node(data = 11)
    val node5 =  Node(data = 9)
    val node4 =  Node(left = node8,data = 7)
    val node3 =  Node(/*node6,*/data = 12)
    val node2 =  Node(node4,8,node5)
    return Node(node2,10,node3)
}

private class Node(var left:Node?=null,var data:Int,var right:Node?=null,var deep:Int = 0)