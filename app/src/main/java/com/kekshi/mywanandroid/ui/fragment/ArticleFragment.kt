package com.kekshi.mywanandroid.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.administrator.wanandroid.http.RetrofitApi
import com.example.administrator.wanandroid.listener.OnItemClickListener
import com.kekshi.library.base.BaseCompatFragment
import com.kekshi.library.paging.Status
import com.kekshi.mywanandroid.R
import com.kekshi.mywanandroid.adapter.ArticleAdapter
import com.kekshi.mywanandroid.adapter.ArticleDiffUtil
import com.kekshi.mywanandroid.bean.ArticleBean
import com.kekshi.mywanandroid.model.ArticleViewModel
import com.kekshi.mywanandroid.paging.ArticleRepository
import com.kekshi.mywanandroid.utils.FIXED_EXECUTOR
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : BaseCompatFragment(), OnItemClickListener<ArticleBean> {
    override fun itemClick(t: ArticleBean, position: Int) {

    }

    override val layoutId: Int = R.layout.fragment_article

    private val adapter by lazy {
        ArticleAdapter(ArticleDiffUtil())
    }

    private val model by lazy {
        initModel()
    }

    private fun initModel(): ArticleViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val api = RetrofitApi.instence
                val resposity = ArticleRepository(api, FIXED_EXECUTOR)
                return ArticleViewModel(resposity) as T
            }
        })[ArticleViewModel::class.java]
    }

    override fun lazyLoadData() {
        model.setPageSize(5)
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
        initSwipe()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvArticle.adapter = adapter
        adapter.let { it.itemClickListener = this }
        model.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initSwipe() {
        swRefreshArticle.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE)
        swRefreshArticle.setOnRefreshListener { model.refresh() }
        model.refreshState.observe(this, Observer {
            swRefreshArticle.isRefreshing = it?.status == Status.LOADING
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment PageFragment.
         */
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }
}