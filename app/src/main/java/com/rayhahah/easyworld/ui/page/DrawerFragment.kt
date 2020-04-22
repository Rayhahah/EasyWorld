package com.rayhahah.easyworld.ui.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rayhahah.easyworld.R
import com.rayhahah.easyworld.architecture.base.BindingFragment
import com.rayhahah.easyworld.bridge.InjectorHelper
import com.rayhahah.easyworld.bridge.state.DrawerFragmentViewModel
import com.rayhahah.easyworld.bridge.state.MainFragmentViewModel
import com.rayhahah.easyworld.data.bean.DrawerItem
import com.rayhahah.easyworld.databinding.FragmentDrawerBinding
import com.rayhahah.easyworld.ui.adapter.DrawerAdapter
import com.rayhahah.libbase.shortToast

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
 * @time 2020/3/16
 * @tips 这个类是Object的子类
 * @fuction
 */
class DrawerFragment : BindingFragment<FragmentDrawerBinding>() {
    private lateinit var drawerAdapter: DrawerAdapter

    private val mDrawerViewModel: DrawerFragmentViewModel by viewModels {
        //        InjectorHelper.provideDefaultFactory()
        InjectorHelper.provideDefaultSaveStateFactory(requireActivity())
    }

    private val mMainFragmentViewModel: MainFragmentViewModel by viewModels(
        { requireParentFragment() },
        { InjectorHelper.provideDefaultSaveStateFactory(requireActivity()) }
    )
    override fun getLayoutId(): Int = R.layout.fragment_drawer

    override fun onCreateView(view: View): View {
        mBinding = FragmentDrawerBinding.bind(view)
        mBinding?.apply {
            vm = mDrawerViewModel
            click = ClickProxy()
            lifecycleOwner = viewLifecycleOwner
        }
        return view
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.apply {
            drawerAdapter = DrawerAdapter.init(rv)
            mDrawerViewModel.drawerItemData.observe(viewLifecycleOwner, Observer {
                for (item in it) {
                    item.click = { view ->
                        shortToast(item.title)
                        onClickEvent(item)
                    }
                }
                drawerAdapter.setDiffNewData(it)
            })
            mDrawerViewModel.getItem()
        }
    }


    private fun onClickEvent(item: DrawerItem) {
        if (item.action != -1) {
            mSharedViewModel.openOrCloseDrawer.value = false
            mSharedViewModel.navMainData.value = item.action
        }
    }

    inner class ClickProxy {

        fun clickTitle() {
            mDrawerViewModel.addItem()
        }

        fun clickBottom() {
            mDrawerViewModel.removeItem()
        }
    }
}