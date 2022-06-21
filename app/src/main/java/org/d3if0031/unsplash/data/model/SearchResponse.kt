package org.d3if0031.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total")
    val total: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0,

    @SerializedName("results")
    val results: List<ImageResponse> = listOf(),
)
