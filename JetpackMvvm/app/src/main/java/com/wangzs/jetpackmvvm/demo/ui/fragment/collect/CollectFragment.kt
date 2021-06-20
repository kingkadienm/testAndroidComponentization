package com.wangzs.jetpackmvvm.demo.ui.fragment.collect

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.appViewModel
import com.wangzs.jetpackmvvm.demo.viewmodel.request.RequestCollectViewModel
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.app.ext.bindViewPager2
import com.wangzs.jetpackmvvm.demo.app.ext.init
import com.wangzs.jetpackmvvm.demo.app.ext.initClose
import com.wangzs.jetpackmvvm.demo.databinding.FragmentCollectBinding
import com.wangzs.jetpackmvvm.ext.nav

/**
 * 作者　: wangzs
 * 时间　: 2020/3/10
 * 描述　: 收藏
 */
class CollectFragment:BaseFragment<RequestCollectViewModel,FragmentCollectBinding>() {

    var titleData = arrayListOf("文章","网址")

    private var fragments : ArrayList<Fragment> = arrayListOf()

    init {
        fragments.add(CollectAriticleFragment())
        fragments.add(CollectUrlFragment())
    }
    override fun layoutId() = R.layout.fragment_collect

    override fun initView(savedInstanceState: Bundle?)  {
        //初始化时设置顶部主题颜色
        appViewModel.appColor.value?.let { collect_viewpager_linear.setBackgroundColor(it) }
        //初始化viewpager2
        collect_view_pager.init(this,fragments)
        //初始化 magic_indicator
        collect_magic_indicator.bindViewPager2(collect_view_pager,mStringList = titleData)
        toolbar.initClose(){
            nav().navigateUp()
        }

    }
}