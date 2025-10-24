package com.snacks.nuvo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveCredentials(nickname: String, password: String) {
        prefs.edit()
            .putString(KEY_NICKNAME, nickname)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun getNickname(): String? = prefs.getString(KEY_NICKNAME, null)

    fun getPassword(): String? = prefs.getString(KEY_PASSWORD, null)

    fun clearCredentials() {
        prefs.edit()
            .remove(KEY_NICKNAME)
            .remove(KEY_PASSWORD)
            .apply()
    }

    companion object {
        private const val KEY_NICKNAME = "key_nickname"
        private const val KEY_PASSWORD = "key_password"
    }
}