package com.kekshi.mywanandroid.adapter

import android.support.v7.util.DiffUtil
import com.kekshi.mywanandroid.bean.ArticleBean

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticleBean>() {
    override fun areItemsTheSame(p0: ArticleBean, p1: ArticleBean): Boolean {
        return p0.id == p1.id
    }

    override fun areContentsTheSame(p0: ArticleBean, p1: ArticleBean): Boolean {
        return p0 == p1
    }
}