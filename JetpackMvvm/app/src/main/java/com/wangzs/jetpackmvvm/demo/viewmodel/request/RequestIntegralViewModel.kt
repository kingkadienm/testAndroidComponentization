package com.wangzs.jetpackmvvm.demo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.app.network.apiService
import com.wangzs.jetpackmvvm.demo.app.network.stateCallback.ListDataUiState
import com.wangzs.jetpackmvvm.demo.data.model.bean.IntegralHistoryResponse
import com.wangzs.jetpackmvvm.demo.data.model.bean.IntegralResponse
import com.wangzs.jetpackmvvm.ext.request

/**
 * 作者　: wangzs
 * 时间　: 2020/3/10
 * 描述　:
 */
class RequestIntegralViewModel : BaseViewModel() {

    private var pageNo = 1

    //积分排行数据
    var integralDataState = MutableLiveData<ListDataUiState<IntegralResponse>>()

    //获取积分历史数据
    var integralHistoryDataState = MutableLiveData<ListDataUiState<IntegralHistoryResponse>>()

    fun getIntegralData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ apiService.getIntegralRank(pageNo) }, {
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            integralDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<IntegralResponse>()
                )
            integralDataState.value = listDataUiState
        })
    }

    fun getIntegralHistoryData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ apiService.getIntegralHistory(pageNo) }, {
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            integralHistoryDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<IntegralHistoryResponse>()
                )
            integralHistoryDataState.value = listDataUiState
        })
    }
}