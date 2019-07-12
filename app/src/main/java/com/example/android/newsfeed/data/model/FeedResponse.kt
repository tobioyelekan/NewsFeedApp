package com.example.android.newsfeed.data.model

data class FeedResponse(
    val id: String,
    val talent_id: String,
    val description: String,
    val media_url: String,
    val thumbnail_url: String,
    val created_at: String,
    val user_info: UserInfo,
    val comments: List<Comments>,
    val comment_count: String,
    val like_count: String,
    val post_shares_count: String
)

data class UserInfo(
    val user_id: String,
    val fullname: String,
    val username: String,
    val avatar: String
)

data class Comments(
    val id: String,
    val comment: String,
    val created_at: String,
    val commenter_id: String,
    val commenter_avatar: String?,
    val commenter_name: String
)