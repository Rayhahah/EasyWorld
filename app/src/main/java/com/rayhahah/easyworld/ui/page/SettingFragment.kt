package com.rayhahah.easyworld.ui.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.rayhahah.easyworld.R
import com.rayhahah.easyworld.architecture.base.BindingFragment
import com.rayhahah.easyworld.bridge.InjectorHelper
import com.rayhahah.easyworld.bridge.state.SettingFragmentViewModel
import com.rayhahah.easyworld.databinding.FragmentSettingBinding
import com.rayhahah.libbase.utils.LogUtils

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
class SettingFragment : BindingFragment<FragmentSettingBinding>() {

    private val mSettingFragViewModel: SettingFragmentViewModel by viewModels {
        InjectorHelper.provideDefaultFactory()
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mSharedViewModel.navMainData.observe(viewLifecycleOwner) { it ->
            if (it != R.id.settingFragment) {
                nav().navigate(it)
            }
        }

        mSettingFragViewModel.settingInfo.observe(viewLifecycleOwner) {
            LogUtils.eTag("SettingFragment", it.toString())
        }

//        mSettingFragViewModel.testStr.observe(viewLifecycleOwner) {
//            LogUtils.eTag("SettingFragment", it.toString())
//        }
        mSettingFragViewModel.getSettingInfo()
    }

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun onCreateView(view: View): View {
        LogUtils.dTag("BindingFragment", " SettingFragment onCreateView")
        mBinding = FragmentSettingBinding.bind(view)
        mBinding?.apply {
            vm = mSettingFragViewModel
            click = ClickProxy()
            lifecycleOwner=viewLifecycleOwner
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.dTag("BindingFragment", " SettingFragment onDestroyView")
    }

    inner class ClickProxy {
        fun updateClick() {
            mSettingFragViewModel.updateSettingInfo()
        }
    }
}