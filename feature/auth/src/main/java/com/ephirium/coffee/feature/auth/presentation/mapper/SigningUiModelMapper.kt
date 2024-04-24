package com.ephirium.coffee.feature.auth.presentation.mapper

import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import com.ephirium.coffee.data.auth.model.entity.SigningModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up

class SigningUiModelMapper {
    companion object {
        fun SigningUiModel.toModel() = when(authorizeState){
            is In -> SignInModel(login, password)
            is Up -> SignUpModel(login, authorizeState.name, password)
        }
        
        fun SigningModel.toUi() = when(this){
            is SignInModel -> SigningUiModel(login, password, In)
            is SignUpModel -> SigningUiModel(login, password, Up(name))
        }
    }
}