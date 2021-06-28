package com.yudl.sturcture.hafuman


/**
 * @author yudongliang
 * create time 2021-06-04
 * describe : 哈夫曼的编码和解码
 *  思路： 首先将字符串转化为字节数组
 *        创建哈夫曼树，将值和权重写入
 *        根据叶子结点的权重来计算哈夫曼编码表
 *        根据哈夫曼编码表来计算哈夫曼编码
 *        最后再转化为字节数组
 * example:
 *  i like like like java do you like a java
 *  d:1 y:1 u:1 j:2  v:2  o:2  l:4  k:4  e:4 i:5  a:5   :9  // 各个字符对应的个数
 */
fun main() {
    val string = "i like like like java do you like a java"
}





/** 实现 Comparable 接口比较两个node的大小 */
class HaFuManNode (var weight: Int) :Comparable<HaFuManNode>{
    var data:Byte?= null
    var leftNode: HaFuManNode?= null
    var rightNode: HaFuManNode?= null
    override fun compareTo(other: HaFuManNode): Int {
        return this.weight - other.weight
    }
}
