package ru.yangel.mad_mvvm.ui.state

import ru.yangel.domain.entity.PhoneNumberValidationError

data class UiState(
    var password: String = "",
    var passwordValidationResult: PhoneNumberValidationError? = null,
    var convertedPhoneNumber: String = ""
)
