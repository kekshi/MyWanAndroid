package com.kekshi.mywanandroid.paging

import com.example.administrator.wanandroid.http.Api
import com.kekshi.library.paging.BaseDataSourceFactory
import com.kekshi.library.paging.BaseRepository
import com.kekshi.mywanandroid.bean.ArticleBean
import java.util.concurrent.Executor

class ArticleRepository(private val api: Api, private val retryExecutor: Executor) : BaseRepository<Int, ArticleBean>() {
    override fun createDataBaseFactory(): BaseDataSourceFactory<Int, ArticleBean> {
        return ArticleDataSourceFactory(api, retryExecutor)
    }
}