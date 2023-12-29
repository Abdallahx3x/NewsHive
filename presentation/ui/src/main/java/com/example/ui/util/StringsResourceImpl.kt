package com.example.ui.util

import android.content.Context
import com.example.ui.R
import com.example.viewmodel.base.StringsResource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringsResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringsResource {
    override val sports: String = getString(R.string.sports)
    override val science: String = getString(R.string.science)
    override val health: String = getString(R.string.health)
    override val technology: String = getString(R.string.technology)
    override val business: String = getString(R.string.business)
    private fun getString(@androidx.annotation.StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }
}