package com.kekshi.mywanandroid.model

import com.kekshi.library.paging.BasePagingViewModel
import com.kekshi.mywanandroid.bean.ArticleBean
import com.kekshi.mywanandroid.paging.ArticleRepository

class ArticleViewModel(articleResposity: ArticleRepository) : BasePagingViewModel<ArticleBean>(articleResposity) {
}