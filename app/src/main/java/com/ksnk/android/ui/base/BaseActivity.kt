package com.ksnk.android.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes)