package com.wangzs.jetpackmvvm.demo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.app.network.apiService
import com.wangzs.jetpackmvvm.demo.data.model.bean.IntegralResponse
import com.wangzs.jetpackmvvm.ext.request
import com.wangzs.jetpackmvvm.state.ResultState

/**
 * 作者　: wangzs
 * 时间　: 2019/12/27
 * 描述　:
 */
class RequestMeViewModel : BaseViewModel() {

    var meData = MutableLiveData<ResultState<IntegralResponse>>()

    fun getIntegral() {
        request({ apiService.getIntegral() }, meData)
    }
}