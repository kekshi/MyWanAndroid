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
            .setPageSize(pageSize)
            .setPrefetchDistance(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        val stuDataSourceFractory = createDataBaseFactory()
        val pagedList = LivePagedListBuilder(stuDataSourceFractory, pageConfig)
        val refreshState = Transformations.switchMap(stuDataSourceFractory.sourceLivaData){it.refreshStatus}
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