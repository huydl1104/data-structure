package com.yudl.sturcture.binaryTree

/**
 * @author yudongliang
 * create time 2021-06-08
 * describe : 线索二叉树
 * 使用场景 ：在使用二叉树过程中经常需要遍历二叉树或者查找节点的前驱节点和后继节点，可考虑采用线索二叉树存储结构
 *              A
 *         B        C
 *      D    E   F     G
 *   H    I
 *  中序线索化的过程
 *  null <-- H <--> D <--> I <--> B <--> E <--> A <--> F <--> C <--> G -->null
 */
class ThreadBinaryTree1 {
    private var preNode: Node? = null //线索化时记录前一个节点

    //节点存储结构
    class Node(var data: String) {
        var left: Node? = null //左指针域
        var right: Node? = null //右指针域
        var isLeftThread = false //左指针域类型  false：指向子节点、true：前驱或后继线索
        var isRightThread = false //右指针域类型  false：指向子节点、true：前驱或后继线索
    }

    /**
     *  中序线索化二叉树
     *              A
     *         B        C
     *      D    E   F     G
     *   H    I
     *  中序线索化的过程
     *  null <-- H <--> D <--> I <--> B <--> E <--> A <--> F <--> C <--> G -->null
     *
     */
    fun inThreadOrder(node: Node?) {
        if (node == null) {
            return
        }

        //处理左子树
        inThreadOrder(node.left)

        println("inThreadOrder value->${node.data} ,preNode ->${preNode?.data}")

        //左指针为空,将左指针指向前驱节点
        if (node.left == null) {
            node.left = preNode
            node.isLeftThread = true
        }

        //前一个节点的后继节点指向当前节点
        if (preNode != null && preNode!!.right == null) {
            preNode!!.right = node
            preNode!!.isRightThread = true
        }
        preNode = node

        //处理右子树
        inThreadOrder(node.right)
    }



    /**
     * 中序遍历线索二叉树，按照后继方式遍历（思路：找到最左子节点开始）
     * @param srcNode
     */
    fun inThreadList(srcNode: Node?) {
        //1、找中序遍历方式开始的节点
        var node = srcNode
        while (node != null && !node.isLeftThread) {
            node = node.left
        }
//        println("inThreadList node->${node?.data}")
        while (node != null) {
            print(node.data + ", ")
            //如果右指针是线索
            if (node.isRightThread) {
                node = node.right
            } else {    //如果右指针不是线索，找到右子树开始的节点
                node = node.right
                while (node != null && !node.isLeftThread) {
                    node = node.left
                }
            }
        }
    }

    /**
     * 中序遍历线索二叉树，按照前驱方式遍历（思路：找到最右子节点开始倒序遍历）
     * @param srcNode
     */
    fun inPreThreadList(srcNode: Node?) {
        //1、找最后一个节点
        var node = srcNode
        while (node!!.right != null && !node.isRightThread) {
            node = node.right
        }
        while (node != null) {
            print(node.data + ", ")

            //如果左指针是线索
            if (node.isLeftThread) {
                node = node.left
            } else {    //如果左指针不是线索，找到左子树开始的节点
                node = node.left
                while (node!!.right != null && !node.isRightThread) {
                    node = node.right
                }
            }
        }
    }

    /**
     * 前序线索化二叉树
     * @param node
     */
    fun preThreadOrder(node: Node?) {
        if (node == null) {
            return
        }

        //左指针为空,将左指针指向前驱节点
        if (node.left == null) {
            node.left = preNode
            node.isLeftThread = true
        }

        //前一个节点的后继节点指向当前节点
        if (preNode != null && preNode!!.right == null) {
            preNode!!.right = node
            preNode!!.isRightThread = true
        }
        preNode = node

        //处理左子树
        if (!node.isLeftThread) {
            preThreadOrder(node.left)
        }

        //处理右子树
        if (!node.isRightThread) {
            preThreadOrder(node.right)
        }
    }

    /**
     * 前序遍历线索二叉树（按照后继线索遍历）
     * @param node
     */
    fun preThreadList(srcNode: Node?) {
        var node = srcNode
        while (node != null) {
            while (!node!!.isLeftThread) {
                print(node.data + ", ")
                node = node.left
            }
            print(node.data + ", ")
            node = node.right
        }
    }

    companion object {
        /**
         * 通过数组构造一个二叉树（完全二叉树）
         * @param array
         * @param index
         * @return
         */
        private fun createBinaryTree(array: Array<String>, index: Int): Node? {
            var node: Node? = null
            if (index < array.size) {
                node = Node(array[index])
                node.left = createBinaryTree(array, index * 2 + 1)
                node.right = createBinaryTree(array, index * 2 + 2)
            }
            return node
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val array = arrayOf("A", "B", "C", "D", "E", "F", "G", "H","I")
            val root = createBinaryTree(array, 0)
            val tree = ThreadBinaryTree1()
            tree.inThreadOrder(root)
            println("中序按后继节点遍历线索二叉树结果：")
            tree.inThreadList(root)
            println("\n中序按后继节点遍历线索二叉树结果：")
            tree.inPreThreadList(root)
            val root2 = createBinaryTree(array, 0)
            val tree2 = ThreadBinaryTree1()
            tree2.preThreadOrder(root2)
            tree2.preNode = null
            println("\n前序按后继节点遍历线索二叉树结果：")
            tree.preThreadList(root2)
        }
    }
}