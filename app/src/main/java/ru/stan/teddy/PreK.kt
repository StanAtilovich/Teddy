package ru.stan.teddy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PreK () {
    val enteredCode = remember { mutableStateOf("") }
    val correctCode = "234" // Предположим, что правильный код - "234"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = enteredCode.value,
            onValueChange = { enteredCode.value = it },
            label = { Text("Enter the code") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (enteredCode.value == correctCode) {
            Image(
                painter = painterResource(id = R.drawable.ic_screen1),
                contentDescription = "Correct Image"
            )
        } else {
            Text("Please enter the correct code")
        }
    }
}