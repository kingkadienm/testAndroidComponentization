package com.wangzs.jetpackmvvm.demo.ui.fragment.tree

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.appViewModel
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.app.ext.bindViewPager2
import com.wangzs.jetpackmvvm.demo.app.ext.init
import com.wangzs.jetpackmvvm.demo.app.ext.initClose
import com.wangzs.jetpackmvvm.demo.data.model.bean.SystemResponse
import com.wangzs.jetpackmvvm.demo.databinding.FragmentSystemBinding
import com.wangzs.jetpackmvvm.demo.viewmodel.state.TreeViewModel
import com.wangzs.jetpackmvvm.ext.nav

/**
 * 作者　: wangzs
 * 时间　: 2020/3/4
 * 描述　:
 */
class SystemArrFragment : BaseFragment<TreeViewModel, FragmentSystemBinding>() {

    lateinit var data: SystemResponse

    var index = 0

    private var fragments: ArrayList<Fragment> = arrayListOf()

    override fun layoutId() = R.layout.fragment_system

    override fun initView(savedInstanceState: Bundle?)  {
        arguments?.let {
            data = it.getParcelable("data")!!
            index = it.getInt("index")
        }
        toolbar.initClose(data.name) {
            nav().navigateUp()
        }
        //初始化时设置顶部主题颜色
        appViewModel.appColor.value?.let { viewpager_linear.setBackgroundColor(it) }
        //设置栏目标题居左显示
        (magic_indicator.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.LEFT

    }

    override fun lazyLoadData() {
        data.children.forEach {
            fragments.add(SystemChildFragment.newInstance(it.id))
        }
        //初始化viewpager2
        view_pager.init(this, fragments)
        //初始化 magic_indicator
        magic_indicator.bindViewPager2(view_pager, data.children.map { it.name })

        view_pager.offscreenPageLimit = fragments.size

        view_pager.postDelayed({
            view_pager.currentItem = index
        },100)

    }

    override fun createObserver() {

    }

}