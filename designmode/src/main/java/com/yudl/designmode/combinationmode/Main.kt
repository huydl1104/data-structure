package com.yudl.designmode.combinationmode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 组合设计者模式 （部分整体模式）
 * 场景分析：
 *      表示对象的部分-整体层次结构时从一个整体中能够独立出部分模块或者功能的场景。
 *      如：文件夹中的文件 ，view的继承结构
 */
fun main() {

    val personInfo1 =  PersonInfo("aaaa",10000)
    val personInfo2 =  PersonInfo("bbbb",15000)
    val personInfo3 =  PersonInfo("cccc",20000)
    val personInfo4 =  PersonInfo("dddd",2000)
    val personInfo5 =  PersonInfo("eeee",11000)
    val personInfo6 =  PersonInfo("ffff",90000)
    val personInfo7 =  PersonInfo("gggg",100000)

    personInfo1.addPersonInfo(personInfo2)
    personInfo1.addPersonInfo(personInfo3)

    personInfo2.addPersonInfo(personInfo4)
    personInfo2.addPersonInfo(personInfo5)

    personInfo3.addPersonInfo(personInfo6)
    personInfo3.addPersonInfo(personInfo7)

    for (info in personInfo1.getListInfo()){
        println(info)
        for (info1 in info.getListInfo()){
            println(info1)
        }
    }

}



private class PersonInfo(var name:String,var salary:Int){

    private val list = ArrayList<PersonInfo>()

    fun addPersonInfo(info: PersonInfo){
        list.add(info)
    }

    fun removePersonInfo(info: PersonInfo){
        list.remove(info)
    }

    fun getListInfo(): ArrayList<PersonInfo> =list

    override fun toString(): String {
        return "PersonInfo(name='$name', salary=$salary, list=$list)"
    }


}

