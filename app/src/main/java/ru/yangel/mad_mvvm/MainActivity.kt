package ru.yangel.mad_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ru.yangel.mad_mvvm.ui.state.PhoneNumberValidationError
import ru.yangel.mad_mvvm.ui.theme.MADMVVMTheme
import ru.yangel.mad_mvvm.ui.viewModel.PhoneNumberViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val passwordViewModel: PhoneNumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContentScreen(viewModel = passwordViewModel)
                }
            }
        }
    }
}

@Composable
fun ContentScreen(viewModel: PhoneNumberViewModel) {
    val focusManager = LocalFocusManager.current
    val passwordUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = passwordUiState.password,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                errorBorderColor = Color.Red,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            ),
            isError = passwordUiState.passwordValidationResult == PhoneNumberValidationError.INVALID_PHONE_NUMBER,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true,
            maxLines = 1,
            onValueChange = { viewModel.onPasswordChanged(password = it) },
            label = { Text(text = "PhoneNumber") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        AnimatedVisibility(visible = showDialog.value) {
            val message = when (passwordUiState.passwordValidationResult) {
                null -> passwordUiState.convertedPhoneNumber
                PhoneNumberValidationError.INVALID_PHONE_NUMBER -> stringResource(id = R.string.invalid_phone_format)
            }
            val messageColor = when (passwordUiState.passwordValidationResult) {
                null -> Color.Black
                PhoneNumberValidationError.INVALID_PHONE_NUMBER -> Color.Red
            }
            Text(
                text = message,
                color = messageColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        Button(
            onClick = {
                viewModel.onConvertPhoneNumberClicked();
                showDialog.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            )
        ) {
            Text(text = "Convert PhoneNumber", modifier = Modifier.padding(8.dp), fontSize = 14.sp)
        }
    }
}

