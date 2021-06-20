package com.wangzs.jetpackmvvm.demo.viewmodel.state

import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.callback.databind.IntObservableField
import com.wangzs.jetpackmvvm.callback.databind.StringObservableField
import com.wangzs.jetpackmvvm.callback.livedata.UnPeekLiveData
import com.wangzs.jetpackmvvm.demo.app.util.ColorUtil

/**
 * 作者　: wangzs
 * 时间　: 2019/12/27
 * 描述　: 专门存放 MeFragment 界面数据的ViewModel
 */
class MeViewModel : BaseViewModel() {

    var name = StringObservableField("请先登录~")

    var integral = IntObservableField(0)

    var info = StringObservableField("id：--　排名：-")

    var imageUrl = StringObservableField(ColorUtil.randomImage())

    var testString = UnPeekLiveData<String>()
}