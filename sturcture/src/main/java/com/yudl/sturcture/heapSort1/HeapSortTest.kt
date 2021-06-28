package com.yudl.sturcture.heapSort1

import kotlin.collections.ArrayList

/**
 * @author yudongliang
 * create time 2021-06-09
 * describe : 对数组进行堆排序
 */
fun main() {
    val arr = arrayOf(13,7,8,3,29,6,1)
//    hafumanSort(arr)

}


//哈夫曼树
fun hafumanSort(arr: Array<Int>) {
    //首先把数组包装为 heapNode 节点再进行排序
    val arrayList = ArrayList<HeapNode>(arr.size)
    for (value in arr){
        arrayList.add(HeapNode(value))
    }

    arrayList.sort()
    println(arrayList)
    while (arrayList.size > 1){
        //取出数组中的第一个，第二个数，想加 得到父节点的权重
        val firstNode = arrayList[0]
        val twoNode = arrayList[1]
        //创建父节点
        val parentNode = HeapNode(firstNode.data + twoNode.data)
        parentNode.leftNode = firstNode
        parentNode.rightNode = twoNode
        arrayList.remove(firstNode)
        arrayList.remove(twoNode)
        arrayList.add(parentNode)
    }

    customPrint(arrayList[0])

}

private fun customPrint(node : HeapNode?){
    if (node == null){
        return
    }
    print("${node.data}\t")
    if (node.leftNode != null){
        customPrint(node.leftNode)
    }
    if (node.rightNode != null){
        customPrint(node.rightNode)
    }
}

