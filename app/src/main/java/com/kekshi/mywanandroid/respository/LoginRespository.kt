package com.kekshi.mywanandroid.respository

import android.arch.lifecycle.MutableLiveData
import com.example.administrator.wanandroid.http.RetrofitApi
import com.kekshi.library.helper.RxHelper
import com.kekshi.mywanandroid.bean.BaseBean
import com.kekshi.mywanandroid.bean.LoginBean

class LoginRespository {
    val login = MutableLiveData<BaseBean<LoginBean>>()
    val register = MutableLiveData<BaseBean<LoginBean>>()

    // login 值改变时通知观察者
    fun login(account: String, psw: String) {
        RetrofitApi.instence.login(account, psw)
            .compose(RxHelper.rxSchedulerHelper())
            .subscribe({
                login.value = it
            }, {
                login.value = BaseBean(LoginBean(), 500, it.message)
            })
    }

    fun register(account: String, psw: String, rpsw: String) {

        RetrofitApi.instence.register(account, psw, rpsw)
            .compose(RxHelper.rxSchedulerHelper())
            .subscribe({
                register.value = it
            }, {
                register.value = BaseBean(LoginBean(), 500, it.message)
            })

    }


}