package com.example.pagingdemo.model

import com.google.gson.annotations.SerializedName


/**Json格式相關內容*/
data class Repo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val count: Int,
)