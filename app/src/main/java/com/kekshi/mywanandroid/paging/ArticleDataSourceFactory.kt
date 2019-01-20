package com.kekshi.mywanandroid.paging

import com.example.administrator.wanandroid.http.Api
import com.kekshi.library.paging.BaseDataSourceFactory
import com.kekshi.library.paging.BaseItemDataSource
import com.kekshi.mywanandroid.bean.ArticleBean
import java.util.concurrent.Executor

/**
 * @author  : Alex
 * @date    : 2018/08/31
 * @version : V 2.0.0
 */
class ArticleDataSourceFactory(private val api: Api, private val retryExecutor: Executor) : BaseDataSourceFactory<Int, ArticleBean>() {

    override fun createDataSource(): BaseItemDataSource<Int, ArticleBean> {
        return ArticleDataSource(api, retryExecutor)
    }

}