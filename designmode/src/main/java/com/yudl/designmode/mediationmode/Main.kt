package com.yudl.designmode.mediationmode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 中介者设计模式
 * 场景分析
 *      用来降低多个对象和类之间的通信复杂性，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。
 *
 *
 */
fun main() {
    //创建四个用户，通过中介者 去沟通消息
    val userA = UserA()
    val userB = UserB()
    val userC = UserC()
    val userD = UserD()
    val mediation = MediationImpl()

    mediation.addUser(userA)
    mediation.addUser(userB)
    mediation.addUser(userC)
    mediation.addUser(userD)

    mediation.setMessage(userA,userB,"hello --- ")
    mediation.setMessage(userC,userD,"world --- ")
}

interface User{
    fun showMessage(msg:String)
}

class UserA :User{
    override fun showMessage(msg: String) {
        print("A $msg\n")
    }
}

class UserB :User{
    override fun showMessage(msg: String) {
        print("B $msg\n")
    }
}
class UserC :User{
    override fun showMessage(msg: String) {
        print("C $msg\n")
    }
}
class UserD :User{
    override fun showMessage(msg: String) {
        print("D $msg\n")
    }
}

interface Mediation{
    fun setMessage(from:User,to:User,msg: String)
    fun addUser(user: User)
}

class MediationImpl : Mediation{

    private val list = HashSet<User>()

    override fun setMessage(from: User, to: User, msg: String) {
        from.showMessage("发送"+msg+"成功！");
        to.showMessage("接收"+msg+"成功！");
    }

    override fun addUser(user: User) {
        list.add(user)
    }

}

