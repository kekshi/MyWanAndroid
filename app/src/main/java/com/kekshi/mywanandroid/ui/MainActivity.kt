package com.kekshi.mywanandroid.ui

import android.os.Bundle
import android.view.View
import com.kekshi.library.base.BaseCompatActivity
import com.kekshi.mywanandroid.R
import kotlinx.android.synthetic.main.toolbar_.*

class MainActivity : BaseCompatActivity() {
    override fun onErrorViewClick(v: View?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

}
