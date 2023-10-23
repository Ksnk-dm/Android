package com.ksnk.android.ui.themes.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.BaseFragment
import com.ksnk.android.Themes

class ThemesAdapter(var items: List<Themes>, private val fragment: BaseFragment) : RecyclerView.Adapter<ThemesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesViewHolder =
        ThemesViewHolder.create(parent, fragment)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ThemesViewHolder, position: Int) =
        holder.bind(items[position])
}