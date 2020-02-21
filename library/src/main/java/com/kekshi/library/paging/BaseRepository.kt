package com.kekshi.library.paging

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

/**
 *  BaseRepository 配置并实例化LivePagedListBuilder（）对象，根据设定的监听状态和数据，封装List<M>对象
 * T :
 * */
abstract class BaseRepository<T, M> : Repository<M> {
    /**
     * 配置PagedList.Config实例化List<M>对象，初始化加载的数量默认为{@link #pageSize} 的两倍
     *  @param pageSize : 每次加载的数量
     */
    override fun getDataList(pageSize: Int): Listing<M> {
        val pageConfig = PagedList.Config.Builder()
            .setPageSize(pageSize)//每次加载数据的数量
            .setPrefetchDistance(pageSize)//预取距离，给定UI中最后一个可见的Item，超过这个item读取下一段数据
            .setInitialLoadSizeHint(pageSize * 2)//定义在第一次加载时加载多少项
            .setEnablePlaceholders(true)//UI占位符
            .build()

        val stuDataSourceFractory = createDataBaseFactory()
        val pagedList = LivePagedListBuilder(stuDataSourceFractory, pageConfig)
        val refreshState = Transformations.switchMap(stuDataSourceFractory.sourceLivaData) { it.refreshStatus }
        val networkStatus = Transformations.switchMap(stuDataSourceFractory.sourceLivaData) { it.networkStatus }

        return Listing<M>(pagedList.build(),
            networkStatus,
            refreshState,
            refresh = {
                stuDataSourceFractory.sourceLivaData.value?.invalidate()
            },
            retry = {
                stuDataSourceFractory.sourceLivaData.value?.retryFailed()
            })
    }

    /**
     * 创建DataSourceFactory
     */
    abstract fun createDataBaseFactory(): BaseDataSourceFactory<T, M>
}