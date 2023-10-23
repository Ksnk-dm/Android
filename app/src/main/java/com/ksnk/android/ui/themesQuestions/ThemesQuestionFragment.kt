package com.ksnk.android.ui.themesQuestions

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ksnk.android.BaseFragment
import com.ksnk.android.R
import com.ksnk.android.databinding.CustomTabItemBinding
import com.ksnk.android.databinding.FragmentThemeQuestionsBinding
import com.ksnk.android.ui.themesQuestions.adapter.CustomTabAdapter
import com.ksnk.android.ui.themesQuestions.adapter.ThemesQuestionAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ThemesQuestionFragment : BaseFragment(R.layout.fragment_theme_questions) {

    private val viewBinding by viewBinding(FragmentThemeQuestionsBinding::bind)
    private val viewModel by viewModel<ThemesQuestionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        viewBinding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val themeId = arguments?.getInt("themeId", 1)

        viewModel.getAllByTheme(themeId).observe(requireActivity(), Observer { questionList ->

            val pagerAdapter = ThemesQuestionAdapter(questionList, viewModel)
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
        })




            val fixedWidthInPixels = 150
            val fixedHeightInPixels = 100

            for (number in numbers) {
                val textView = TextView(requireContext())

                val layoutParams = ViewGroup.MarginLayoutParams(
                    fixedWidthInPixels,
                    fixedHeightInPixels
                )

                val leftMarginInPixels = 20
                val rightMarginInPixels = 20

                layoutParams.leftMargin = leftMarginInPixels
                layoutParams.rightMargin = rightMarginInPixels

                textView.layoutParams = layoutParams
                textView.setBackgroundResource(R.drawable.circular_border)
                textView.text = number.toString()
                textView.textSize = 24f
                textView.setTextColor(Color.WHITE)
                textView.setPadding(16, 0, 16, 0)

                // Устанавливаем текст в центре
                textView.gravity = Gravity.CENTER
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER


            }
    }
}