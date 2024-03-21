package ru.yangel.domain.usecase

import ru.yangel.domain.utils.PhoneNumberConverter

class ConvertPhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): String {
        return PhoneNumberConverter.convertPhoneNumber(phoneNumber)
    }
}