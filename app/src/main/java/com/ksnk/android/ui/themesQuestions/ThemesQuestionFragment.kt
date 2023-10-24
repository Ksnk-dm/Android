package com.ksnk.android.ui.themesQuestions

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ksnk.android.ui.base.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.databinding.FragmentThemeQuestionsBinding
import com.ksnk.android.ui.themesQuestions.adapter.CustomTabAdapter
import com.ksnk.android.ui.themesQuestions.adapter.ThemesQuestionAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesQuestionFragment : BaseFragment(R.layout.fragment_theme_questions) {

    private val viewBinding by viewBinding(FragmentThemeQuestionsBinding::bind)
    private val viewModel by viewModel<ThemesQuestionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val themeId = arguments?.getLong("themeId", 1)
        Log.d("MESSAGE:::4", themeId.toString())

        val questionList = viewModel.getAllByTheme(themeId?.toInt())
        Log.d("MESSAGE::: ", questionList.toString())
        val pagerAdapter = ThemesQuestionAdapter(questionList, viewModel, viewBinding.viewPager)
        viewBinding.viewPager.adapter = pagerAdapter
        val customTabAdapter = CustomTabAdapter(viewBinding.tabLayout.context)
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { tab, position ->
            val customView = customTabAdapter.createTabView(position)
            tab.customView = customView
            if (position == viewBinding.viewPager.currentItem)
                customView.setBackgroundResource(R.drawable.circular_border)
            else customView.setBackgroundResource(R.drawable.circular_border)
        }.attach()

        viewBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val selectedTabView = viewBinding.tabLayout.getTabAt(position)?.customView
                val unselectedTabViews = viewBinding.tabLayout.getTabAt(viewBinding.viewPager.currentItem)?.customView

                selectedTabView?.let { it.setBackgroundResource(R.drawable.circular_border) }
                unselectedTabViews?.let { customTabAdapter.deselectTabView(it) }
            }
        })
    }
}