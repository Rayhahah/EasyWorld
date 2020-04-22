package com.rayhahah.easyworld.data.repo

import androidx.lifecycle.MutableLiveData
import com.rayhahah.easyworld.R
import com.rayhahah.easyworld.data.bean.DrawerItem

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
 * @time 2020/3/17
 * @tips 这个类是Object的子类
 * @fuction
 */
object DrawerRepository {

    suspend fun getItem(): ArrayList<DrawerItem> {
        val items = arrayListOf<DrawerItem>()
        items.add(DrawerItem("Home", "", DrawerItem.TYPE_MIAN, R.id.mainFragment))
        items.add(DrawerItem("Page", "Show how page it is.", DrawerItem.TYPE_SUB, -1))
        items.add(
            DrawerItem(
                "Setting",
                "Here is some advance for App.",
                DrawerItem.TYPE_SUB,
                R.id.settingFragment
            )
        )
        return items
    }

    suspend fun addItem(data: MutableLiveData<ArrayList<DrawerItem>>): ArrayList<DrawerItem> {
        val value = arrayListOf<DrawerItem>()
        value.addAll(data.value!!)
        if (value.size % 2 == 1) {
            value.add(DrawerItem("Add ${value.size + 1}", "", DrawerItem.TYPE_MIAN, -1))
        } else {
            value.add(
                DrawerItem(
                    "Add ${value.size + 1}",
                    "Add ${value.size + 1}  Description",
                    DrawerItem.TYPE_SUB,
                    -1
                )
            )
        }
        return value
    }

    suspend fun removeItem(data: MutableLiveData<ArrayList<DrawerItem>>): ArrayList<DrawerItem> {
        val value = arrayListOf<DrawerItem>()
        value.addAll(data.value!!)
        value.removeAt(value.size - 1)
        return value
    }
}