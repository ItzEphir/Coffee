package com.ephirium.coffee.feature.profile.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDataState(
    val userLogin: String,
    val currentPage: Int,
    val isEnd: Boolean,
) : Parcelable{
    companion object{
        val default = ProfileDataState(
            userLogin = "",
            currentPage = 0,
            isEnd = false,
        )
    }
}