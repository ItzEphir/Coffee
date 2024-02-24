package com.ephirium.coffee.app.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface AuthScreenState : Parcelable {
    
    val login: String
    val password: String
    
    @Parcelize
    data class SignIn(
        override val login: String = "",
        override val password: String = "",
    ) : AuthScreenState
    
    @Parcelize
    data class SignUp(
        val email: String = "",
        override val login: String = "",
        override val password: String = "",
    ) : AuthScreenState
}
