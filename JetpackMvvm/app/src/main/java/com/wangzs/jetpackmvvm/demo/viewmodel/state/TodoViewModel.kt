package com.wangzs.jetpackmvvm.demo.viewmodel.state

import com.wangzs.jetpackmvvm.base.viewmodel.BaseViewModel
import com.wangzs.jetpackmvvm.callback.databind.IntObservableField
import com.wangzs.jetpackmvvm.callback.databind.StringObservableField
import com.wangzs.jetpackmvvm.demo.data.model.enums.TodoType
import com.wangzs.jetpackmvvm.ext.launch

/**
 * 作者　: wangzs
 * 时间　: 2020/3/11
 * 描述　:
 */
class TodoViewModel : BaseViewModel() {

    //标题
    var todoTitle = StringObservableField()

    //内容
    var todoContent =
        StringObservableField()

    //时间
    var todoTime = StringObservableField()

    //优先级
    var todoLeve =
        StringObservableField(TodoType.TodoType1.content)

    //优先级颜色
    var todoColor =
        IntObservableField(TodoType.TodoType1.color)

    fun xx(): Unit {
        launch({

        },{

        })
    }
}