package org.d3if0031.unsplash.ui.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.d3if0031.unsplash.R
import org.d3if0031.unsplash.databinding.FragmentFeedBinding
import org.d3if0031.unsplash.ui.MainViewModel

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

    companion object {
        private var isFirstOpen = true
    }

    private lateinit var binding: FragmentFeedBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var feedAdapter: FeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedBinding.bind(view)
        feedAdapter = FeedAdapter(viewModel)

        if (isFirstOpen) {
            viewModel.getFeed(true)
            isFirstOpen = false
        }

        binding.rvFeed.adapter = feedAdapter

        viewModel.images.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }

        binding.srlFeed.setOnRefreshListener {
            viewModel.getFeed()
        }

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_searchFragment)
        }

        val snackbar = Snackbar
            .make(binding.root, "", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                viewModel.refresh()
            }

        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.srlFeed.isRefreshing = it.isLoading

            if (it.imageSelected) {
                findNavController().navigate(R.id.action_feedFragment_to_imageDetailFragment)
            }

            if (it.error != null) {
                snackbar.let { sb ->
                    sb.setText(it.error?.message.toString())
                    sb.show()
                }
            } else {
                snackbar.dismiss()
            }
        }
    }

}
