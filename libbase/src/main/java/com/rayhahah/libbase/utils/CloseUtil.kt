package com.rayhahah.libbase.utils

import java.io.Closeable
import java.io.IOException

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/10/9
 * desc  : 关闭相关工具类
</pre> *
 * @author Blankj
 */
class CloseUtil private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        /**
         * 关闭IO
         *
         * @param closeables closeable
         */
        fun closeIO(vararg closeables: Closeable) {
            if (closeables == null) {
                return
            }
            closeables
                    .filterNotNull()
                    .forEach {
                        try {
                            it.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
        }

        /**
         * 安静关闭IO
         *
         * @param closeables closeable
         */
        fun closeIOQuietly(vararg closeables: Closeable) {
            if (closeables == null) {
                return
            }
            closeables
                    .filterNotNull()
                    .forEach {
                        try {
                            it.close()
                        } catch (ignored: IOException) {
                        }
                    }
        }
    }
}