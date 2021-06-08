package com.yudl.sturcture.binarysearch

/**
 * @author yudongliang
 * create time 2021-06-04
 * describe : 二分查找
 */
fun main() {

    //第一中方案 从数组中找到想等的就返回对应的角标
//    val arr = createArr()
//    val binarySearch = binarySearch(arr, 0, arr.size - 1, 4)
//    println("binarySearch->$binarySearch")
//    println(count)

    //第二中方案  找出数组中所有相等数据的角标
    val arr = arrayOf(1,3,5,6,7,7,7,8,10,10)
    val list = binarySearch2(arr, 0, arr.size - 1, 3)
    customPrint(list)

}
private fun createArr():Array<Int>{
    val array = Array<Int>(20){0}
    for (i in 1..20){
        array[i-1] = i
    }
    return array
}

//首要的前提条件  数组是有序的
//1、二分查找法 采用 递归比较的方式去实现,根据 findVal 从中找到 findVal == array[i] 返回 i
fun binarySearch(array: Array<Int>,left :Int,right: Int,findVal :Int) : Int{
    println("left->$left ,right->$right")
    if (left > right){
        return -1
    }
    val mid = (left+right) shr 1
    if (findVal > array[mid]){
        return binarySearch(array,mid+1,right,findVal)
    }else if(findVal < array[mid]) {
        return binarySearch(array,left,mid -1, findVal)
    }else{
        return mid
    }
}

//2、使用插值二分查找
fun binarySearch2(array: Array<Int>,left :Int,right: Int,findVal :Int) : List<Int>{
    if (left > right || findVal < array[0] || findVal > array[array.size-1]){
        return ArrayList()
    }
    //val mid = (left+right) shr 1
    //1,3,5,6,7,7,7,8,10,10
    val mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left])
    println("binarySearch2 mid ->$mid")
    if (findVal > array[mid]){
        return binarySearch2(array,mid+1,right,findVal)
    }else if(findVal < array[mid]) {
        return binarySearch2(array,left,mid -1, findVal)
    }else{
        //当其中有多个相同的数据时
        val list = ArrayList<Int>()
        list.add(mid)
        //先从左侧开始循环查找 是否有 相同的数
        var tempIndex = mid - 1
        while (true){
            //退出循环的条件
            if (tempIndex < 0 || array[tempIndex] != findVal){
                break
            }
            list.add(tempIndex)
            tempIndex--
        }

        //往右边开始遍历存照
        tempIndex = mid + 1
        while (true){
            if (tempIndex > array.size - 1 || array[tempIndex] != findVal){
                break
            }
            list.add(tempIndex)
            tempIndex++
        }
        return list
    }
}

private fun customPrint(arr:List<Int>){
    for (i in arr){
        print("$i \t")
    }
}