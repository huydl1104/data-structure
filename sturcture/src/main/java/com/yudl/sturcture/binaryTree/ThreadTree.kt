package com.yudl.sturcture.binaryTree

/**
 * @author yudongliang
 * create time 2021-06-08
 * describe : 线索二叉树， 实际上也就是转换为了 双向链表 ，利用了二叉树中多余的指针，指向对应的前驱和后继
 *    n位置(从0开始)  左节点 2*n+1，右节点 2*n+2, 父节点 (n-1)/2
 *    有空指针的个数 2*n-(n-1) = n+1 (n代表共有多少个节点)
 * 使用场景 ：在使用二叉树过程中经常需要遍历二叉树或者查找节点的前驱节点和后继节点，可考虑采用线索二叉树存储结构
 *              A
 *         B        C
 *      D    E   F     G
 *   H    I
 *  中序线索化的过程
 *  null <-- H <--> D <--> I <--> B <--> E <--> A <--> F <--> C <--> G -->null
 */
fun main() {
    val arr = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I")
    val node = createThreadNodeByArray(arr,0) //从第一个位置开始
    threadNext(node)
    listTree(node)
}

var preNode :ThreadNode?= null

/**
 * 给一个数组创建一个完全二叉树
 */
private fun createThreadNodeByArray(array: Array<String>, index: Int):ThreadNode?{
    //若是index >= array.size 跳出递归
    var node :ThreadNode?= null
    if (index < array.size){
        node = ThreadNode(array[index])
        node.left = createThreadNodeByArray(array,2*index+1)
        node.right = createThreadNodeByArray(array,2*index+2)
    }
    return node
}


/**
 * 中序遍历，增加后继指针
 */
private fun threadNext(node: ThreadNode?){
    if (node == null){
        return
    }
    //从最左的叶子节点
    threadNext(node.left)

    //将左指针指向前驱的节点
    if (node.left == null){
        node.left = preNode
        node.isLeftState = true
    }

    if (preNode != null && preNode!!.right == null ){
        preNode!!.right = node
        preNode!!.isRightState = true
    }

    preNode = node

    threadNext(node.right)

}

/**
 * 通过后继节点 中序遍历二叉树
 */
private fun listTree(srcNode: ThreadNode?){
    if (srcNode == null){
        return
    }
    //先将指针移到最左侧的节点
    var node = srcNode
    //第二个条件 是因为在添加线索，找到最左侧的节点开始 添加后继指针
    while (node != null && !node.isLeftState){
        node = node.left
    }

    while (node != null){
        //打印出当前node的data
        print("${node.data} \t")
        if (node.isRightState){
            node = node.right
        }else{
            //若右子树不是开始的节点，那么就需要找到node右侧开始的node
            node = node.right
            //找到右子树中最左侧的节点
            while (node != null && !node.isLeftState){
                node = node.left
            }
        }

    }

}


/**
 * 线索二叉树的节点
 */
data class ThreadNode(var data:String){
    var left:ThreadNode?= null
    var right:ThreadNode?= null
    //是否使用前驱、后继的状态
    var isLeftState : Boolean = false
    var isRightState : Boolean = false
}
