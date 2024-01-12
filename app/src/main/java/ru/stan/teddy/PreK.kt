package ru.stan.teddy

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun PreK() {
    val context = LocalContext.current
    val sharedPreferences2 = context.getSharedPreferences("my_shared_preferences2", Context.MODE_PRIVATE)
    val editor2 = sharedPreferences2.edit()
    val correctCode2 = "234" // Предположим, что правильный код - "234"

    val enteredCode2 = remember {
        mutableStateOf(sharedPreferences2.getString("enteredCode2", "") ?: "")
    }

    val isCodeEnteredCorrectly2 = remember {
        mutableStateOf(enteredCode2.value == correctCode2)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (!isCodeEnteredCorrectly2.value) {
            OutlinedTextField(
                value = enteredCode2.value,
                onValueChange = {
                    enteredCode2.value = it
                    editor2.putString("enteredCode1", it)
                    editor2.apply()
                },
                label = { Text("Enter the code") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (enteredCode2.value == correctCode2) {
                WebViewComponent2()

            } else {
                Text("Please enter the correct code")
            }
        } else {
            WebViewComponent2()
        }
    }
}


@Composable
fun WebViewComponent2() {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { WebView(context) },
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("https://www.youtube.com/playlist?list=PL-HApJy1B-ffwJHiV4wbE4f3zXtPgtt92")
        }
    )
}