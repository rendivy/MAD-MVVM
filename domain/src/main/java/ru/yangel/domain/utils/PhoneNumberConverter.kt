package ru.yangel.domain.utils


object PhoneNumberConverter {

    fun convertPhoneNumber(phoneNumber: String): String {
        val cleanNumber = phoneNumber.replace(Regex("[^\\d]"), "")

        return when {
            cleanNumber.length == 10 -> formatPhoneNumber(cleanNumber)
            cleanNumber.length == 11 && (cleanNumber.startsWith("7") || cleanNumber.startsWith("8")) -> formatPhoneNumber(cleanNumber.substring(1))
            else -> phoneNumber
        }
    }

    private fun formatPhoneNumber(number: String): String {
        return "+7 (${number.substring(0, 3)}) ${number.substring(3, 6)} ${number.substring(6, 8)} ${number.substring(8, 10)}"
    }
}