package com.ksnk.android.ui.themes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.Themes
import com.ksnk.android.databinding.FragmentThemesBinding
import com.ksnk.android.ui.themes.adapter.ThemesAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesFragment : BaseFragment(R.layout.fragment_themes) {

    private val viewBinding by viewBinding(FragmentThemesBinding::bind)
    private val viewModel by viewModel<ThemesViewModel>()
    val listThemes = arrayListOf<Themes>(
//        Themes(1, 1, "тема 1", 12, 2, 3),
//        Themes(2, 2, "тема 2", 3, 0, 11),
//        Themes(3, 3, "тема 3", 22, 8, 22),
//        Themes(4, 4, "тема 4", 11, 3, 1),
//        Themes(5, 5, "тема 5", 4, 8, 4),
//        Themes(6, 6, "тема 6", 8, 5, 8),
//        Themes(7, 7, "тема 7", 6, 0, 6),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            materialToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
               // hideBottomNavigation()
            }
            viewModel.getAllThemes().observe(requireActivity(), Observer { themesList ->
                themesList.forEach {
                    viewModel.getQuestionCountForTheme(it.themeId).observe(requireActivity(), Observer { count ->
                        listThemes.add(Themes(it.themeId, it.themeId, it.name, allQuestions = count.total, openQuestions = count.openCount))
                        val adapter = ThemesAdapter(listThemes, this@ThemesFragment)
                        rv.adapter = adapter
                        rv.layoutManager = LinearLayoutManager(context)
                    })
                }

            })

        }
    }
}