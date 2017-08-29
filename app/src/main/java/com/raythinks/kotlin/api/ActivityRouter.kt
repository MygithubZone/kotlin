package com.raythinks.kotlin.api

/**
 * 功能：<br></br>
 * 作者：赵海<br></br>
 * 时间： 2017/8/21 0021<br></br>.
 * 版本：1.2.0
 */

class ActivityRouter {
    init {
    { x: Int, y: Int ->
        println("${x + y}")
    }(1, 3)
}
val printMsg={msg:String-> print(msg)}
    companion object {
    }
fun  main(){
    printMsg.invoke("sss")
}
}
