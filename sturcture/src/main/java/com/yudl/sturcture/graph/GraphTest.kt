package com.yudl.sturcture.graph

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author yudongliang
 * create time 2021-06-21
 * describe :  图的深度优先搜索， 广度优先搜索
 *
 * 深度优先搜索：深度的优先遍历，从初始点出发，初始的访问节点可能有多个临接节点，深度优先遍历的策略是 首先访问第一个临接节点，
 *          然后在以这个被访问的邻接节点作为初始节点，也就是每次都在访问完当前的节点后首先访问当前节点的第一个邻接节点
 * 广度优先搜索：类似于分层搜索的过程，广度优先遍历需要使用一个队列保持访问过的节点的顺序，以便于按照这个顺序来访问节点的邻接节点
 *
 *     1 2 3 4 5 6 7 8
 *  1  0 1 1 0 0 0 0 0
 *  2  1 0 0 1 1 0 0 0
 *  3  1 0 0 0 0 1 1 0
 *  4  0 1 0 0 0 0 0 1
 *  5  0 1 0 0 0 0 0 1
 *  6  0 0 1 0 0 0 0 0
 *  7  0 0 1 0 0 0 0 0
 *  8  0 0 0 1 1 0 0 0
 *
 */

fun main() {
//    val vertex = arrayOf("A","B","C","D","E")
    val vertex = arrayOf("1","2","3","4","5","6","7","8")
    val graph = Graph(vertex.size)

    for (value in vertex){
        graph.insertVertex(value)
    }
    //A-B,A-C,B-C,B-D,A-E
/*    graph.insertEdges(0,1,1)
    graph.insertEdges(0,2,1)
    graph.insertEdges(1,2,1)
    graph.insertEdges(1,3,1)
    graph.insertEdges(0,4,1)*/

    graph.insertEdges(0,1,1)
//    graph.insertEdges(1,0,1)
    graph.insertEdges(0,2,1)
//    graph.insertEdges(2,0,1)
    graph.insertEdges(1,3,1)
//    graph.insertEdges(3,1,1)
    graph.insertEdges(3,7,1)
//    graph.insertEdges(7,3,1)
    graph.insertEdges(7,3,1)
//    graph.insertEdges(1,4,1)
    graph.insertEdges(4,1,1)
//    graph.insertEdges(4,7,1)
    graph.insertEdges(7,4,1)
//    graph.insertEdges(2,5,1)
    graph.insertEdges(5,2,1)
//    graph.insertEdges(2,6,1)
    graph.insertEdges(2,6,1)
//    graph.insertEdges(6,2,1)


    graph.printEdges()

    println("numOfEdges->${graph.numOfEdges} , size ->${graph.isVisited.size}")
    graph.dfs()

//    graph.bfs()

}
//图的构建
class Graph(edgesSize : Int){
    //存储顶点集合
    var vertexList = ArrayList<String>()
    //存放对应的邻接矩阵
    var edges = Array(edgesSize){IntArray(edgesSize)}
    //边的数目
    var numOfEdges :Int = 0

    var isVisited = Array(edgesSize){false}

    //得到第一个邻接点的下标 从edges中查
    fun getFirstNeighbor(index: Int):Int{
        for (j in vertexList.indices){
            if (edges[index][j] > 0){
                return j
            }
        }
        return -1
    }

    //得到下一个邻接点的下标
    fun getNextNeighbor(v1: Int,v2: Int):Int{
        for (j in v2 +1 until vertexList.size){
            if (edges[v1][j] > 0){
                return j
            }
        }
        return -1
    }

    fun dfs(){
        for (i in 0 until numOfEdges){
            if (!isVisited[i]){
                dfs(isVisited,i)
            }
        }
    }

    //深度优先遍历
    fun dfs(isVisited : Array<Boolean>,i :Int){
        val value = getValueByIndex(i)
        print("$value ->")
        isVisited[i] = true
        var w = getFirstNeighbor(i)
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w)
            }
            w = getNextNeighbor(i,w)
        }

    }

    fun bfs(){
        for (i in 0 until numOfEdges){
            if (!isVisited[i]){
                bfs(isVisited,i)
            }
        }
    }

    //广度优先
    fun bfs(isVisited : Array<Boolean>,i :Int){
        var u = 0
        var w = 0
        val linkedList = LinkedList<Int>()
        print("${getValueByIndex(i)} \t")
        isVisited[i] = true
        linkedList.addLast(i)
        while (!linkedList.isEmpty()){
            u = linkedList.removeFirst()
            w = getFirstNeighbor(u)
            while (w != -1){
                if (!isVisited[w]){
                    print("${getValueByIndex(w)} \t")
                    isVisited[w] = true
                    linkedList.addLast(w)
                }
                w = getNextNeighbor(u,w)
            }
        }
    }

    fun insertVertex(vertex : String){
        vertexList.add(vertex)
    }

    //v1 : 第几个顶点  v2 ：第二个顶点对应的下标   weight : 权值
    fun insertEdges(v1:Int,v2:Int,weight: Int){
        //因为是无序的，所以
        edges[v1][v2] = weight
        edges[v2][v1] = weight
        numOfEdges++
    }

    fun getVertexSize(): Int =  vertexList.size

    fun getValueByIndex(index:Int): String = vertexList[index]

    fun getWeight(v1: Int,v2: Int):Int = edges[v1][v2]

    fun printEdges(){
        for (i in edges.indices){
            for (j in edges[i].indices){
                print("${edges[i][j]}\t")
            }
            println()
        }
    }
}