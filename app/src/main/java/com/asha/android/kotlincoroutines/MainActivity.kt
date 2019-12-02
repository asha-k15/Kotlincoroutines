package com.asha.android.kotlincoroutines


import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.asha.android.kotlincoroutines.adapters.RedditPostsAdapter
import com.asha.android.kotlincoroutines.viewmodels.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val redditPostsAdapter = RedditPostsAdapter()
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)

        //initialize the view model
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        observeLiveData()
        initializeList()

    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        mainViewModel.getPosts().observe(this, Observer {
            redditPostsAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = redditPostsAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}