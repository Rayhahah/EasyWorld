package com.rayhahah.libbase

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.google.gson.reflect.TypeToken
import com.rayhahah.libbase.helper.AnimHelper
import com.rayhahah.libbase.helper.JsonHelper
import com.rayhahah.libbase.utils.LogUtils


/**
 * ┌───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│ │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│ ┌┐    ┌┐    ┌┐
 * └───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘ └┘    └┘    └┘
 * ┌──┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐┌───┬───┬───┐┌───┬───┬───┬───┐
 * │~`│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp ││Ins│Hom│PUp││N L│ / │ * │ - │
 * ├──┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤├───┼───┼───┤├───┼───┼───┼───┤
 * │Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ ││Del│End│PDn││ 7 │ 8 │ 9 │   │
 * ├────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤└───┴───┴───┘├───┼───┼───┤ + │
 * │Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │             │ 4 │ 5 │ 6 │   │
 * ├─────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤    ┌───┐    ├───┼───┼───┼───┤
 * │Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │    │ ↑ │    │ 1 │ 2 │ 3 │   │
 * ├────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤┌───┼───┼───┐├───┴───┼───┤ E││
 * │Ctrl│Ray │Alt │         Space         │ Alt│code│fuck│Ctrl││ ← │ ↓ │ → ││   0   │ . │←─┘│
 * └────┴────┴────┴───────────────────────┴────┴────┴────┴────┘└───┴───┴───┘└───────┴───┴───┘
 *
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/5/13
 * @tips 这个类是Object的子类
 * @fuction
 */

fun ltime(msg: Any, startTime: Long = 0, tag: String = ProjectConst.DEFAULT_TAG): Long {
    val currentTimeMillis = System.currentTimeMillis()
    LogUtils.dTag(tag, msg.toString() + "=${currentTimeMillis - startTime}")
    return currentTimeMillis
}

fun lFile(msg: Any, tag: String = ProjectConst.DEFAULT_TAG) {
    LogUtils.file(tag, msg.toString())
}

/**
 * 对象转Json
 */
inline fun <reified T : Any> List<T>.toJsonList(): String {
    return JsonHelper.getInstance().gson().toJson(this, T::class.java)
}

/**
 * 对象转Json
 */
inline fun <reified T : Any> T.toJson(): String {
    return JsonHelper.getInstance().gson().toJson(this, T::class.java)
}


/**
 * Json转对象
 */
inline fun <reified T> String.parse(): T {
    return JsonHelper.getInstance().gson().fromJson(this, T::class.java)
}


/**
 * Json转对象
 */
inline fun <reified T> String.parseList(): T {
    return JsonHelper.getInstance().gson().fromJson<T>(this, object : TypeToken<T>() {
    }.type)
}

//fun <T, H : BaseViewHolder> BaseQuickAdapter<T, H>.autoNotify(old: List<T>, new: List<T>,
//                                                              compareItemSame: (old: T, new: T) -> Boolean, compareContentSame: (old: T, new: T) -> Boolean) {
//    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return compareItemSame(old[oldItemPosition], new[newItemPosition])
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return compareContentSame(old[oldItemPosition], new[newItemPosition])
//        }
//
//        override fun getOldListSize() = old.size
//
//        override fun getNewListSize() = new.size
//    })
//    this.data?.clear()
//    this.data?.addAll(new)
//
//    diff.dispatchUpdatesTo(this)
//}

fun ObjectAnimator.obtainOption(
    duration: Long,
    timeInterpolator: TimeInterpolator = LinearInterpolator(),
    repeatCount: Int = 0, repeatMode: Int = ValueAnimator.RESTART
): ObjectAnimator {
    return AnimHelper.obtainOption(this, duration, timeInterpolator, repeatCount, repeatMode)
}