package com.kekshi.mywanandroid.ui

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View


class BottomBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    //layoutDependsOn()：使用该Behavior的View要监听哪个类型的View的状态变化。
    // 其中参数parant代表CoordinatorLayout， child代表使用该Behavior的View，dependency代表要监听的View。
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    //当被监听的View状态变化时会调用该方法，参数和上一个方法一致。
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val distance = ((dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior as AppBarLayout.Behavior).topAndBottomOffset
        child.translationY = -distance.toFloat()
        return true
    }
}