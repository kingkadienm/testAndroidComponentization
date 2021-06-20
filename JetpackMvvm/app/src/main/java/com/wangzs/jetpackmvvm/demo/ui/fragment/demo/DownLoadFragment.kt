package com.wangzs.jetpackmvvm.demo.ui.fragment.demo

import android.os.Bundle
import androidx.lifecycle.Observer
import com.wangzs.jetpackmvvm.demo.R
import com.wangzs.jetpackmvvm.demo.app.base.BaseFragment
import com.wangzs.jetpackmvvm.demo.app.ext.initClose
import com.wangzs.jetpackmvvm.demo.app.ext.showMessage
import com.wangzs.jetpackmvvm.demo.databinding.FragmentDownloadBinding
import com.wangzs.jetpackmvvm.demo.viewmodel.state.DownloadViewModel
import com.wangzs.jetpackmvvm.ext.download.DownloadResultState
import com.wangzs.jetpackmvvm.ext.download.FileTool
import com.wangzs.jetpackmvvm.ext.download.FileTool.getBasePath
import com.wangzs.jetpackmvvm.ext.nav
import com.wangzs.jetpackmvvm.ext.util.logd


/**文件下载 示例 框架自带的，功能比较简单，肯定木得三方库那么强大
 * @author : wangzs
 * @date   : 2020/7/13
 */
class DownLoadFragment : BaseFragment<DownloadViewModel, FragmentDownloadBinding>() {

    override fun layoutId() = R.layout.fragment_download

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        toolbar.initClose("框架自带普通下载") {
            nav().navigateUp()
        }
    }

    override fun createObserver() {
        mViewModel.downloadData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DownloadResultState.Pending -> {
                    //开始下载
                    "开始下载".logd()
                }
                is DownloadResultState.Progress -> {
                    //下载中
                    downloadProgressBar.progress = it.progress
                    "下载中 ${it.soFarBytes}/${it.totalBytes}".logd()
                    downloadProgress.text = "${it.progress}%"
                    downloadSize.text ="${FileTool.bytes2kb(it.soFarBytes)}/${FileTool.bytes2kb(it.totalBytes)}"
                }
                is DownloadResultState.Success -> {
                    //下载成功
                    downloadProgressBar.progress = 100
                    downloadProgress.text = "100%"
                    downloadSize.text ="${FileTool.bytes2kb(it.totalBytes)}/${FileTool.bytes2kb(it.totalBytes)}"
                    showMessage("下载成功--文件地址：${it.filePath}")
                }
                is DownloadResultState.Pause -> {
                    showMessage("下载暂停")
                }
                is DownloadResultState.Error -> {
                    //下载失败
                    showMessage("错误信息:${it.errorMsg}")
                }
            }
        })
    }

    inner class ProxyClick {
        fun download() {
            //普通下载
            mViewModel.downloadApk(
                getBasePath(),
                "https://down.qq.com/qqweb/QQlite/Android_apk/qqlite_4.0.1.1060_537064364.apk",
                "qq"
            )
        }

        fun cancel() {
            mViewModel.downloadCancel("qq")
        }

        fun pause() {
            mViewModel.downloadPause("qq")
        }
    }


}