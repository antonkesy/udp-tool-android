package com.antonkesy.udptool.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.antonkesy.udptool.R

sealed class NavCategories(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableId: Int
) {
    object Configure :
        NavCategories("Configure", R.string.configure, R.drawable.ic_baseline_settings_24)

    object Log :
        NavCategories("Log", R.string.log, R.drawable.ic_baseline_message_24)
}
