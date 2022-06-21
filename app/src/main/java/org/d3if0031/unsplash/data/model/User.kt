package org.d3if0031.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("username")
    val username: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("profile_image")
    val profileImage: ProfileImage = ProfileImage(),
)
