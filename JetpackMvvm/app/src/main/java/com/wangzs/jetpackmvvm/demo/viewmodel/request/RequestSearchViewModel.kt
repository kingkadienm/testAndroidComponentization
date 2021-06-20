package com.wangzs.jetpackmvvm.demo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.demo.app.network.apiService
import com.wangzs.jetpackmvvm.demo.app.util.CacheUtil
import com.wangzs.jetpackmvvm.demo.data.model.bean.ApiPagerResponse
import com.wangzs.jetpackmvvm.demo.data.model.bean.AriticleResponse
import com.wangzs.jetpackmvvm.demo.data.model.bean.SearchResponse
import com.wangzs.jetpackmvvm.ext.launch
import com.wangzs.jetpackmvvm.ext.request
import com.wangzs.jetpackmvvm.state.ResultState

/**
 * 作者　: wangzs
 * 时间　: 2020/2/29
 * 描述　:
 */
class RequestSearchViewModel : BaseViewModel() {

    var pageNo = 0

    //搜索热词数据
    var hotData: MutableLiveData<ResultState<ArrayList<SearchResponse>>> = MutableLiveData()

    //搜索结果数据
    var seachResultData: MutableLiveData<ResultState<ApiPagerResponse<ArrayList<AriticleResponse>>>> =
        MutableLiveData()

    //搜索历史词数据
    var historyData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    /**
     * 获取热门数据
     */
    fun getHotData() {
        request({ apiService.getSearchData() }, hotData)
    }

    /**
     * 获取历史数据
     */
    fun getHistoryData() {
        launch({
            CacheUtil.getSearchHistoryData()
        }, {
            historyData.value = it
        }, {
            //获取本地历史数据出异常了
        })
    }

    /**
     * 根据字符串搜索结果
     */
    fun getSearchResultData(searchKey: String, isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request(
            { apiService.getSearchDataByKey(pageNo, searchKey) },
            seachResultData
        )
    }
}