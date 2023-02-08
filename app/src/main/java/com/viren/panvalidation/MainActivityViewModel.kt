package com.viren.panvalidation

import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivityViewModel : ViewModel() {

    fun validatePanNumber(panNumber: String): Boolean {
        val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        val matcher: Matcher = pattern.matcher(panNumber)
        return matcher.matches()
    }

}