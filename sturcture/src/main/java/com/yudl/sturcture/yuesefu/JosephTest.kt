package com.yudl.sturcture.yuesefu

/**
 * @author yudongliang
 * create time 2021-05-28
 * describe : 约瑟夫环
 *      约瑟夫环（约瑟夫问题）是一个数学的应用问题：已知 n 个人（以编号1，2，3…n分别表示）围坐在一张圆桌周围。
 *  从编号为 k 的人开始报数，数到 m 的那个人出圈；他的下一个人又从 1 开始报数，数到 m 的那个人又出圈；
 *  依此规律重复下去，直到剩余最后一个胜利者。
 *  例如：有10个人围成一圈进行此游戏，每个人编号为 1-10 。若规定数到 3 的人出圈。则游戏过程如下。
 *  （1）开始报数，第一个数到 3 的人为 3 号，3 号出圈。
         1， 2， 【3】， 4， 5， 6， 7， 8， 9， 10。
    （2）从4号重新从1开始计数，则接下来数到3的人为6号，6号出圈。
         1， 2， 【3】， 4， 5， 【6】， 7， 8， 9， 10。
    （3）从7号重新从1开始计数，则接下来数到3的人为9号，9号出圈。
         1， 2， 【3】， 4， 5， 【6】， 7， 8， 【9】， 10。
    （4）从10号重新从1开始计数，由于10个人称环形结构，则接下来数到3的人为2号，2号出圈。
         1， 【2】， 【3】， 4， 5， 【6】， 7， 8， 【9】， 10。
    （5）从4号重新从1开始计数，则接下来数到3的人为7号，7号出圈。
         1， 【2】， 【3】， 4， 5， 【6】， 【7】， 8， 【9】， 10。
    （6）从8号重新从1开始计数，则接下来数到3的人为1号，1号出圈。
        【1】， 【2】， 【3】， 4， 5， 【6】， 【7】， 8， 【9】， 10。
    （7）从4号重新从1开始计数，则接下来数到3的人为8号，8号出圈。
        【1】， 【2】， 【3】， 4， 5， 【6】， 【7】， 【8】， 【9】， 10。
    （8）从10号重新从1开始计数，则接下来数到3的人为5号，5号出圈。
        【1】， 【2】， 【3】， 4， 【5】， 【6】， 【7】， 【8】， 【9】， 10。
    （9）从10号重新从1开始计数，则接下来数到3的人为10号，10号出圈。
         【1】， 【2】， 【3】， 4， 【5】， 【6】， 【7】， 【8】， 【9】， 【10】。
    （10）最终剩余 4 号，4 号为胜利者
 */
fun main() {
/*
    //数组实现约瑟夫
    val josephLoop = josephArrayLoop()
    System.out.printf("The last one is : %d \n", josephLoop);
*/
    josephLinkLoop()
}

// ------------- 链表实现约瑟夫  start---------------

class JNode(ele: Int) {
     var value :Int = ele
     var next : JNode? = null
}

fun createLinkLoopData(n: Int) : JNode {
    val head = JNode(1)
    var temp : JNode? = head
    for (index in 1 .. n){
        val node = JNode(index + 1)
        temp?.next = node
        temp = node
    }
    temp?.next = head
    return head
}

fun iterator(node: JNode?) {
    // 判断当前是否为空
    if (node == null) {
        println("没有任何小孩~")
        return
    }
    // 因为first不能动,所以我们用一个辅助指针
    var temp: JNode? = node
    while (true) {
        System.out.printf("小孩编号 %d \n", temp?.value)
        if (temp?.next === node) {
            break
        }
        temp = temp?.next
    }
}


fun josephLinkLoop(){

    val countNum = 3 // 间隔几个人报数
    var startNo = 2 //淘汰的人数
    val rootNode = createLinkLoopData(4)
    val nums = 5 // 最大的参与人数
    var first : JNode?= rootNode
    var last : JNode?= rootNode
    while (true){
        if (last?.next == rootNode){
            break
        }
        last = last?.next
    }

    for (i in 0 until startNo - 1) {
        first = first?.next
        last = last?.next
    }

    while (true) {
        if (last === first) {    // 说明环形链表中只有一个节点
            break
        }
        // 报数,第countNum删除
        // 即first和last指针同时移动(countNum - 1)次
        // 也就是移动后,first会指向需要删除的节点
        for (i in 0 until countNum - 1) {
            first = first?.next
            last = last?.next
        }
        // 移除节点
        System.out.printf("删除第%d个节点\n", first?.value)
        first = first?.next
        last?.next= first
    }
    System.out.printf("最后还在环形链表中的节点编号为%d\n", first?.value);
    println()
}

// ------------- 链表实现约瑟夫  end ---------------
//使用数组实现约瑟夫环
fun josephArrayLoop():Int{
    val n = 10 //游戏中的最大人数
    val m = 3 //间隔多少人报数
    var num = 0 // 报数器
    var count = 0 // 出局的人数
    // val intArray = IntArray(3)
    // int[] intArray = new int[6]
    // srcArray 中默认值 为 1
    val srcArray = IntArray(n){1}

    while (count < n - 1){
        for (i in 0 until n){
            if (srcArray[i] == 1){
                num ++
                if (num == m){
                    srcArray[i] = 0
                    count ++
                    num = 0
                }
                if (count == n-1){
                    break
                }
            }

        }
    }
    var res = 0
    for (i in srcArray.indices) {
        System.out.printf("data is  : %d ,value %d \n", i, srcArray[i]);
        if (srcArray[i] == 1){
            res = i
        }
    }
    return res

}