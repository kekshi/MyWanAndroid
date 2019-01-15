package com.kekshi.mywanandroid.model

import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.kekshi.mywanandroid.respository.LoginRespository

class LoginModel(val loginResposity: LoginRespository) : ViewModel() {

    //观察 Livedata(loginResposity.login) 的生命周期传递的信息，当信息改变时自动调用，并将结果返回
    //也可以不用这个，而直接用 Livedata。相当于把 LoginModel和 LoginRespository合并成一个类
    val loginState = Transformations.map(loginResposity.login) { it }
    val register = Transformations.map(loginResposity.register) { it }

    fun login(account: String, psw: String) {
        loginResposity.login(account, psw)
    }

    fun register(account: String, psw: String, rpsw: String) {
        loginResposity.register(account, psw, rpsw)
    }
}