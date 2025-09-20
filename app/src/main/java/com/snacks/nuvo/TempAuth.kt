package com.snacks.nuvo

object TempAuthManager {

    private var accessToken: String? = null

    fun issueAndSaveToken(token: String) {
        this.accessToken = token
    }

    fun getAccessToken(): String? {
        return accessToken
    }
}
