package com.ksnk.android.ui.library

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentLibraryBinding
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.ui.library.adapter.LibraryAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class LibraryFragment : BaseFragment(R.layout.fragment_library) {

    private val viewBinding by viewBinding(FragmentLibraryBinding::bind)
    private val viewModel by viewModel<LibraryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            viewModel.getAll().observe(this@LibraryFragment, Observer { libraryList ->
                val adapter = LibraryAdapter(libraryList)
                rvLibrary.adapter = adapter
                rvLibrary.layoutManager = LinearLayoutManager(context)
            })

        }
    }
}