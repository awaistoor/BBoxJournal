package com.bbox.bboxjournal.presentation.addnote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bbox.bboxjournal.R
import com.bbox.bboxjournal.databinding.FragmentAddNoteBinding
import com.bbox.bboxjournal.presentation.addnote.viewmodel.AddNoteViewModel
import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.presentation.model.MoodColorUiModel
import com.bbox.bboxjournal.utils.makeGone
import com.bbox.bboxjournal.utils.makeVisible
import com.bbox.bboxjournal.utils.showSnackBar
import com.bbox.bboxjournal.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
        setupCardViews()
        binding.btnSave.setOnClickListener {
            if (performValidation()) {
                val moodColor = getSelectedModeColor()
                viewModel.saveJournal(
                    JournalUiModel(
                        moodColor = moodColor,
                        note = binding.etNote.text.toString()
                    )
                )
            } else {
                showSnackBar(binding.root, getString(R.string.please_enter_a_note))
            }
        }
    }

    private fun getSelectedModeColor(): MoodColorUiModel {
        return when {
            binding.cvMoodGreen.isChecked -> MoodColorUiModel.GREEN
            binding.cvMoodRed.isChecked -> MoodColorUiModel.RED
            binding.cvMoodYellow.isChecked -> MoodColorUiModel.YELLOW
            else -> MoodColorUiModel.GREEN
        }
    }

    private fun observeUiState() {
        viewModel.addNoteUiState.observe(viewLifecycleOwner) {
            when (it) {
                AddNoteViewState.Error -> {
                    binding.loader.makeGone()
                    showSnackBar(binding.root, getString(R.string.something_went_wrong))
                }
                AddNoteViewState.Loading -> {
                    binding.loader.makeVisible()
                }
                AddNoteViewState.Success -> {
                    binding.loader.makeGone()
                    showToast(context, getString(R.string.journal_added))
                    findNavController().popBackStack()
                }
            }
        }
    }


    private fun setupCardViews() {
        // setting up default checked card to green
        binding.cvMoodGreen.isChecked = true
        binding.cvMoodGreen.setOnClickListener {
            with(binding) {
                cvMoodGreen.isChecked = true
                cvMoodRed.isChecked = false
                cvMoodYellow.isChecked = false
            }
        }
        binding.cvMoodYellow.setOnClickListener {
            with(binding) {
                cvMoodYellow.isChecked = true
                cvMoodRed.isChecked = false
                cvMoodGreen.isChecked = false
            }
        }

        binding.cvMoodRed.setOnClickListener {
            with(binding) {
                cvMoodRed.isChecked = true
                cvMoodYellow.isChecked = false
                cvMoodGreen.isChecked = false
            }
        }
    }

    private fun performValidation(): Boolean = !binding.etNote.text.isNullOrEmpty()

}