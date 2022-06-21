package org.d3if0031.unsplash.data

import org.d3if0031.unsplash.data.model.ImageResponse

object InMemoryImageDataSource {

    private val images = mutableMapOf<String, ImageResponse>()

    fun add(response: ImageResponse) {
        images[response.id] = response
    }

    fun addAll(responses: List<ImageResponse>) {
        responses.forEach {
            add(it)
        }
    }

    fun getAsList(): List<ImageResponse> {
        return images.map {
            it.value
        }
    }

    fun clear() {
        images.clear()
    }

}