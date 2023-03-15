package com.bbox.bboxjournal.presentation.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bbox.bboxjournal.R
import com.bbox.bboxjournal.databinding.FragmentHomeBinding
import com.bbox.bboxjournal.domain.usecase.ConvertDateTimeFormatUseCase
import com.bbox.bboxjournal.presentation.home.adapter.JournalListAdapter
import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.viewmodel.HomeViewModel
import com.bbox.bboxjournal.utils.makeGone
import com.bbox.bboxjournal.utils.makeVisible
import com.bbox.bboxjournal.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase
    private val adapter by lazy {
        JournalListAdapter(convertDateTimeFormatUseCase) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
        observeUiState()
        binding.fabAddJournal.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
        }
    }

    private fun setupListAdapter() {
        binding.rvJournal.adapter = adapter
    }

    private fun observeUiState() {
        viewModel.homeUiState.observe(viewLifecycleOwner) {
            when (it) {
                HomeViewState.Error -> {
                    showError()
                }
                HomeViewState.Loading -> {
                    showLoading()
                }
                is HomeViewState.Success -> {
                    populateDate(it.list)
                }
            }
        }
    }

    private fun showError() {
        with(binding) {
            rvJournal.makeGone()
            loader.makeGone()
            showToast(context, getString(R.string.something_went_wrong))
        }
    }

    private fun showLoading() {
        with(binding) {
            rvJournal.makeGone()
            loader.makeVisible()
        }
    }

    private fun populateDate(list: List<ListUiModel>) {
        with(binding) {
            rvJournal.makeVisible()
            loader.makeGone()
        }
        adapter.submitList(list)
    }
}