package ru.yangel.domain.usecase

class ValidatePhoneNumberUseCase {

    operator fun invoke(phoneNumber: String): Boolean {
        val regex = Regex("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
        return phoneNumber.matches(regex)
    }

}