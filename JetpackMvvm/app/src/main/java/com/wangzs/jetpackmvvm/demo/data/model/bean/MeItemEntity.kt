package com.wangzs.jetpackmvvm.demo.data.model.bean

import com.wangzs.jetpackmvvm.demo.data.model.enums.MeItemType

/**
 * @author : wangzs
 * @date   : 2020/6/11
 *
 */
data class MeItemEntity(
    var itemType: MeItemType,
    var itemPosition: Int,
    var data: Any
)