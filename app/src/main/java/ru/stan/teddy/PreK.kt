package ru.stan.teddy

import android.content.Context
import android.preference.PreferenceManager
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PreK() {
    val context = LocalContext.current
    val sharedPreferences2 = context.getSharedPreferences("my_shared_preferences2", Context.MODE_PRIVATE)
    val editor2 = sharedPreferences2.edit()
    val correctCode2 = "234" // Предположим, что правильный код - "234"

    val enteredCode2 = remember {
        mutableStateOf(sharedPreferences2.getString("enteredCode2", "") ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = enteredCode2.value,
            onValueChange = {
                enteredCode2.value = it
                editor2.putString("enteredCode2", it)
                editor2.apply()
            },
            label = { Text("Enter the code") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (enteredCode2.value == correctCode2) {
            Image(
                painter = painterResource(id = R.drawable.ic_screen1),
                contentDescription = "Correct Image"
            )
        } else {
            Text("Please enter the correct code")
        }
    }
}
