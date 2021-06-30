/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 单链表的反转问题
 * 两种解决方案
 * 1、使用循环的方式，
 * 2、使用递归的方式，
 */
fun main() {
    val nodes = getNodes()
    val reverseNode = linkReverse1(nodes)
    println(reverseNode)


}

/**
 * 使用递归的方式去实现
 */
private fun linkReverse1(head: Node?) :Node?{
    if (head?.next == null){
        return head
    }
    val newNode = linkReverse1(head.next)// head 4
    head.next?.next = head
    head.next = null
    return newNode

}

/**
 * 使用循环的方式实现
 *
 */
private fun linkReverse(head: Node): Node? {
    var preNode: Node?= null
    var nextNode: Node?= null
    var currentnode: Node?= head
    while (currentnode != null){
        nextNode = currentnode.next
        currentnode.next = preNode
        preNode = currentnode
        currentnode = nextNode
    }
    return preNode
}


private fun getNodes():Node{
    val node5 =  Node(5)
    val node4 =  Node(4,node5)
    val node3 =  Node(3,node4)
    val node2 =  Node(2,node3)
    return Node(1,node2)
}

private class Node(var data:Int, var next:Node?=null)