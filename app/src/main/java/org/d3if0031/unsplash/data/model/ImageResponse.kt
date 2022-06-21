package org.d3if0031.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val id: String = "",

    @SerializedName("created_at")
    val createdAt: String = "",

    @SerializedName("updated_at")
    val updatedAt: String = "",

    @SerializedName("width")
    val width: Int = 0,

    @SerializedName("height")
    val height: Int = 0,

    @SerializedName("description")
    val description: String = "",

    @SerializedName("urls")
    val urls: Urls = Urls(),

    @SerializedName("likes")
    val likes: Int = 0,

    @SerializedName("user")
    val user: User = User(),
)
