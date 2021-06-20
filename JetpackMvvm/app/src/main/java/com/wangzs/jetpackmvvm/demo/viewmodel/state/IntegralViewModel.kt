package com.wangzs.jetpackmvvm.demo.viewmodel.state

import androidx.databinding.ObservableField
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.data.model.bean.IntegralResponse

/**
 * 作者　: wangzs
 * 时间　: 2020/3/10
 * 描述　:
 */
class IntegralViewModel : BaseViewModel() {

    var rank = ObservableField<IntegralResponse>()
}