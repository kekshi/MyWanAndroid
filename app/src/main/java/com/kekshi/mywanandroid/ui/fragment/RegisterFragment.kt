package com.kekshi.mywanandroid.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kekshi.library.utils.PreferencesUtil
import com.kekshi.library.utils.ToastUtils
import com.kekshi.mywanandroid.R
import com.kekshi.mywanandroid.model.LoginModel
import com.kekshi.mywanandroid.respository.LoginRespository
import com.kekshi.mywanandroid.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private lateinit var model: LoginModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        model = ViewModelProviders.of(activity!!, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginModel(LoginRespository()) as T
            }

        })[LoginModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {

            register()
        }

        model.register.observe(this, Observer {
            if (it?.errorCode == 0) {
                startActivity(Intent(activity, MainActivity::class.java))
                var userId: Int by PreferencesUtil("userId", 0)
                var userLogin: Boolean by PreferencesUtil<Boolean>("login", false)
                var userName: String by PreferencesUtil<String>("userName", "Android")
                userId = it.data?.id!!
                userName = it.data?.username!!
                userLogin = true
                activity?.finish()
            } else {
                ToastUtils.showToast(it?.errorMsg!!)
            }
        })
    }

    private fun register() {
        val account = tie_phone_register.text?.trim().toString()
        val password = tie_pswd_register.text?.trim().toString()
        val password_re = tie_pswd_register_re.text?.trim().toString()

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password_re)) {
            ToastUtils.showToast(getString(R.string.login_empty))
        } else {
            model.register(account, password, password_re)
        }
    }
}