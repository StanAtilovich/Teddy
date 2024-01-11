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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Kcource() {
    val context = LocalContext.current
    val sharedPreferences3 = context.getSharedPreferences("my_shared_preferences3", Context.MODE_PRIVATE)//PreferenceManager.getDefaultSharedPreferences(context)
    val editor3 = sharedPreferences3.edit()
    val correctCode3 = "34" // Предположим, что правильный код - "234"

    val enteredCode3 = remember {
        mutableStateOf(sharedPreferences3.getString("enteredCode3", "") ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = enteredCode3.value,
            onValueChange = {
                enteredCode3.value = it
                editor3.putString("enteredCode3", it)
                editor3.apply()
            },
            label = { Text("Enter the code") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (enteredCode3.value == correctCode3) {
            Image(
                painter = painterResource(id = R.drawable.ic_screen3),
                contentDescription = "Correct Image"
            )
        } else {
            Text("Please enter the correct code")
        }
    }
}