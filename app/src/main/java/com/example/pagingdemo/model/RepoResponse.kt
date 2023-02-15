package com.example.pagingdemo.model

import com.google.gson.annotations.SerializedName

class RepoResponse {

    @SerializedName("items") val item: List<Repo> = emptyList()
}