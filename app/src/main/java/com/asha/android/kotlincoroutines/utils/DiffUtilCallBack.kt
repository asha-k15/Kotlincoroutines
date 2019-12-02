package com.asha.android.kotlincoroutines.utils


import androidx.recyclerview.widget.DiffUtil
import com.asha.android.kotlincoroutines.models.RedditPost

class DiffUtilCallBack : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
    }

}