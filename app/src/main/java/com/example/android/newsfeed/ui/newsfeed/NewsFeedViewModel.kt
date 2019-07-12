package com.example.android.newsfeed.ui.newsfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.newsfeed.data.network.FeedApi
import com.example.android.newsfeed.data.model.FeedResponse
import kotlinx.coroutines.*

class NewsFeedViewModel() : ViewModel() {
    private val job = Job()
    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)

    private val _newsFeed = MutableLiveData<List<FeedResponse>>()
    val newsFeed: LiveData<List<FeedResponse>>
        get() = _newsFeed

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus>
        get() = _networkStatus

    init {
        getFeeds()
    }

    fun getFeeds() {
        viewModelScope.launch {
            val feedsDeferred = FeedApi.retrofitService.getFeeds()
            _networkStatus.value = NetworkStatus.LOADING
            try {
                val feeds = feedsDeferred.await()
                _networkStatus.value = NetworkStatus.DONE
                _newsFeed.value = feeds
            } catch (e: Exception) {
                _networkStatus.value = NetworkStatus.ERROR
            }
        }
    }

    enum class NetworkStatus { LOADING, ERROR, DONE }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}