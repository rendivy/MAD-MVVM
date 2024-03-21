package ru.yangel.mad_mvvm.ui.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yangel.domain.usecase.ConvertPhoneNumberUseCase
import ru.yangel.domain.usecase.ValidatePhoneNumberUseCase
import ru.yangel.mad_mvvm.ui.state.UiState
import ru.yangel.domain.entity.PhoneNumberValidationError
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val convertPhoneNumberUseCase: ConvertPhoneNumberUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    private fun validatePhoneNumber() {
        val password = _uiState.value.password
        val validationResult = if (validatePhoneNumberUseCase(password)) {
            null
        } else {
            PhoneNumberValidationError.INVALID_PHONE_NUMBER
        }
        _uiState.value =
            _uiState.value.copy(passwordValidationResult = validationResult)
    }


    private fun convertPhoneNumber() {
        val password = _uiState.value.password
        val convertedPhoneNumber = convertPhoneNumberUseCase(password)
        _uiState.value = _uiState.value.copy(convertedPhoneNumber = convertedPhoneNumber)
    }


    fun onConvertPhoneNumberClicked() {
        validatePhoneNumber()
        convertPhoneNumber()
    }


}