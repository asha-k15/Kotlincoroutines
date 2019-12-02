package com.asha.android.kotlincoroutines.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.asha.android.kotlincoroutines.models.RedditPost
import com.asha.android.kotlincoroutines.repositories.PostsDataSource

class MainViewModel : ViewModel() {
    var postsLiveData  :LiveData<PagedList<RedditPost>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts():LiveData<PagedList<RedditPost>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RedditPost> {

        val dataSourceFactory = object : DataSource.Factory<String, RedditPost>() {
            override fun create(): DataSource<String, RedditPost> {
                return PostsDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder<String, RedditPost>(dataSourceFactory, config)
    }
}