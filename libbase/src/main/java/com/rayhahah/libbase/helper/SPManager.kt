package com.rayhahah.libbase.helper

import android.content.Context
import android.content.SharedPreferences
import com.rayhahah.libbase.BaseApp
import com.rayhahah.libbase.ProjectConst


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
 * @time 2018/1/30
 * @tips 这个类是Object的子类
 * @fuction SP工具类
 */
class SPManager private constructor() {

//    private val prefenerces: MMKV by lazy {
//        MMKV.mmkvWithID(ProjectConst.TABLE_PREFS)
//    }
//    private val editor: SharedPreferences.Editor by lazy {
//        prefenerces.edit()
//    }

    private val prefenerces: SharedPreferences by lazy {
        BaseApp.getAppContext().getSharedPreferences(ProjectConst.TABLE_PREFS, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        prefenerces.edit()
    }

    /**
     * Boolean数据
     */
    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }

    /**
     *  默认 false
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefenerces.getBoolean(key, defaultValue)
    }

    /**
     *  String数据
     */
    fun putString(key: String, value: String) {
        editor.putString(key, value)
    }

    /**
     *   默认 ""
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return prefenerces.getString(key, defaultValue) ?: ""
    }

    /**
     *  Int数据
     */
    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
    }

    /**
     *  默认 0
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefenerces.getInt(key, defaultValue)
    }

    /**
     *   Float数据
     */
    fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
    }

    /**
     *  默认 0
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return prefenerces.getFloat(key, defaultValue)
    }

    /**
     *   Long数据
     */
    fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
    }

    /**
     *  默认 0
     */
    fun getLong(key: String, defaultValue: Long = 0): Long {
        return prefenerces.getLong(key, defaultValue)
    }


    /**
     *   删除key数据
     */
    fun remove(key: String) {
        editor.remove(key)
    }


    companion object {
        fun getInstance() = Holder.instance
    }

    object Holder {
        val instance = SPManager()
    }
}