package com.kekshi.mywanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.kekshi.library.base.BaseCompatFragment
import com.kekshi.mywanandroid.R
import com.kekshi.mywanandroid.adapter.ViewPageAdapter
import kotlinx.android.synthetic.main.fragment_page.*

/**首页fragment*/
class PageFragment : BaseCompatFragment() {
    override val layoutId: Int = R.layout.fragment_page

    private val supportFramentManager by lazy {
        activity?.supportFragmentManager
    }

    private val fragmentList = mutableListOf<BaseCompatFragment>()

    val adapter by lazy {
        ViewPageAdapter(supportFramentManager!!)
    }

    override fun lazyLoadData() {
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
        fragmentList.let {
            it.add(ArticleFragment.newInstance())
            it.add(CollectArticleFragment.newInstance())
            it.add(PlanArticleFragment.newInstance())
        }
        adapter.setFragments(fragmentList)

        vpMain.adapter = adapter
        vpMain.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(vpMain)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment PageFragment.
         */
        fun newInstance(): PageFragment {
            return PageFragment()
        }
    }
}