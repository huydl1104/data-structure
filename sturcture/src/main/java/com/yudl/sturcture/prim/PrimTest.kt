package com.yudl.sturcture.prim

import java.util.*
import kotlin.math.min

/**
 * @Author: ydl
 * @Description: 普瑞姆算法
 * @CreateDate: 2021-06-28
 */
fun main() {
    val data = arrayOf('A','B','C','D','E','F','G')
    var verxs = data.size
    val weight = arrayOf(
        intArrayOf(10000, 5, 7, 10000, 10000, 10000, 2),
        intArrayOf(5, 10000, 10000, 9, 10000, 10000, 3),
        intArrayOf(7, 10000, 10000, 10000, 8, 10000, 10000),
        intArrayOf(10000, 9, 10000, 10000, 10000, 4, 10000),
        intArrayOf(10000, 10000, 8, 10000, 10000, 5, 4),
        intArrayOf(10000, 10000, 10000, 4, 5, 10000, 6),
        intArrayOf(2, 3, 10000, 10000, 4, 6, 10000)
    )
    val graph = MGraph(verxs)
    val minTree = MinTree()
    minTree.createGraph(graph,verxs,data,weight)
    minTree.showGraph(graph)
}

//创建最小生成树 --- >村庄的图
class MinTree {

    /**
     * graph：图对象
     * verxs：图对应的顶点个数
     * data：图的各个顶点的值
     * weight：图的邻接矩阵
     */
    fun createGraph(graph: MGraph,verxs:Int,data:Array<Char>,weight: Array<IntArray>){
        for (i in 0 until verxs){
            graph.data[i] = data[i]
            for (j in 0 until verxs){
                graph.weight[i][j] = weight[i][j]
            }
        }
    }

    fun showGraph(graph: MGraph){
        for (arr in graph.weight){
            print(arr.contentToString())
        }
        println()
    }

}


class MGraph(vertexSize:Int,
             var data:CharArray = CharArray(vertexSize),
             var weight:Array<IntArray> =  Array(vertexSize) { IntArray(vertexSize)})
