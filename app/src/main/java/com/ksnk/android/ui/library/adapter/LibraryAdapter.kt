package com.ksnk.android.ui.library.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.data.entity.LibraryEntity

class LibraryAdapter(
    val items: List<LibraryEntity>
) : RecyclerView.Adapter<LibraryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder =
        LibraryViewHolder.create(parent)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) =
        holder.bind(items[position])
}