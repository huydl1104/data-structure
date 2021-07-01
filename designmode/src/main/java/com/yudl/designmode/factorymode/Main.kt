package com.yudl.designmode.factorymode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 工厂设计模式
 * 分析：定义一个用于创建对象的接口，让子类去决定实例化那个类。
 * 使用场景：任何需要创建对象的地方都可以使用工厂的方法。
 */
fun main() {
    val factory = CarFactoryImpl()
    val createCar = factory.createCar(CarImpl1::class.java)
    createCar.selfGuide()
}


abstract class CarFactory {
    abstract fun <T : Car?> createCar(clazz: Class<T>): T
}

class CarFactoryImpl : CarFactory() {

    override fun <T : Car?> createCar(clazz: Class<T>): T {
        var car: Car? = null
        try {
            car = Class.forName(clazz.name).newInstance() as Car
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return car as T
    }

}


abstract class Car {
    abstract fun drive()
    abstract fun selfGuide()
}

class CarImpl1 : Car() {
    override fun drive() {
        println("car1  drive -- ")
    }

    override fun selfGuide() {
        println("car1  selfGuide -- ")
    }
}



