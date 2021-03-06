package com.wangzs.jetpackmvvm.demo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.app.network.apiService
import com.wangzs.jetpackmvvm.demo.app.network.stateCallback.ListDataUiState
import com.wangzs.jetpackmvvm.demo.data.model.bean.AriticleResponse
import com.wangzs.jetpackmvvm.demo.data.model.bean.ShareResponse
import com.wangzs.jetpackmvvm.ext.request

/**
 * 作者　: wangzs
 * 时间　: 2020/3/4
 * 描述　:
 */
class RequestLookInfoViewModel : BaseViewModel() {

    var pageNo = 1

    var shareListDataUistate = MutableLiveData<ListDataUiState<AriticleResponse>>()

    var shareResponse = MutableLiveData<ShareResponse>()

    fun getLookinfo(id: Int, isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ apiService.getShareByidData(id, pageNo) }, {
            //请求成功
            pageNo++
            shareResponse.value = it
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = it.shareArticles.isRefresh(),
                    isEmpty = it.shareArticles.isEmpty(),
                    hasMore = it.shareArticles.hasMore(),
                    isFirstEmpty = isRefresh && it.shareArticles.isEmpty(),
                    listData = it.shareArticles.datas
                )
            shareListDataUistate.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            shareListDataUistate.value = listDataUiState
        })
    }
}