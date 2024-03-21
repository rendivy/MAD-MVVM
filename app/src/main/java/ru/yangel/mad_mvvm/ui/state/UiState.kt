package ru.yangel.mad_mvvm.ui.state

data class UiState(
    var password: String = "",
    var passwordValidationResult: PhoneNumberValidationError? = null,
    var convertedPhoneNumber: String = ""
)
