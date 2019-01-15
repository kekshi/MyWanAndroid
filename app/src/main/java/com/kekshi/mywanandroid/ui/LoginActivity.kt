package com.kekshi.mywanandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.kekshi.library.base.BaseCompatActivity
import com.kekshi.mywanandroid.R

class LoginActivity : BaseCompatActivity() {
    override fun onErrorViewClick(v: View?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.activity_login

    //ActionBar 点击事件
//    override fun onSupportNavigateUp(): Boolean {
//        return Navigation.findNavController(this, R.id.fragment_navigation_login).navigateUp()
//    }
}