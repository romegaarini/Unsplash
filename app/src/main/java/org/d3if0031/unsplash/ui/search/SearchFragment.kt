package org.d3if0031.unsplash.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.d3if0031.unsplash.R
import org.d3if0031.unsplash.databinding.FragmentSearchBinding
import org.d3if0031.unsplash.ui.MainViewModel

class SearchFragment : BottomSheetDialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        val editText = binding.etKeyword
        val button = binding.btnSearch

        button.setOnClickListener {
            val input = editText.text.toString()

            if (input.isEmpty()) {
                editText.error = "Keyword can't be blank"
            } else {
                editText.error = ""
            }

            viewModel.setSearchKeyword(input)
            this.dismiss()
        }
    }
}