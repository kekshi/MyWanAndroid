package com.example.administrator.wanandroid.listener

/**
 * @author  : Alex
 * @date    : 2018/09/05
 * @version : V 2.0.0
 *RecycleView单击事件监听接口
 */
interface OnItemClickListener<T> {
    /**
     *单击事件
     */
    fun itemClick(t: T, position: Int)
}


interface OnItemNavigationClickListener {
    /**
     *单击事件
     */
//    fun itemClick(view: View, position: Int, parent : FlowLayout, children : List<CategoryBean>)
}