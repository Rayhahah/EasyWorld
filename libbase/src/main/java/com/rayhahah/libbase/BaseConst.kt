package com.rayhahah.libbase

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
 * @time 2018/5/14
 * @tips 这个类是Object的子类
 * @fuction
 */
object BaseConst {

    object Market {

        const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
        // 浏览器版本的电子市场详情地址
        const val BROWSER_APP_DETAIL = "https://play.google.com/store/apps/details?id="

        // 用包名搜索market上的软件
        const val BY_PKGNAME = "market://search?q=pname:"

        // 进入软件详细页面
        const val APP_DETAIL = "market://details?id="

        const val GOLAUNCHER_MARKET_URL =
            "market://details?id=com.gau.go.launcherex&referrer=utm_source%3Dupgrade%26utm_medium%3DHyperlink%26utm_campaign%3DGOLauncher"
        const val VIDEO_WALLPAPER_MARKET_URL =
            "market://details?id=com.coolworld.livewallpaperstudio&referrer=utm_source%3Dupgrade%26utm_medium%3DHyperlink%26utm_campaign%3DLiveWallpaperStudio"
        const val GOLAUNCHER_BROWSER_URL =
            "https://play.google.com/store/apps/details?id=com.gau.go.launcherex&referrer=utm_source%3Dupgrade%26utm_medium%3DHyperlink%26utm_campaign%3DGOLauncher"
        const val VIDEO_WALLPAPER_BROWSER_URL =
            "https://play.google.com/store/apps/details?id=com.coolworld.livewallpaperstudio&referrer=utm_source%3Dupgrade%26utm_medium%3DHyperlink%26utm_campaign%3DLiveWallpaperStudio"
    }


}