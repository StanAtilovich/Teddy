package ru.stan.teddy

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.LifecycleOwner


@Composable
fun HelloAbc() {
    val context = LocalContext.current
    val sharedPreferences1 =
        context.getSharedPreferences("my_shared_preferences1", Context.MODE_PRIVATE)
    val editor1 = sharedPreferences1.edit()
    val correctCode1 = "1234" // Предположим, что правильный код - "1234"

    val enteredCode1 = remember {
        mutableStateOf(sharedPreferences1.getString("enteredCode1", "") ?: "")
    }

    val isCodeEnteredCorrectly = remember {
        mutableStateOf(enteredCode1.value == correctCode1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (!isCodeEnteredCorrectly.value) {
            OutlinedTextField(
                value = enteredCode1.value,
                onValueChange = {
                    enteredCode1.value = it
                    editor1.putString("enteredCode1", it)
                    editor1.apply()
                },
                label = { Text("Enter the code") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (enteredCode1.value == correctCode1) {

                WebViewComponent()

            } else {
                Text("Please enter the correct code")
            }
        } else {
            WebViewComponent()
        }
    }
}

@Composable
fun WebViewComponent() {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { WebView(context) },
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("https://www.youtube.com/playlist?list=PL-HApJy1B-fd5kjtKfBDtQYuzMmkrTsly")
        }
    )
}








