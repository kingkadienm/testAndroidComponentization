package com.wangzs.jetpackmvvm.demo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.ext.setAdapterAnimation
import com.wangzs.jetpackmvvm.demo.app.util.ColorUtil
import com.wangzs.jetpackmvvm.demo.app.util.SettingUtil
import com.wangzs.jetpackmvvm.demo.data.model.bean.AriticleResponse
import com.wangzs.jetpackmvvm.ext.util.toHtml

class NavigationChildAdapter(data: ArrayList<AriticleResponse>) :
    BaseQuickAdapter<AriticleResponse, BaseViewHolder>(R.layout.flow_layout, data) {

    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(holder: BaseViewHolder, item: AriticleResponse) {
        holder.setText(R.id.flow_tag,item.title.toHtml())
        holder.setTextColor(R.id.flow_tag, ColorUtil.randomColor())
    }

}