package com.kekshi.mywanandroid.bean

data class LoginBean(
    var email: String? = null,
    var icon: String? = null,
    var id: Int = 0,
    var password: String? = null,
    var token: String? = null,
    var type: Int = 0,
    var username: String? = null,
    var chapterTops: List<*>? = null,
    var collectIds: List<Int>? = null
)
