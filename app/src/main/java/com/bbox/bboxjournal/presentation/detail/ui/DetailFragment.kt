package com.bbox.bboxjournal.presentation.detail.ui

import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bbox.bboxjournal.R
import com.bbox.bboxjournal.databinding.FragmentDetailBinding
import com.bbox.bboxjournal.domain.usecase.ConvertDateTimeFormatUseCase
import com.bbox.bboxjournal.presentation.detail.viewmodel.DetailViewModel
import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.utils.makeGone
import com.bbox.bboxjournal.utils.makeVisible
import com.bbox.bboxjournal.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        observeUiState()
    }

    private fun observeUiState() {
        viewModel.loadDetailUiState.observe(viewLifecycleOwner) {
            when (it) {
                DetailViewState.LoadDetailViewState.Error -> showError()
                DetailViewState.LoadDetailViewState.Loading -> showLoading()
                is DetailViewState.LoadDetailViewState.Success -> populateData(it.journalUiModel)
            }
        }
        viewModel.deleteJournalUiState.observe(viewLifecycleOwner) {
            when (it) {
                DetailViewState.DeleteJournalViewState.Error -> showError()
                DetailViewState.DeleteJournalViewState.Loading -> showLoading()
                DetailViewState.DeleteJournalViewState.Success -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun populateData(journalUiModel: JournalUiModel) {
        with(binding) {
            loader.makeGone()
            clContainer.makeVisible()
            cardBorder.setBackgroundColor(
                ResourcesCompat.getColor(
                    resources,
                    journalUiModel.moodColor.colorCode,
                    null
                )
            )
            tvJournal.text = journalUiModel.note
            tvJournalDate.text =
                convertDateTimeFormatUseCase.invoke(journalUiModel.dateTime, "MMM dd, yyyy | HH:mm")
        }
    }

    private fun showLoading() {
        with(binding) {
            clContainer.makeGone()
            loader.makeVisible()
        }
    }

    private fun showError() {
        with(binding) {
            clContainer.makeGone()
            loader.makeGone()
            showSnackBar(root, getString(R.string.something_went_wrong))
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.detail_fragment_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.delete) {
            viewModel.deleteJournal()
            return true
        }
        return false
    }
}