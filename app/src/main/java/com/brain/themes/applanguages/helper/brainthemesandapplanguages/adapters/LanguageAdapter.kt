package com.brain.themes.applanguages.helper.brainthemesandapplanguages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.ItemLanguageBinding
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.models.Languages

class LanguageAdapter(private val list: List<Languages>, private val onItemClicked: OnItemClicked) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    interface OnItemClicked {
        fun onClicked(item: Languages, position: Int)
    }

    class ViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Languages,
            position: Int,
            onItemClicked: OnItemClicked
        ) {
            with(binding) {
                tvLanguage.text = item.languageName
                checkbox.isChecked = item.isSelected
                itemView.setOnClickListener {
                    onItemClicked.onClicked(item, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position, onItemClicked)
    }


}