package org.d3if0031.unsplash.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.d3if0031.unsplash.data.InMemoryImageDataSource
import org.d3if0031.unsplash.data.UnsplashAPI
import org.d3if0031.unsplash.data.model.ImageResponse
import org.d3if0031.unsplash.ui.feed.FeedViewState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val unsplashAPI: UnsplashAPI
) : ViewModel() {

    companion object {
        private const val TAG = "VMMain"
    }

    private val inMemoryImage = InMemoryImageDataSource

    private val _images = MutableLiveData<List<ImageResponse>>()
    val images: LiveData<List<ImageResponse>> = _images

    private val _selectedImage = MutableLiveData<ImageResponse>()
    val selectedImage: LiveData<ImageResponse> = _selectedImage

    private val _viewState = MutableLiveData<FeedViewState>()
    val viewState: LiveData<FeedViewState> = _viewState

    private var searchKeyword: String = ""

    fun getFeed(forceRefresh: Boolean = false) = viewModelScope.launch {
        if (!forceRefresh) {
            _images.value = inMemoryImage.getAsList()
            return@launch
        }

        updateState(isLoading = true)

        try {
            val responses = unsplashAPI.getFeed()
            _images.value = responses
            inMemoryImage.addAll(responses)

            updateState(isLoading = false)
            Log.i(TAG, "getFeed: ${responses.size}")
        } catch (e: Exception) {
            updateState(error = e)
            Log.e(TAG, "getFeed: ${e.message}", e)
        }
    }

    fun searchImage(page: Int = 1) = viewModelScope.launch {
        updateState(isLoading = true)

        try {
            val response = unsplashAPI.search(page, searchKeyword)
            _images.value = response.results

            updateState(isLoading = false)
            Log.i(TAG, "searchImage: ${response.results.size}")
        } catch (e: Exception) {
            updateState(error = e)
            Log.e(TAG, "searchImage: ${e.message}", e)
        }
    }

    fun refresh() {
        if (searchKeyword.isNotEmpty()) {
            searchImage(1)
        } else {
            getFeed(true)
        }
    }

    fun setSelectedImage(imageResponse: ImageResponse) {
        updateState(imageSelected = true)
        _selectedImage.value = imageResponse
        updateState(imageSelected = false)
    }

    private fun updateState(
        isLoading: Boolean = false,
        error: Exception? = null,
        imageSelected: Boolean = false,
    ) {
        _viewState.value = FeedViewState(
            isLoading = isLoading,
            error = error,
            imageSelected = imageSelected
        )
    }

    fun setSearchKeyword(input: String) {
        searchKeyword = input
        searchImage(1)
    }

}