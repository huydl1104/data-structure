package com.yudl.designmode.simglemode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 单利设计模式
 * 分析：确保某个类只有一个实例，避免产生多个对象导致消耗过多的资源，这时就需要使用单例模式。
 *  如：要是需要消耗过多的资源 和访问IO数据库的操作就需要考虑使用单例
 *
 *  单例模式的有缺点
 *  优点：由于单例模式在内存中只有一个实例，减少了内存的开支。避免对象的多重占用。单例模式可以在系统中设置全局的访问点。
 *  缺点：一般没有接口，扩展比较困难，基本上都需要去修改原来的代码，单例对象持有Context很容易造成内存泄露，尽量是用 应用界别的Context。
 */
fun main() {


}


/**
 * 推荐使用静态内部类单例模式创建单例对象，
 * 双重检查判断虽然解决了资源消耗，线程的安全问题。但是还是会有失效的情况，称为双重检查锁定失效
 */
class Singleton {

    private object SingletonHolder {
        val sInstance = Singleton()
    }

    companion object {
        val instance: Singleton
            get() = SingletonHolder.sInstance
    }
}

/**
 * 枚举单利，这种形式不仅有字段也有自己的方法，默认枚举实例的创建是线程安全的，
 * 在一种情况下，会出现重新创建对象的情况。就是反序列化。
 */
enum class SingletonEnum {
    INSTANCE;
    fun doSomething() {
        println("do  sth ... ")
    }
}

/**
 * 使用容器实现单例
 */
class SingletonManager{

    companion object{
        private val map = HashMap<String, Any>()
        fun registerService(key: String, obj: Any) {
            if (!map.contains(key)) {
                map[key] = obj
            }
        }

        fun getService(key: String): Any? {
            return map[key]
        }
    }
}



