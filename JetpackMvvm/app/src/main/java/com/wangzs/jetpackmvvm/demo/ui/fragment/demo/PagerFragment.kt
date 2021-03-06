package com.wangzs.jetpackmvvm.demo.ui.fragment.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.databinding.FragmentPagerBinding
import com.wangzs.jetpackmvvm.demo.ui.fragment.collect.CollectFragment
import com.wangzs.jetpackmvvm.demo.ui.fragment.search.SearchFragment
import com.wangzs.jetpackmvvm.demo.ui.fragment.share.AriticleFragment
import com.wangzs.jetpackmvvm.demo.ui.fragment.todo.TodoListFragment
import com.wangzs.jetpackmvvm.demo.viewmodel.state.MainViewModel

/**
 * @author : wangzs
 * @date   : 2020/6/15
 * 测试 Viewpager下的懒加载
 */
class PagerFragment : BaseFragment<MainViewModel, FragmentPagerBinding>() {

    override fun layoutId() = R.layout.fragment_pager

    override fun initView(savedInstanceState: Bundle?) {
        pagerViewpager.adapter = object : FragmentStatePagerAdapter(childFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        SearchFragment()
                    }
                    1 -> {
                        TodoListFragment()
                    }
                    2 -> {
                        AriticleFragment()
                    }
                    else -> {
                        CollectFragment()
                    }
                }
            }

            override fun getCount(): Int {
                return 5;
            }
        }
        pagerViewpager.offscreenPageLimit = 5
    }
}