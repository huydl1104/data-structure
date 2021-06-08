package com.yudl.sturcture.queue

/**
 * @author yudongliang
 * create time 2021-05-27
 * describe : 双向链表实现队列
 */
fun main() {


    val doubleLinkQueue = DoubleLinkQueue(arrayListOf(12, 13, 14, 15, 19, 20, 40, 50))

//    doubleLinkQueue.addData(60)
//    doubleLinkQueue.addFirst(4)
//    doubleLinkQueue.unLinkFirst()
//    doubleLinkQueue.unLinkLast()
    doubleLinkQueue.addData(5, 90)
//    doubleLinkQueue.node(5)
}

class Node<E> internal constructor(
        var prev: Node<E>?,
        var item: Any?,
        var next: Node<E>?
)

class DoubleLinkQueue<E>(list: MutableCollection<E>) {
    @Transient
    var size = 0

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     * (first.prev == null && first.item != null)
     */
    @Transient
    var first: Node<E>? = null

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    @Transient
    var last: Node<E>? = null

    init {
        addAll(size, list)
    }

    private fun checkIndexPosition(index: Int): Boolean{
        return index in 0..size
    }
    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $size"
    }
    private fun addAll(index: Int, list: MutableCollection<out E>) {
        if (!checkIndexPosition(index)) {
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
        }
        if (list.isEmpty()){
            return
        }
        var pre: Node<E>?
        val suc: Node<E>?
        if (index == size){
            pre = last
            suc = null
        }else{
            suc = node(index)
            pre = suc?.prev
        }
        list.forEach {
            val newNode = Node(pre, it, null)
            if (pre == null){
                first = newNode
            }else{
                pre?.next = newNode
            }
            pre = newNode
        }

        if (suc == null) {
            last = pre
        } else {
            pre?.next = suc
            suc.prev = pre
        }

        size += list.size

    }

    fun addData(ele: E){
        addLast(ele)
        listIterator()
    }

    fun addFirst(ele: E){
        val head = first
        val newNode = Node(null, ele, head)
        first = newNode
        if (head == null){
            last = newNode
        }else{
            head.prev = newNode
        }
        size++
        listIterator()
    }

    fun unLinkFirst(){
        val firstNode = first
        val next = first?.next
        firstNode?.item = null
        firstNode?.next = null
        first = next
        if (next == null){
            last = null
        }else {
            next.prev = null
        }
        size--
        listIterator()
    }

    fun unLinkLast(){
        println(last?.item)
        val lastNode = last
        val pre = last?.prev
        lastNode?.item = null
        lastNode?.prev = null
        last = pre
        if (pre == null){
            first = null
        }else {
            pre.next = null
        }
        size--
        listIterator()
    }

    private fun addLast(ele: E){
        val lastNode = last
        val newNode = Node(lastNode, ele, null)
        last = newNode
        if (lastNode == null){
            first = newNode
        }else {
            lastNode.next = newNode
        }
        size++
    }

    fun addData(index: Int, ele: E){
        checkIndexPosition(index)
        if (index == size){
            addLast(ele)
        }else{
            addBefore(node(index), ele)
        }
        listIterator()
    }

    private fun addBefore(node: Node<E>?, ele: E) {
        println("addBefore node ->${node?.item}")
        val pre = node?.prev
        val newNode = Node(pre, ele, node)
        node?.prev = newNode
        if (pre == null){
            first = node
        }else{
            pre.next = newNode
        }
        size++
    }

    fun clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        var x: Node<E>? = first
        while (x != null) {
            val next: Node<E>? = x.next
            x.item = null
            x.next = null
            x.prev = null
            x = next
        }
        last = null
        first = last
        size = 0
    }

    /* 得到当前index 的 Node */
    fun node(index: Int): Node<E>?{
        var temp : Node<E>?
        if (index > size shr 1){ //向右移动1位
            temp  = last
            for (i in size - 1 downTo index + 1){
//                println("node i ->$i  , temp ->${temp?.item}")
                temp = temp?.prev
            }

        }else{
            temp = first
            for (i in 0 until index step 1){

                temp = temp?.next
            }
        }
        println("node index ->$index  , temp ->${temp?.item}")
        listIterator()
        return temp
    }

    fun node12(index: Int): Node<E>? {
        // assert isElementIndex(index);
        return if (index < size shr 1) {
            var x: Node<E>? = first
            for (i in 0 until index) x = x?.next
            x
        } else {
            var x: Node<E>? = last
            for (i in size - 1 downTo index + 1) x = x?.prev
            x
        }
    }


    private fun listIterator() {
        var node: Node<E>? = first
        while (node != null) {
            System.out.printf("元素数据 %d \t", node.item)
            node = node.next
        }
        println()
    }

}


