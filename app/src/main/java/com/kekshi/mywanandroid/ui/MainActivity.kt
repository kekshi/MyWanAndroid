package com.kekshi.mywanandroid.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.kekshi.library.base.BaseCompatActivity
import com.kekshi.library.utils.PreferencesUtil
import com.kekshi.library.utils.ToastUtils
import com.kekshi.mywanandroid.R
import com.kekshi.mywanandroid.ui.fragment.KnowledgeFragment
import com.kekshi.mywanandroid.ui.fragment.NavigationFragment
import com.kekshi.mywanandroid.ui.fragment.PageFragment
import com.kekshi.mywanandroid.ui.fragment.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.toolbar_.*

const val bottomIndex = "Bottom_Index"

class MainActivity : BaseCompatActivity() {
    lateinit var pageFragment: PageFragment
    lateinit var knowledgeFragment: KnowledgeFragment
    lateinit var navigationFragment: NavigationFragment
    lateinit var projectFragment: ProjectFragment
    var currentIndex = 0

    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {

            R.id.page -> {
                showFragmentIndex(0)
                true
            }
            R.id.knowledge -> {
                showFragmentIndex(1)
                true
            }
            R.id.navigation -> {
                showFragmentIndex(2)
                true
            }
            R.id.project -> {
                showFragmentIndex(3)
                true
            }
            else -> {
                false
            }
        }
    }
    private val drawNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {

        Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
        when (it.itemId) {

            R.id.logout -> {
                loginOut()
            }
            else -> {

            }
        }
        layout.closeDrawer(Gravity.START)
        true
    }

    private fun loginOut() {

    }

    private fun showFragmentIndex(index: Int) {
        val transaction = fragmentManager.beginTransaction()

        currentIndex = index
        when (index) {
            0 -> {
                toolbar.title = "首页"
                transaction.show(pageFragment)
                    .hide(knowledgeFragment)
                    .hide(navigationFragment)
                    .hide(projectFragment)
            }
            1 -> {
                toolbar.title = "知识"

                transaction.show(knowledgeFragment)
                    .hide(pageFragment)
                    .hide(navigationFragment)
                    .hide(projectFragment)
            }
            2 -> {
                toolbar.title = "导航"
                transaction.show(navigationFragment)
                    .hide(pageFragment)
                    .hide(knowledgeFragment)
                    .hide(projectFragment)

            }
            3 -> {
                toolbar.title = "项目"
                transaction.show(projectFragment)
                    .hide(pageFragment)
                    .hide(knowledgeFragment)
                    .hide(navigationFragment)

            }
            else -> {

            }
        }
        transaction.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {
//                startActivity(SearchActivity::class.java)
                ToastUtils.showToast("搜索")
            }
        }
        return true
    }

    override fun onErrorViewClick(v: View?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigationView.setNavigationItemSelectedListener(drawNavigationItemSelectedListener)
        val imageView = navigationView.getHeaderView(0).findViewById<ImageView>(R.id.ivHeadView)
        val textView = navigationView.getHeaderView(0).findViewById<TextView>(R.id.tvUserName)
        var userName: String by PreferencesUtil<String>("userName", "Android")
        textView.text = userName
        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            pageFragment = fragmentManager.getFragment(savedInstanceState, PageFragment::class.java.simpleName) as PageFragment
            knowledgeFragment = fragmentManager.getFragment(savedInstanceState, KnowledgeFragment::class.java.simpleName) as KnowledgeFragment
            navigationFragment = fragmentManager.getFragment(savedInstanceState, NavigationFragment::class.java.simpleName) as NavigationFragment
            projectFragment = fragmentManager.getFragment(savedInstanceState, ProjectFragment::class.java.simpleName) as ProjectFragment
            currentIndex = savedInstanceState.getInt(bottomIndex)
        } else {
            pageFragment = PageFragment.newInstance()
            knowledgeFragment = KnowledgeFragment.newInstance()
            navigationFragment = NavigationFragment.newInstance()
            projectFragment = ProjectFragment.newInstance()
        }

        if (!pageFragment.isAdded) {
            with(fragmentManager) {
                beginTransaction().add(R.id.frameLayout, pageFragment, PageFragment::class.java.simpleName)
                    .commit()
            }
        }

        if (!knowledgeFragment.isAdded) {
            with(fragmentManager) {
                beginTransaction().add(R.id.frameLayout, knowledgeFragment, ProjectFragment::class.java.simpleName)
                    .commit()
            }
        }
        if (!navigationFragment.isAdded) {
            with(fragmentManager) {
                beginTransaction().add(R.id.frameLayout, navigationFragment, NavigationFragment::class.java.simpleName)
                    .commit()
            }
        }
        if (!projectFragment.isAdded) {
            with(fragmentManager) {
                beginTransaction().add(R.id.frameLayout, projectFragment, ProjectFragment::class.java.simpleName)
                    .commit()
            }
        }
        showFragmentIndex(currentIndex)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.e("TAG", "onSaveInstanceState")
        if (pageFragment.isAdded) {
            fragmentManager.putFragment(outState!!, PageFragment::class.java.simpleName, pageFragment)
        }
        if (knowledgeFragment.isAdded) {
            fragmentManager.putFragment(outState!!, ProjectFragment::class.java.simpleName, knowledgeFragment)
        }
        if (navigationFragment.isAdded) {
            fragmentManager.putFragment(outState!!, NavigationFragment::class.java.simpleName, navigationFragment)
        }
        if (projectFragment.isAdded) {
            fragmentManager.putFragment(outState!!, ProjectFragment::class.java.simpleName, projectFragment)
        }

        outState?.putInt(bottomIndex, currentIndex)
    }
}
