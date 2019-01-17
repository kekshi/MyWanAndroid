package com.kekshi.library.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kekshi.library.global.GlobalApplication
import io.reactivex.disposables.CompositeDisposable

abstract class BaseCompatFragment : Fragment() {
    protected lateinit var mActivity: BaseCompatActivity
    private var mCompositeDisposable: CompositeDisposable? = null
    protected lateinit var mApplication: GlobalApplication
    /**
     * 无网状态—>有网状态 的自动重连操作，子类可重写该方法
     */
    open fun doReConnected() {
        lazyLoadData()
    }

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false


    /**
     * 获取xml文件
     *
     * @return
     */
    abstract val layoutId: Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            loadDataPrepare()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        mCompositeDisposable = CompositeDisposable()
        prepare()
        initUI(view, savedInstanceState)
        loadDataPrepare()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearCompositeDisposable()
    }

    private fun loadDataPrepare() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoadData()
            hasLoadData = true
        }
    }

    /**
     * 在监听器之前把数据准备好
     */
    open fun prepare() {
        mApplication = mActivity.application as GlobalApplication
    }

    /**
     * 取消订阅
     */
    private fun clearCompositeDisposable() {
        mCompositeDisposable?.clear()
    }

    /**
     * [页面跳转]
     *
     * @param clz    要跳转的Activity
     * @param intent intent
     */
    fun startActivity(clz: Class<*>, intent: Intent) {
        intent.setClass(mActivity, clz)
        startActivity(intent)
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz    要跳转的Activity
     * @param bundle bundel数据
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(mActivity, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)

    }

    /**
     * 懒加载，且只加载一次
     * */
    abstract fun lazyLoadData()

    abstract fun initUI(view: View, savedInstanceState: Bundle?)
}