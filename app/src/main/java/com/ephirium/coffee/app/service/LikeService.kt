package com.ephirium.coffee.app.service

import com.google.firebase.messaging.FirebaseMessagingService

class LikeService : FirebaseMessagingService() {
    
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        
        
    }

}