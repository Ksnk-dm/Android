package com.ksnk.android.ui.library.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.android.data.entity.LibraryEntity
import com.ksnk.android.databinding.LibraryItemBinding
import com.squareup.picasso.Picasso

class LibraryViewHolder(
    private val binding: LibraryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: LibraryEntity) {
        Picasso.with(binding.root.context)
            .load(item.image)
            .into(binding.imageViewLink)

        binding.textViewLibraryDescription.text = item.description
        binding.textViewLibraryTitle.text = item.title

        binding.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            binding.root.context.startActivity(intent)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): LibraryViewHolder {
            val binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LibraryViewHolder(binding)
        }
    }
}