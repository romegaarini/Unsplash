package org.d3if0031.unsplash.ui.image_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.d3if0031.unsplash.R
import org.d3if0031.unsplash.databinding.FragmentImageDetailBinding
import org.d3if0031.unsplash.ui.MainViewModel

@AndroidEntryPoint
class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentImageDetailBinding

    private var isDetailShown: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageDetailBinding.bind(view)

        binding.ivImage.setOnClickListener {
            isDetailShown = !isDetailShown
            binding.clItemsContainer.visibility = if (isDetailShown) View.VISIBLE else View.GONE
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.selectedImage.observe(viewLifecycleOwner) {
            binding.imageResponse = it
        }
    }

}