package com.wangzs.jetpackmvvm.demo.viewmodel.state

import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.callback.databind.StringObservableField

/**
 * 作者　: wangzs
 * 时间　: 2020/3/11
 * 描述　:
 */
class AriticleViewModel : BaseViewModel() {

    //分享文章标题
    var shareTitle = StringObservableField()

    //分享文章网址
    var shareUrl = StringObservableField()

    //分享文章的人
    var shareName = StringObservableField()

}