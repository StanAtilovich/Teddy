package ru.stan.teddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun HelloAbc() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val savedState = rememberSaveable { mutableStateOf("") }
    val enteredCode = savedState.value
    val correctCode = "1234" // Предположим, что правильный код - "1234"
    val isCodeEntered = enteredCode == correctCode

    if (isCodeEntered) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_screen1),
                contentDescription = "Correct Image"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Успешно зашли")

            Button(
                onClick = {
                    // Действие при нажатии на кнопку
                }
            ) {
                Text("Некоторый текст на кнопке")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = enteredCode,
                onValueChange = { savedState.value = it },
                label = { Text("Enter the code") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Please enter the correct code")
        }
    }
}

