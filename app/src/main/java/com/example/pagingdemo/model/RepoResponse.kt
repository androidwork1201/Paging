package com.example.pagingdemo.model

import com.google.gson.annotations.SerializedName

/**將Repo class 包裝成List*/
class RepoResponse {

    @SerializedName("items") val item: List<Repo> = emptyList()
}