package com.ksnk.android.ui.themes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentThemesBinding
import com.ksnk.android.model.Themes
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.ui.themes.adapter.ThemesAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesFragment : BaseFragment(R.layout.fragment_themes) {

    private val viewBinding by viewBinding(FragmentThemesBinding::bind)
    private val viewModel by viewModel<ThemesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        with(viewBinding) {

            materialToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            val mediatorLiveData = MediatorLiveData<List<Themes>>()

            viewModel.getAllThemes().observe(this@ThemesFragment, Observer { themesList ->
                val updatedListThemes = mutableListOf<Themes>()

                themesList.forEach { theme ->
                    val liveData = viewModel.getQuestionCountForTheme(theme.themeId)
                    mediatorLiveData.addSource(liveData) { count ->
                        updatedListThemes.add(
                            Themes(
                                theme.themeId,
                                theme.themeId,
                                theme.name,
                                allQuestions = count.total,
                                openQuestions = count.openCount
                            )
                        )

                        if (updatedListThemes.size == themesList.size) {
                            mediatorLiveData.value = updatedListThemes
                        }
                    }
                }
            })

            mediatorLiveData.observe(this@ThemesFragment, Observer { themesList ->
                val adapter = ThemesAdapter(themesList, this@ThemesFragment)
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(context)
            })
        }
    }
}