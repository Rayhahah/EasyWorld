package com.rayhahah.libbase

import com.rayhahah.libbase.utils.FileUtils
import com.rayhahah.libbase.utils.LogUtils
import java.io.File

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
object ProjectConst {

    const val PRODUCT_NAME = "EasyWorld"
    const val PACKAGE: String = "com.rayhahah.easyworld"

    var DEBUG: Boolean = false
    var TAG_TYPE: Int = LogUtils.D
    const val DEFAULT_TAG: String = "EasyWorld"

    //SP表名
    const val TABLE_PREFS = "EasyWorld"

    /**
     * 测试地址
     */
//    const val HOST = "https://livewallpaperstudio.rayhahah.com/"
    /**
     * 正式环境
     */
    const val HOST = "https://livewallpaperstudio.ccoolworld.com/"

    /**
     * 各种临时测试配置，上线前检查，以防错漏
     */
    object Test {

        /**
         * 强制使用启动试用流程
         */
        const val IS_FORCE_SPLASH_TRIAL = false
    }

    object Dir {
        /**
         * Tips
         * FileUtils.rootFilePath  =   /data/data
         * filesDir                =   /data/data/com.learn.test/files
         * cacheDir                =   /data/data/com.learn.test/cache
         * externalCacheDir        =   /storage/emulated/0/Android/data/com.learn.test/cache
         * getExternalFilesDir     =    /storage/emulated/0/Android/data/com.learn.test/files
         */

        var VIDEO_DIR = FileUtils.rootFilePath + "${PRODUCT_NAME}/video"
        var LOG_DIR =
            BaseApp.mAppContext.filesDir.absolutePath + File.separator + "${PRODUCT_NAME}/log"
    }
}
