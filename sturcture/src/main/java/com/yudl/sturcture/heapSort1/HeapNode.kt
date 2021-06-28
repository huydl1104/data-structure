package com.yudl.sturcture.heapSort1

/**
 * @author yudongliang
 * create time 2021-06-09
 * describe : 重写compareTo，比较两个node 的大小
 */
class HeapNode(var data: Int) : Comparable<HeapNode> {
    var leftNode: HeapNode? = null
    var rightNode: HeapNode? = null
    override fun compareTo(other: HeapNode): Int {
        //从小到大的顺序
        return data - other.data
    }

    override fun toString(): String {
        return this.data.toString()
    }
}