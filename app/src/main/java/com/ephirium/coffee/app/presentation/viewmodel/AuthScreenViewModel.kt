package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ephirium.coffee.domain.usecase.user.CreateNewUserUseCase

class AuthScreenViewModel(private val createNewUserUseCase: CreateNewUserUseCase) : ViewModel() {

}