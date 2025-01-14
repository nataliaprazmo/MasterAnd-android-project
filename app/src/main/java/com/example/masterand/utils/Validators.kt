package com.example.masterand.utils

import android.util.Patterns

object Validators {
    fun validateName(name: String): Boolean = name.isNotBlank()

    fun validateEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validateColorCount(count: Int): Boolean = count in 5..10
}