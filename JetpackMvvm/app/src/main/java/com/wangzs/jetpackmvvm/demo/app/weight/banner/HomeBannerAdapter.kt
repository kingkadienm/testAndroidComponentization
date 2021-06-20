package com.wangzs.jetpackmvvm.demo.app.weight.banner

/**
 * 作者　: wangzs
 * 时间　: 2020/2/20
 * 描述　:
 */

import android.view.View
import com.zhpan.bannerview.BaseBannerAdapter
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.data.model.bean.BannerResponse

class HomeBannerAdapter : BaseBannerAdapter<BannerResponse, HomeBannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_itemhome
    }

    override fun createViewHolder(itemView: View, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: HomeBannerViewHolder?,
        data: BannerResponse?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }


}
