package com.rayhahah.libbase.helper

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import com.rayhahah.libbase.BaseConst
import com.rayhahah.libbase.ProjectConst
import com.rayhahah.libbase.utils.LogUtils
import com.rayhahah.libbase.utils.Machine
import com.rayhahah.libbase.utils.PackageUtil
import java.util.*


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
 * @time 2018/7/1
 * @tips 这个类是Object的子类
 * @fuction
 */
object AppHelper {


    /**
     * 功能简述:获取Android ID的方法
     */
    fun getAndroidId(context: Context): String {
        var androidId = ""
        UUID.randomUUID().toString()
        if (context != null) {
            androidId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }
        return androidId
    }

    /**
     * 跳转到Android Market
     *
     * @param uriString
     * market的uri
     * @return 成功打开返回true
     */
    fun gotoMarket(context: Context, uriString: String): Boolean {
        var uriString = uriString

        if (!TextUtils.isEmpty(uriString)) {
            try {
                val sharedPreferences = context.getSharedPreferences("desk", Context.MODE_PRIVATE)
                val isForceUpgrade = sharedPreferences.getBoolean("key_version_is_upgrade", false)
                if (isForceUpgrade) {
                    val replaceUrl =
                        sharedPreferences.getString("key_version_forceupgrade_replace_url", null)
                    if (!TextUtils.isEmpty(replaceUrl)) {
                        uriString = replaceUrl!!
                        //不存在电子市场跳浏览器
                        if (!isMarketExist(context)) {
                            return gotoBrowser(context, replaceUrl)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        var ret = false
        val marketIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
        marketIntent.`package` = PackageUtil.Package.GOOGLE_PLAY_PACKAGE
        if (context is Activity) {
            marketIntent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        } else {
            marketIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        try {
            context.startActivity(marketIntent)
            ret = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ret
    }

    /**
     * 浏览器直接访问uri
     *
     * @param uriString
     * @return 成功打开返回true
     */
    fun gotoBrowser(context: Context, uriString: String?): Boolean {
        var ret = false
        if (uriString == null) {
            return ret
        }
        val browserUri = Uri.parse(uriString)
        if (null != browserUri) {
            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            try {
                context.startActivity(browserIntent)
                ret = true
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return ret
    }

    /**
     * 优先跳转到market，如果失败则转到浏览器
     *
     * @param context
     * @param marketUrl
     * market地址
     * @param browserUrl
     * 浏览器地址
     */
    fun gotoBrowserIfFailtoMarket(
        context: Context, marketUrl: String,
        browserUrl: String
    ): Boolean {
        var toMarket = gotoMarket(context, marketUrl)
        if (!toMarket) {
            toMarket = gotoBrowser(context, browserUrl)
        }
        return toMarket
    }

    /**
     * 手机上是否有电子市场
     *
     * @param context
     * @return
     */
    fun isMarketExist(context: Context): Boolean {
        return isAppExist(context, BaseConst.Market.GOOGLE_PLAY_PACKAGE)
    }

    /**
     * 检查是安装某包
     *
     * @param context
     * @param packageName
     * 包名
     * @return
     */
    fun isAppExist(context: Context?, packageName: String?): Boolean {
        if (context == null || packageName == null) {
            return false
        }

        var result = false
        try {
            // context.createPackageContext(packageName,
            // Context.CONTEXT_IGNORE_SECURITY);
            context.packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SHARED_LIBRARY_FILES
            )
            result = true
        } catch (e: PackageManager.NameNotFoundException) {
            result = false
        } catch (e: Exception) {
            result = false
        }

        return result
    }

    fun isProductionMode(context: Context): Boolean {
        try {
            val metaData = context.packageManager
                .getApplicationInfo(
                    context.packageName,
                    PackageManager.GET_META_DATA
                ).metaData
            val buildType = metaData.getString("buildType")
            val isRelease = "release".equals(buildType!!, ignoreCase = true)

            if (!isRelease) {
                LogUtils.iTag("ProductionMode", "ProductMode=" + buildType!!)
            }
            return isRelease
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    fun addShortcut(context: Context, icon: Int, target: Class<*>) {
        val addShortcutIntent = Intent(Intent.ACTION_CREATE_SHORTCUT)


        // 不允许重复创建

        // 经测试不是根据快捷方式的名字判断重复的
        addShortcutIntent.putExtra("duplicate", false);
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, ProjectConst.PRODUCT_NAME)

        // 图标
        addShortcutIntent.putExtra(
            Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
            Intent.ShortcutIconResource.fromContext(context, icon)
        )

        // 设置关联程序
        val launcherIntent = Intent(Intent.ACTION_MAIN)
        launcherIntent.setClass(context, target)
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        addShortcutIntent
            .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent)

        // 发送广播
        context.sendBroadcast(addShortcutIntent)
    }

    fun triggerAlarm(
        alarmManager: AlarmManager, type: Int, triggerAtMillis: Long,
        operation: PendingIntent
    ) {
        try {
            if (Machine.IS_SDK_ABOVE_KITKAT) {
                alarmManager.setExact(type, triggerAtMillis, operation)
            } else {
                alarmManager.set(type, triggerAtMillis, operation)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}