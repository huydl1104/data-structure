package com.yudl.sturcture.binaryTree




/**
 * @author yudongliang
 * create time 2021-06-08
 * describe : 二叉树的前中后序遍历
 *    n位置(从0开始)  左节点 2*n+1，右节点 2*n+2, 父节点 (n-1)/2
 *    有空指针的个数 2*n-(n-1) = n+1 (n代表共有多少个节点)
 *          1
 *
 *      2       3
 *
 *   4    5  6     7
 */

fun main() {
    val node = createNode()
    val count = getElementCount(node)
//    preOrder(node)  // 1,2,4,5,3,6,7
//    println()
//    midOrder(node)  // 4,2,5,1,6,3,7
//    println()
//    lastOrder(node) // 4,5,2,6,7,3,1

//    println()
//    val arrayList = ArrayList<Int>(count)
//    testPreOrder(node, arrayList)
//    for (i in arrayList){
//        print("$i\t")
//    }
    println()
    val array = arrayOf(1,2,3,4,5,6,7)
    val toTree = toTree(array, 0)
    println()




}


/**
 * 线索二叉树
 */



/**
 * 将数组转换成为一棵树
 */
fun toTree(value: Array<Int>, rootNo: Int): Node? {
    return if (rootNo < value.size) { //数组标号必须小于数组长度
        val head = Node(null,value[rootNo],null)
        if (rootNo * 2 + 1 < value.size) {
            head.leftNode = toTree(value, rootNo * 2 + 1)
        }
        if (rootNo * 2 + 2 < value.size) {
            head.rightNode = toTree(value, rootNo * 2 + 2)
        }
        head
    } else {
        null
    }
}

/**
 * 将二叉树转换为数组
 */
private fun testPreOrder(node: Node?, list: ArrayList<Int>){
    if (node == null){
        return
    }
    list.add(node.value)
    testPreOrder(node.leftNode, list)
    testPreOrder(node.rightNode, list)

}

/**
 * 得到node的数量
 */
private fun getElementCount(node: Node?) :Int{
    if (node == null){
        return 0
    }
    return getElementCount(node.leftNode) + 1 + getElementCount(node.rightNode)
}

/**
 * 前序遍历
 */
private fun preOrder(node: Node?){
    if (node == null){
        return
    }
    print("${node.value},")
    preOrder(node.leftNode)
    preOrder(node.rightNode)
}

/**
 * 中序遍历
 */
private fun midOrder(node: Node?){
    if (node == null){
        return
    }
    midOrder(node.leftNode)
    print("${node.value},")
    midOrder(node.rightNode)
}

/**
 * 后序遍历
 */
private fun lastOrder(node: Node?){
    if (node == null){
        return
    }
    lastOrder(node.leftNode)
    lastOrder(node.rightNode)
    print("${node.value},")
}

/**
 * 创建一个node节点
 */
private fun createNode(): Node {
    val threeRight = Node(null, 7, null)
    val threeLeft = Node(null, 6, null)
    val twoRight = Node(null, 5, null)
    val twoLeft = Node(null, 4, null)
    val firstRight = Node(threeLeft, 3, threeRight)
    val firstLeft = Node(twoLeft, 2, twoRight)
    return Node(firstLeft, 1, firstRight)
}

data class Node(var leftNode: Node?, var value: Int, var rightNode: Node?)