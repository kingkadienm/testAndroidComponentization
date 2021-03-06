package com.wangzs.jetpackmvvm.demo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.app.network.apiService
import com.wangzs.jetpackmvvm.demo.app.network.stateCallback.ListDataUiState
import com.wangzs.jetpackmvvm.demo.data.model.bean.AriticleResponse
import com.wangzs.jetpackmvvm.demo.data.model.bean.ClassifyResponse
import com.wangzs.jetpackmvvm.demo.data.repository.request.HttpRequestCoroutine
import com.wangzs.jetpackmvvm.ext.request
import com.wangzs.jetpackmvvm.state.ResultState

/**
 * 作者　: wangzs
 * 时间　: 2020/2/28
 * 描述　:
 */
class RequestProjectViewModel : BaseViewModel() {

    //页码
    var pageNo = 1

    var titleData: MutableLiveData<ResultState<ArrayList<ClassifyResponse>>> = MutableLiveData()

    var projectDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    fun getProjectTitleData() {
        request({ apiService.getProjecTitle() }, titleData)
    }

    fun getProjectData(isRefresh: Boolean, cid: Int, isNew: Boolean = false) {
        if (isRefresh) {
            pageNo = if (isNew) 0 else 1
        }
        request({ HttpRequestCoroutine.getProjectData(pageNo, cid, isNew) }, {
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
            projectDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            projectDataState.value = listDataUiState
        })
    }
}