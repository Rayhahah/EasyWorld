package com.rayhahah.libbase.net

import android.util.Log
import com.rayhahah.libbase.BaseApp
import com.rayhahah.libbase.ProjectConst
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


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
 * @time 2018/1/31
 * @tips 这个类是Object的子类
 * @fuction
 */

class OkHttpManager private constructor() {

    private val DEFAULT_TIMEOUT = 10

    /**
     * 构建OkHttpClient
     */
    fun create(interceptor: Interceptor? = null): OkHttpClient.Builder {
        //添加Cache拦截器，有网时添加到缓存中，无网时取出缓存
        val dir = BaseApp.getAppContext().cacheDir
        val cache = Cache(dir, 1024 * 1024 * 100)
        val builder = OkHttpClient.Builder()
            //缓存只能用在get请求上
//                .addNetworkInterceptor(CacheInterceptor())
//                .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(cache)

        if (ProjectConst.DEBUG) {
            builder.addInterceptor(initLogInterceptor())
        }
        if (interceptor != null) {
            builder.addNetworkInterceptor(interceptor)
        }
        return builder
    }

    /**
     * 日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            if (ProjectConst.DEBUG) {
                Log.v("OkHttpManager", it)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * post的请求参数，构造RequestBody
     * @param bodyParams
     * @return
     */
    fun setRequestBody(bodyParams: Map<String, String>?): RequestBody {
        val formEncodingBuilder = okhttp3.FormBody.Builder()
        if (bodyParams != null) {
            val iterator = bodyParams.keys.iterator()
            var key = ""
            while (iterator.hasNext()) {
                key = iterator.next()
                formEncodingBuilder.add(key, bodyParams[key] ?: "")
                Log.d("post http", "post_Params===" + key + "====" + bodyParams[key])
            }
        }
        return formEncodingBuilder.build()
    }


    companion object {
        fun getInstance() = Holder.instance
    }

    object Holder {
        val instance = OkHttpManager()
    }

}