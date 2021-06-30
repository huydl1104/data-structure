package com.yudl.sturcture.binarySearchTree


/**
 * @author yudongliang
 * create time 2021-06-15
 * describe : 二叉搜索树 遵循 根节点value > 跟节点left.value && 根节点value < 跟节点right.value
 *              10
 *          8         15
 *       5     9  12      20
 *          6
 *
 *  思路 ：1、创建二叉树，使用递归的方式去 比较 插入的值和 节点的值 大小
 *        2、 删除节点的时候需要考虑三种情况
 *              （1）删除的是叶子节点
 *              （2）删除的是双子树节点
 *              （3）删除的是单子树节点，分为左子树、右子树
 */
fun main() {
    val arr = arrayOf(10,8,5,9,15,20,12,6)
    val tree = Tree()
    for (value in arr){
        tree.addNewNode(TreeNode(value))
    }
    tree.deleteNode(10)
    tree.infixOrder()
}

class Tree{

    var rootNode: TreeNode?= null

    fun addNewNode(node: TreeNode?){
        if (rootNode == null){
            rootNode = node
        }else{
            rootNode!!.addNode(node)
        }
    }

    fun infixOrder(){
        if (rootNode == null){
            println("rootNode is empty")
            return
        }
        rootNode!!.infixOrder()
    }

    fun searchNode(data: Int): TreeNode?{
       return rootNode?.searchNode(data)
    }

    fun searchTargetNode(data: Int): TreeNode?{
        return rootNode?.searchTargetNode(data)
    }

    fun searchParent(data: Int): TreeNode?{
        return rootNode?.searchParent(data)
    }
    //删除右侧树的最小值
    fun deleteRightTreeMin(node: TreeNode?): Int {
        if (node == null){
            return 0
        }
        var currentNode = node
        while (currentNode?.leftNode != null){
            currentNode = currentNode.leftNode
        }
        deleteNode(currentNode?.data!!)
        return currentNode.data
    }

    //删除二叉搜索树的节点
    fun deleteNode(data: Int){
        if (rootNode == null){
            return
        }
        //找到需要删除的节点
        val targetNode = searchTargetNode(data) ?: return
        //校验是否是只有一个节点
        if (rootNode?.leftNode == null && rootNode?.rightNode == null){
            return
        }
        //找出父节点
        val parentNode = searchParent(data)
        //先判断是不是叶子节点
        if (targetNode.leftNode == null && targetNode.rightNode == null){
            //接着去是 父节点的 左子树还是 右子树
            if (parentNode != null && parentNode.leftNode?.data == data){ //说明是在父节点的左子树
                parentNode.leftNode = null // 直接为空
            }else if (parentNode != null && parentNode.rightNode?.data == data){
                parentNode.rightNode = null
            }
        }else if (parentNode?.leftNode == null && parentNode?.rightNode == null){//有两棵子树
            val deleteValue = deleteRightTreeMin(targetNode.rightNode)
            targetNode.data = deleteValue
        }else{//只有一个子树
            if (targetNode.leftNode != null){//只有一个左子树
                if (parentNode != null){
                    if (parentNode.leftNode?.data == data){
                        parentNode.leftNode = targetNode.leftNode
                    }else{
                        parentNode.rightNode = targetNode.leftNode
                    }
                }else{
                    rootNode = targetNode.leftNode
                }

            }else{//只有一个右子树
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
}

class TreeNode(var data:Int){
    var leftNode : TreeNode?= null
    var rightNode : TreeNode?= null

    fun infixOrder(){
        if (leftNode != null){
            leftNode!!.infixOrder()
        }
        print("${this.data}\t")
        if (rightNode != null){
            rightNode!!.infixOrder()
        }
    }
    //循环的方式查找
    fun searchNode(data: Int): TreeNode?{ //12
        var currentNode: TreeNode?= this
        while (currentNode != null){
            currentNode = when {
                currentNode.data == data -> {
                    return currentNode
                }
                currentNode.data < data -> {
                    currentNode.rightNode
                }
                else -> {
                    currentNode.leftNode
                }
            }
        }

        return currentNode
    }

    //递归的方式查找
    fun searchTargetNode(data: Int): TreeNode?{
        return when {
            this.data == data -> {
                return this
            }
            this.data < data -> {
                if (this.rightNode == null){
                    return null
                }
                this.rightNode?.searchTargetNode(data)
            }
            else -> {
                if (this.leftNode == null){
                    return null
                }
                this.leftNode?.searchTargetNode(data)
            }
        }
    }

    //根据具体的值查找对应的 node 节点的父节点
    fun searchParent(data: Int): TreeNode?{
        return if ((this.leftNode != null && this.leftNode!!.data == data)
            || (this.rightNode != null && this.rightNode!!.data == data)){
            this
        }else {// 左右的node.data != data
            if (this.leftNode != null && data < this.data){
                this.leftNode!!.searchParent(data)
            }else if (this.rightNode != null && data > this.data){
                this.rightNode!!.searchParent(data)
            }else{
                null
            }
        }

    }

    fun addNode(node: TreeNode?){
        if (node == null){
            return
        }
        if (this.data < node.data){//右侧
            if (this.rightNode == null){
                this.rightNode = node
            }else{
                this.rightNode?.addNode(node)
            }
        }else{//左侧
            if (this.leftNode == null){
                this.leftNode = node
            }else{
                this.leftNode?.addNode(node)
            }
        }
    }
}