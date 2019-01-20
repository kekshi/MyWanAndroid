package com.kekshi.mywanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.kekshi.library.base.BaseCompatFragment
import com.kekshi.mywanandroid.R

/**项目fragment*/
class ProjectFragment : BaseCompatFragment() {
    override val layoutId: Int = R.layout.fragment_project

    override fun lazyLoadData() {
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment PageFragment.
         */
        fun newInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }
}