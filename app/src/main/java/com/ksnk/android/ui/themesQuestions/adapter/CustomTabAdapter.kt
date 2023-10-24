package com.ksnk.android.ui.themesQuestions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ksnk.android.R
import com.ksnk.android.databinding.CustomTabItemBinding

class CustomTabAdapter (private val context: Context) {

    fun createTabView(position: Int): View {
        val binding = CustomTabItemBinding.inflate(LayoutInflater.from(context))
        val customView = binding.root
        binding.tabNumber.text = (position.plus(1)).toString()

        return customView
    }

    fun deselectTabView(tabView: View) {
        val tabNumberTextView = tabView.findViewById<TextView>(R.id.tab_number)
        tabNumberTextView.setTextColor(ContextCompat.getColor(context, R.color.selected_color))
    }
}