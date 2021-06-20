package com.wangzs.jetpackmvvm.demo.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.appViewModel
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.app.ext.init
import com.wangzs.jetpackmvvm.demo.app.ext.initMain
import com.wangzs.jetpackmvvm.demo.app.ext.interceptLongClick
import com.wangzs.jetpackmvvm.demo.app.ext.setUiTheme
import com.wangzs.jetpackmvvm.demo.databinding.FragmentMainBinding
import com.wangzs.jetpackmvvm.demo.viewmodel.state.MainViewModel
import com.wangzs.jetpackmvvm.ext.nav

/**
 * 时间　: 2019/12/27
 * 作者　: wangzs
 * 描述　:项目主页Fragment
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override fun layoutId() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        mainBottom.init{
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_system -> mainViewpager.setCurrentItem(2, false)
                R.id.menu_public -> mainViewpager.setCurrentItem(3, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(4, false)
            }
        }
        mainBottom.interceptLongClick(R.id.menu_main,R.id.menu_project,R.id.menu_system,R.id.menu_public,R.id.menu_me)
    }

    override fun createObserver() {
        appViewModel.appColor.observeInFragment(this, Observer {
            setUiTheme(it, mainBottom)
        })
    }

}