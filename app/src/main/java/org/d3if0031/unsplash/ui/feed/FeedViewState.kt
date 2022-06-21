package org.d3if0031.unsplash.ui.feed

data class FeedViewState(
    var isLoading: Boolean = false,
    var error: Exception? = null,
    var imageSelected: Boolean = false,
)
