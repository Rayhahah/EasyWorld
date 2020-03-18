package com.rayhahah.easyworld

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.observe
import com.rayhahah.easyworld.architecture.NavHelper
import com.rayhahah.easyworld.architecture.base.BindingActivity
import com.rayhahah.easyworld.bridge.InjectorHelper
import com.rayhahah.easyworld.bridge.state.MainActivityViewModel
import com.rayhahah.easyworld.databinding.ActivityMainBinding

class MainActivity : BindingActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mMainActivityViewModel: MainActivityViewModel by viewModels {
        InjectorHelper.provideDefaultFactory()
    }

    override fun initView(savedInstanceState: Bundle?) {
        InitProxy.onHomeInit()
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.vm = mMainActivityViewModel

        NavHelper.initKeepStateNavigator(this, R.id.main_fragment_host, R.navigation.nav_main)

        mMainActivityViewModel.openDrawer.value = true
        mMainActivityViewModel.allowDrawerOpen.value = true

        mBinding.dl.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })
        mSharedViewModel.openOrCloseDrawer.observe(this) { open: Boolean ->
            mMainActivityViewModel.openDrawer.value = open
        }
    }
}