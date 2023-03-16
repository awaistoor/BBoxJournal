package com.bbox.bboxjournal.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bbox.bboxjournal.databinding.LayoutJournalListDayHeaderBinding
import com.bbox.bboxjournal.databinding.LayoutJournalListItemBinding
import com.bbox.bboxjournal.databinding.LayoutJournalListMonthHeaderBinding
import com.bbox.bboxjournal.domain.usecase.ConvertDateTimeFormatUseCase
import com.bbox.bboxjournal.presentation.model.JournalUiModel

class JournalListAdapter(
    private val convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase,
    private val onItemClick: (JournalUiModel) -> Unit
) :
    ListAdapter<ListUiModel, RecyclerView.ViewHolder>(
        JournalListDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW -> {
                val binding = LayoutJournalListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JournalItemViewHolder(parent.context, binding)
            }
            MONTH_HEADER_VIEW -> {
                val binding = LayoutJournalListMonthHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JournalMonthHeaderViewHolder(parent.context, binding)
            }
            DAY_HEADER_VIEW -> {
                val binding = LayoutJournalListDayHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JournalDayHeaderViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong ViewType for this adapter!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JournalItemViewHolder -> {
                getItem(position).listItem?.let { holder.bind(it) }
            }
            is JournalDayHeaderViewHolder -> {
                getItem(position).header?.let { holder.bind(it) }
            }
            is JournalMonthHeaderViewHolder -> {
                getItem(position).header?.let { holder.bind(it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).itemType) {
            ListUiModel.ItemType.MONTH_HEADER_ITEM -> MONTH_HEADER_VIEW
            ListUiModel.ItemType.DAY_HEADER_ITEM -> DAY_HEADER_VIEW
            ListUiModel.ItemType.LIST_ITEM -> ITEM_VIEW
        }
    }

    private inner class JournalItemViewHolder(
        private val context: Context,
        private val binding: LayoutJournalListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JournalUiModel) {
            with(binding) {
                tvJournalContent.text = item.note
                cardBorder.setBackgroundColor(context.getColor(item.moodColor.colorCode))
                tvTime.text = convertDateTimeFormatUseCase(item.dateTime, "HH:mm")
                cvJournal.setOnClickListener {
                    onItemClick(item)
                }
            }
        }

    }

    private inner class JournalMonthHeaderViewHolder(
        private val context: Context,
        private val binding: LayoutJournalListMonthHeaderBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: ListUiModel.HeaderUiModel) {
            binding.tvMonth.text = header.headerTitle
            binding.tvEntries.text = "${header.entriesCount} entries"
            binding.cvMonthHeader.setBackgroundColor(context.getColor(header.headerColor.colorCode))
        }
    }

    private inner class JournalDayHeaderViewHolder(private val binding: LayoutJournalListDayHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: ListUiModel.HeaderUiModel) {
            binding.tvDay.text = header.headerTitle
            binding.tvEntries.text = "${header.entriesCount} entries"
        }
    }


    private class JournalListDiffUtil :
        DiffUtil.ItemCallback<ListUiModel>() {
        override fun areItemsTheSame(
            oldItem: ListUiModel,
            newItem: ListUiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ListUiModel,
            newItem: ListUiModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    companion object {
        const val MONTH_HEADER_VIEW = 0
        const val DAY_HEADER_VIEW = 1
        const val ITEM_VIEW = 2
    }
}