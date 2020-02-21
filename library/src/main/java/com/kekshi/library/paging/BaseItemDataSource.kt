package com.kekshi.library.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import java.util.concurrent.Executor

/**
 *
 * 组件提供了一下三种模式的DataSource，我们在使用时只需根据自己的需求选择何时的实现子类：

    PageKeyedDataSource：如果页面需要实现上一页、下一页，需要将请求的Token传递到下一步
    ItemKeyedDataSource：程序需要根据上一条数据信息（ID）获取下一条数据时
    PositionalDataSource：需要从数据存储中选择的任何位置获取数据页；例如，请求可能返回以位置1200开头的20个数据项
 */
abstract class BaseItemDataSource<T, M>(private var retryExecutor: Executor) : ItemKeyedDataSource<T, M>() {
    /**
     * 控制重新加载
     */
    private var retry: (() -> Any)? = null
    /**
     * 检测网络状态
     */
    val networkStatus by lazy {
        MutableLiveData<Resource<String>>()
    }
    /**
     * 检测加载状态
     */
    val refreshStatus by lazy {
        MutableLiveData<Resource<String>>()
    }

    /**
     * 重新加载的方法
     */
    fun retryFailed() {
        val preRetry = retry
        retry = null
        preRetry.let {
            retryExecutor.execute {
                it?.invoke()
            }
        }
    }

    /**
     * 初始化时的加载
     */
    override fun loadInitial(params: LoadInitialParams<T>, callback: LoadInitialCallback<M>) {
        refreshStatus.postValue(Resource.loading())
        setLoadInitial(params, callback)
    }

    /**
     * 加载更多
     */
    override fun loadAfter(params: LoadParams<T>, callback: LoadCallback<M>) {
        networkStatus.postValue(Resource.loading())
        setLoadAfterResult(params, callback)
    }

    override fun loadBefore(params: LoadParams<T>, callback: LoadCallback<M>) {

    }

    override fun getKey(item: M) = setKey(item)

    /**
     * 加载、刷新成功
     */
    fun refreshSuccess() {
        refreshStatus.postValue(Resource.success())
        retry = null
    }

    /**
     * 网络状态可用
     */
    fun networkSuccess() {
        retry = null
        networkStatus.postValue(Resource.success())
    }

    /**
     * 加载、刷新失败
     */
    fun networkFailed(msg: String?, params: LoadParams<T>, callback: LoadCallback<M>) {
        networkStatus.postValue(Resource.error(msg))
        retry = {
            loadAfter(params, callback)
        }
    }

    /**
     * 网络状态不可用
     */
    fun refreshFailed(msg: String?, params: LoadInitialParams<T>, callback: LoadInitialCallback<M>) {
        refreshStatus.postValue(Resource.error(msg))
        retry = {
            loadInitial(params, callback)
        }
    }


    /**
     * 设置区分item的标志
     */
    abstract fun setKey(item: M): T

    /**
     * 加载更多
     */
    abstract fun setLoadAfterResult(params: LoadParams<T>, callback: LoadCallback<M>)

    /**
     * 初始化刷新
     */
    abstract fun setLoadInitial(params: LoadInitialParams<T>, callback: LoadInitialCallback<M>)
}