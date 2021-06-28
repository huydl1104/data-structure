package com.example.testapplication.suanfa.binarySearchTree

import kotlin.math.max

/**
 * @author yudongliang
 * create time 2021-06-16
 * describe : 平衡二叉树
 * 左旋的过程
 *          2
 *        1   4
 *          3   5
 *                6
 *
 *          4
 *       2     5
 *     1   3      6
 *  ------------------------------
 *  左旋的过程
 *            6
 *        4       7
 *     3     5
 *  1
 *
 *         4
 *      3     6
 *   1     5     7
 *
 */
fun main() {
    //测试左旋
/*   val node = BalanceNode(2)
   val nodeleft = BalanceNode(1)
   val noderight = BalanceNode(4)
    node.leftNode = nodeleft
    node.rightNode = noderight

    val nodeleft1 = BalanceNode(3)
    val noderight1 = BalanceNode(5)
    noderight.leftNode = nodeleft1
    noderight.rightNode = noderight1

    val noderight2 = BalanceNode(6)
    noderight1.rightNode = noderight2

    node.leftRotate()
    println("2->right :${node.rightNode?.data}")

    midOrder(node)
    println()*/

    //测试右旋

    val node = BalanceNode(6)
    val node4 = BalanceNode(4)
    val node5 = BalanceNode(5)
    val node7 = BalanceNode(7)
    val node3 = BalanceNode(3)
    val node1 = BalanceNode(1)
    node.leftNode = node4
    node4.rightNode = node5

    node.rightNode = node7
    node4.leftNode = node3
    node3.leftNode = node1
    node.rightRotate()
    midOrder(node)
    println()
}


fun midOrder(node: BalanceNode?){
    if (node == null){
        return
    }
    midOrder(node.leftNode)
    print("${node.data},")
    midOrder(node.rightNode)
}


class BalancedTree{
    var rootNode :BalanceNode? = null

    /**
     * 1、叶子节点
     * 2、节点有一个子树
     * 3、节点有两个子树
     */
    fun deleteNode(data: Int){
        if (rootNode == null){
            return
        }
        val targetNode = searchTargetNode(data) ?: return
        //解决只有一个节点的情况，1、和删除的节点相等就删除 2、不想等不用之行往后的代码 return
        if (rootNode?.leftNode == null && rootNode?.rightNode == null){
            if (rootNode?.data == targetNode.data){
                rootNode = null
            }
            return
        }
        val parentNode = searchParent(data)
        //处理叶子节点的情况
        if (targetNode.leftNode == null && targetNode.rightNode == null){
            if (parentNode != null && parentNode.leftNode?.data == data){
                parentNode.leftNode = null
            }else if (parentNode != null && parentNode.rightNode?.data == data){
                parentNode.rightNode = null
            }
        }else if (targetNode.leftNode != null && targetNode.rightNode != null){
            val rightMinValue = deleteRightTreeMin(targetNode.rightNode)
            targetNode.data = rightMinValue
        }else{
            if (targetNode.leftNode != null){//左
                if (parentNode != null){
                    if (parentNode.leftNode?.data == data){
                        parentNode.leftNode = targetNode.leftNode
                    }else{
                        parentNode.rightNode = targetNode.leftNode
                    }
                }else{
                    rootNode = targetNode.leftNode
                }
            }else{//右
                if (parentNode != null){
                    if (parentNode.leftNode?.data == data){
                        parentNode.leftNode = targetNode.rightNode
                    }else{
                        parentNode.rightNode = targetNode.rightNode
                    }
                }else{
                    rootNode = targetNode.rightNode
                }
            }
        }


    }

    fun deleteRightTreeMin(node: BalanceNode?): Int {
        if (node == null) return 0
        var currentNode = node
        while (currentNode?.leftNode != null){
            currentNode = currentNode.leftNode
        }
        deleteNode(node.data)
        return currentNode?.data!!
    }

    fun addNode(data: Int){
        rootNode?.addNode(BalanceNode(data))
    }

    fun searchParent(data: Int):BalanceNode?{
        return rootNode?.searchParent(data)
    }

    fun searchTargetNode(data: Int):BalanceNode?{
        return rootNode?.searchTargetNode(data)
    }

}



class BalanceNode(var data :Int){
    var leftNode : BalanceNode?= null
    var rightNode : BalanceNode?= null

    fun leftHeight():Int{
        return leftNode?.leftHeight() ?: 0
    }

    fun rightHeight():Int{
        return rightNode?.rightHeight()?:0
    }

    fun height():Int{
        return max(leftNode?.height()?:0, rightNode?.height()?:0) + 1
    }



    fun leftRotate(){
        //以当前节点创建一个新的节点
        val newNode = BalanceNode(data)
        newNode.leftNode = this.leftNode
        newNode.rightNode = this.rightNode?.leftNode

        this.data = this.rightNode?.data ?: -1
        this.rightNode = this.rightNode?.rightNode
        this.leftNode = newNode
    }

    fun rightRotate(){
        val newNode = BalanceNode(data)
        newNode.rightNode = this.rightNode
        newNode.leftNode = this.leftNode?.rightNode

        this.data = this.leftNode?.data ?: -1
        this.leftNode = this.leftNode?.leftNode
        this.rightNode = newNode
    }

     fun searchParent(data: Int):BalanceNode?{
        return if ((this.leftNode != null && this.leftNode!!.data == data) ||
            (this.rightNode != null && this.rightNode!!.data == data)){
            this
        }else{
            if (data < this.data && this.leftNode != null){
                this.leftNode?.searchParent(data)
            }else if (data > this.data && this.rightNode != null){
                this.rightNode?.searchParent(data)
            }else{
                null
            }
        }

    }

     fun searchTargetNode(data:Int):BalanceNode?{
        when {
            data == this.data -> {
                return this
            }
            data < this.data -> {
                if (this.leftNode == null){
                    return null
                }
                return this.leftNode?.searchTargetNode(data)
            }
            else -> {
                if (this.rightNode == null){
                    return null
                }
                return this.rightNode?.searchTargetNode(data)
            }
        }
    }

    /**
     *  在左旋的过程中 需要 先右旋 再左旋
     *                10
     *            5       15
     *                13
     *                    14
     *
     */

    fun addNode(node: BalanceNode?){
        if (node == null){
            return
        }
        if (node.data < this.data){//此处不处理相等的情况
            if (this.leftNode == null){
                this.leftNode = node
            }else{
                this.leftNode?.addNode(node)
            }
        }else{
            if (this.rightNode == null){
                this.rightNode = node
            }else{
                this.rightNode?.addNode(node)
            }
        }

        if (rightHeight() - leftHeight() > 1){//左旋
            if ((rightNode?.leftHeight()!! - rightNode?.rightHeight()!!) > 1){
                rightNode?.rightRotate()
            }
            leftRotate()
            return
        }

        if (leftHeight() - rightHeight() > 1){//右旋
            if ((leftNode?.rightHeight()!! - leftNode?.leftHeight()!!) > 1){
                leftNode?.leftRotate()
            }
            rightRotate()
        }

    }
}


