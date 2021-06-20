package com.wangzs.jetpackmvvm.demo.app.weight.banner

/**
 * 作者　: wangzs
 * 时间　: 2020/2/20
 * 描述　:
 */

import android.view.View
import android.widget.TextView
import com.zhpan.bannerview.BaseViewHolder
import com.wangzs.jetpackmvvm.demo.R

class WelcomeBannerViewHolder(view: View) : BaseViewHolder<String>(view) {
    override fun bindData(data: String?, position: Int, pageSize: Int) {
        val textView = findView<TextView>(R.id.banner_text)
        textView.text = data
    }

}
