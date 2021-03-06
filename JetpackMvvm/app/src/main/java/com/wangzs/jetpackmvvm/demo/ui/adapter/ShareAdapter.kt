package com.wangzs.jetpackmvvm.demo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.ext.setAdapterAnimation
import com.wangzs.jetpackmvvm.demo.app.util.SettingUtil
import com.wangzs.jetpackmvvm.demo.data.model.bean.AriticleResponse


/**
 * 分享的文章 adapter
 * @Author:         wangzs
 * @CreateDate:     2019/9/1 9:52
 */
class ShareAdapter(data: ArrayList<AriticleResponse>) :
    BaseQuickAdapter<AriticleResponse, BaseViewHolder>(
        R.layout.item_share_ariticle, data
    ) {
    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(helper: BaseViewHolder, item: AriticleResponse) {
        //赋值
        item.run {
            helper.setText(R.id.item_share_title, title)
            helper.setText(R.id.item_share_date, niceDate)
        }
    }
}


