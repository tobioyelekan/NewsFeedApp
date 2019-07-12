package com.example.android.newsfeed.ui

import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.newsfeed.ui.newsfeed.NewsFeedViewModel.NetworkStatus
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:loadImg")
fun loadImg(imgView: CircleImageView, url: String?) {
    url?.let {
        Glide.with(imgView.context).load(url)
            .into(imgView)
    }
}

@BindingAdapter("app:loadingStatus")
fun loadingStatus(view: ProgressBar, status: NetworkStatus?) {
    status?.let {
        when (it) {
            NetworkStatus.DONE -> view.visibility = View.GONE
            NetworkStatus.LOADING -> view.visibility = View.VISIBLE
            NetworkStatus.ERROR -> view.visibility = View.GONE
        }
    }
}

@BindingAdapter("app:playVideo")
fun playVideo(videoView: VideoView, url: String?) {
    videoView.setVideoURI(Uri.parse(url))

    videoView.start()

    videoView.setOnCompletionListener {
        videoView.stopPlayback()
    }
}