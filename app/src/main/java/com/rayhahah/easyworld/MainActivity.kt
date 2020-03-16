package com.rayhahah.easyworld

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
    }
}