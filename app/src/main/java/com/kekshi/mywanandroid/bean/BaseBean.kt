package com.kekshi.mywanandroid.bean

/**
 * @author : Alex
 * @version : V 2.0.0
 * @date : 2018/10/19
 */
class BaseBean<T> {

    constructor(data: T, errorCode: Int, errorMsg: String?) {
        this.data = data
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }


    /**
     * data : null
     * errorCode : 0
     * errorMsg :
     */

    var data: T? = null
    var errorCode: Int = 0
    var errorMsg: String? = null
}
