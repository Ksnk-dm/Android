package com.ksnk.android.ui.themes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.model.Themes
import com.ksnk.android.databinding.FragmentThemesBinding
import com.ksnk.android.ui.themes.adapter.ThemesAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesFragment : BaseFragment(R.layout.fragment_themes) {

    private val viewBinding by viewBinding(FragmentThemesBinding::bind)
    private val viewModel by viewModel<ThemesViewModel>()
    private val listThemes = arrayListOf<Themes>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {

            materialToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            listThemes.clear()
            viewModel.getAllThemes().observe(requireActivity(), Observer { themesList ->
                themesList.forEach {
                    viewModel.getQuestionCountForTheme(it.themeId).observe(requireActivity(), Observer { count ->
                        listThemes.add(Themes(it.themeId, it.themeId, it.name, allQuestions = count.total, openQuestions = count.openCount))
                        val adapter = ThemesAdapter(listThemes, this@ThemesFragment)
                        rv.adapter = adapter
                        rv.layoutManager = LinearLayoutManager(context)
                        adapter.notifyDataSetChanged()
                    })
                }
            })
        }
    }
}