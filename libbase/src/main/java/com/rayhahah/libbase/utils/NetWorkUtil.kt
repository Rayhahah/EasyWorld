package com.rayhahah.libbase.utils

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission


/*
    网络工具
 */
object NetWorkUtil {


    /**
     * Open the settings of wireless.
     */
    fun openWirelessSettings(context: Context) {
        context.startActivity(
                Intent(Settings.ACTION_WIRELESS_SETTINGS)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    /**
     * Return whether network is connected.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`
     *
     * @return `true`: connected<br></br>`false`: disconnected
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isNetWorkConnected(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info.isConnected
    }

    /**
     * Return whether using 4G.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`
     *
     * @return `true`: yes<br></br>`false`: no
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun is4G(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return (info != null
                && info.isAvailable
                && info.subtype == TelephonyManager.NETWORK_TYPE_LTE)
    }

    /**
     * Return whether wifi is enabled.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`
     *
     * @return `true`: enabled<br></br>`false`: disabled
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun getWifiEnabled(context: Context): Boolean {
        @SuppressLint("WifiManagerLeak")
        val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return manager.isWifiEnabled
    }

    /**
     * Set wifi enabled.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />`
     *
     * @param enabled True to enabled, false otherwise.
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun setWifiEnabled(context: Context,enabled: Boolean) {
        @SuppressLint("WifiManagerLeak")
        val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager ?: return
        if (enabled) {
            if (!manager.isWifiEnabled) {
                manager.isWifiEnabled = true
            }
        } else {
            if (manager.isWifiEnabled) {
                manager.isWifiEnabled = false
            }
        }
    }

    /**
     * Return whether wifi is connected.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`
     *
     * @return `true`: connected<br></br>`false`: disconnected
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isWifiConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (cm.activeNetworkInfo != null
                && cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return null
        return manager.activeNetworkInfo
    }

}
