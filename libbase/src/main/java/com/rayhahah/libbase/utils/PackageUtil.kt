package com.rayhahah.libbase.utils

import android.content.Context
import android.content.pm.PackageManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
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
 * @time 2018/6/26
 * @tips 这个类是Object的子类
 * @fuction
 */
object PackageUtil {

    object Package {
        const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
    }

    private var appVersionName: String? = null
    private var majorMinorVersion: String? = null
    private var majorVersion = -1
    private var minorVersion = -1
    private var fixVersion = -1
    private var appVersionCode = 0


    /**
     * manifest 中的 versionName 字段
     */
    fun getAppVersion(context: Context): String {
        if (appVersionName == null) {
            val manager = context.packageManager
            try {
                val info = manager.getPackageInfo(context.packageName, 0)
                appVersionName = info.versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return if (appVersionName == null) {
            ""
        } else {
            appVersionName!!
        }
    }


    /**
     * manifest 中的 versionCode 字段
     */
    fun getAppVersionCode(context: Context): Int {
        if (appVersionCode == 0) {
            val manager = context.packageManager
            try {
                val info = manager.getPackageInfo(context.packageName, 0)
                appVersionCode = info.versionCode
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return if (appVersionCode == 0) {
            0
        } else {
            appVersionCode
        }
    }

    /**
     * 获取 App 的主版本与次版本号。比如说 3.1.2 中的 3.1
     */
    fun getMajorMinorVersion(context: Context): String {
        if (majorMinorVersion == null || majorMinorVersion == "") {
            majorMinorVersion = getMajorVersion(context).toString() + "." + getMinorVersion(context)
        }
        return majorMinorVersion as String
    }

    /**
     * 读取 App 的主版本号，例如 3.1.2，主版本号是 3
     */
    private fun getMajorVersion(context: Context): Int {
        if (majorVersion == -1) {
            val appVersion = getAppVersion(context)
            val parts =
                appVersion.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (parts.size != 0) {
                majorVersion = Integer.parseInt(parts[0])
            }
        }
        return majorVersion
    }

    /**
     * 读取 App 的次版本号，例如 3.1.2，次版本号是 1
     */
    private fun getMinorVersion(context: Context): Int {
        if (minorVersion == -1) {
            val appVersion = getAppVersion(context)
            val parts =
                appVersion.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (parts.size >= 2) {
                minorVersion = Integer.parseInt(parts[1])
            }
        }
        return minorVersion
    }

    /**
     * 读取 App 的修正版本号，例如 3.1.2，修正版本号是 2
     */
    fun getFixVersion(context: Context): Int {
        if (fixVersion == -1) {
            val appVersion = getAppVersion(context)
            val parts =
                appVersion.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (parts.size >= 3) {
                fixVersion = Integer.parseInt(parts[2])
            }
        }
        return fixVersion
    }

    fun SHA1(context: Context): String? {
        try {
            val info = context.packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNATURES
            )
            val cert = info.signatures[0].toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val publicKey = md.digest(cert)
            val hexString = StringBuffer()
            for (i in publicKey.indices) {
                val appendString = Integer.toHexString(0xFF and publicKey[i].toInt())
                    .toUpperCase(Locale.US)
                if (appendString.length == 1) {
                    hexString.append("0")
                }
                hexString.append(appendString)
                hexString.append(":")
            }
            val result = hexString.toString()
            return result.substring(0, result.length - 1)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }


}