package com.ksnk.android.ui.themes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.R
import com.ksnk.android.Themes
import com.ksnk.android.databinding.ThemeItemBinding

class ThemesViewHolder(
    private val binding: ThemeItemBinding,
    private val fragment: Fragment
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Themes) {
        binding.textViewNumber.text = item.number.toString() + "."
        binding.textViewName.text = item.title.toString()
        binding.progressBar.max = item.allQuestions
        binding.progressBar.progress = item.openQuestions
        binding.textViewAll.text = item.allQuestions.toString()
        binding.textViewOpen.text = item.openQuestions.toString()
        binding.root.setOnClickListener {
            val bundle = bundleOf("themeId" to item.id)
            fragment.findNavController().navigate(R.id.action_themesFragment_to_questionThemeFragment, bundle)
        }
    }

    companion object {
        fun create(parent: ViewGroup, fragment: Fragment): ThemesViewHolder {
            val binding = ThemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ThemesViewHolder(binding, fragment)
        }
    }
}