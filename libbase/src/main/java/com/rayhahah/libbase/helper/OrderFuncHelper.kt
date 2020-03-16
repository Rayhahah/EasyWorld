package com.rayhahah.libbase.helper

import java.util.*

/**
 *
 * 队列执行工具
 */
class OrderFuncHelper {

    private var queueFun = LinkedList<Function>()

    private var currentFun: (() -> Unit)? = null // 当前任务


    fun addFunc(function: () -> Unit) {
        val func = Function(function)
        doFunc(func)
    }

    fun finishFunc() {
        doFunc(null)
    }

    private fun doFunc(func: Function?) {
        if (func != null) {
            queueFun.offer(func)
        } else {
            currentFun = null
        }
        if (currentFun == null) {
            if (queueFun.size != 0) {
                val funnow = queueFun.poll()
                currentFun = funnow.function
                currentFun?.invoke()
            }
        }
    }

    data class Function(var function: () -> Unit = {})
}
