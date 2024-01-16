package com.ephirium.coffee.app.presentation.state

import com.ephirium.coffee.domain.model.present.Compliment

data class MainScreenState(val isVisible: Boolean, val compliment: Compliment?)