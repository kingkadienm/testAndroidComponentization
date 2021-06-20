package com.wangzs.jetpackmvvm.demo.ui.fragment.login

import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.appViewModel
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.app.ext.hideSoftKeyboard
import com.wangzs.jetpackmvvm.demo.app.ext.initClose
import com.wangzs.jetpackmvvm.demo.app.ext.showMessage
import com.wangzs.jetpackmvvm.demo.app.util.CacheUtil
import com.wangzs.jetpackmvvm.demo.app.util.SettingUtil
import com.wangzs.jetpackmvvm.demo.databinding.FragmentLoginBinding
import com.wangzs.jetpackmvvm.demo.viewmodel.request.RequestLoginRegisterViewModel
import com.wangzs.jetpackmvvm.demo.viewmodel.state.LoginRegisterViewModel
import com.wangzs.jetpackmvvm.ext.nav
import com.wangzs.jetpackmvvm.ext.navigateAction
import com.wangzs.jetpackmvvm.ext.parseState

/**
 * 作者　: wangzs
 * 时间　: 2019/12/23
 * 描述　: 登录页面
 */
class LoginFragment : BaseFragment<LoginRegisterViewModel, FragmentLoginBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_login

    override fun initView(savedInstanceState: Bundle?) {
        addLoadingObserve(requestLoginRegisterViewModel)
        mDatabind.viewmodel = mViewModel

        mDatabind.click = ProxyClick()

        toolbar.initClose("登录") {
            nav().navigateUp()
        }
        //设置颜色跟主题颜色一致
        appViewModel.appColor.value?.let {
            SettingUtil.setShapColor(loginSub, it)
            loginGoregister.setTextColor(it)
            toolbar.setBackgroundColor(it)
        }
    }

    override fun createObserver() {

        requestLoginRegisterViewModel.loginResult.observe(viewLifecycleOwner,Observer { resultState ->
                parseState(resultState, {
                    //登录成功 通知账户数据发生改变鸟
                    CacheUtil.setUser(it)
                    CacheUtil.setIsLogin(true)
                    appViewModel.userInfo.value = it
                    nav().navigateUp()
                }, {
                    //登录失败
                    showMessage(it.errorMsg)
                })
            })
    }

    inner class ProxyClick {

        fun clear() {
            mViewModel.username.set("")
        }

        fun login() {
            when {
                mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                else -> requestLoginRegisterViewModel.loginReq(
                    mViewModel.username.get(),
                    mViewModel.password.get()
                )
            }
        }

        fun goRegister() {
            hideSoftKeyboard(activity)
            nav().navigateAction(R.id.action_loginFragment_to_registerFrgment)
        }

        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }
}