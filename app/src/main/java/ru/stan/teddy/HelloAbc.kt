package ru.stan.teddy

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


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
class MyWebChromeClient(private val activity: Activity) : WebChromeClient() {
    private var mCustomView: View? = null
    private var mCustomViewCallback: CustomViewCallback? = null

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (mCustomView != null) {
            callback?.onCustomViewHidden()
            return
        }

        mCustomView = view
        mCustomViewCallback = callback

        activity.setContentView(view)
    }

    override fun onHideCustomView() {
        mCustomView?.let {
            activity.setContentView(it)
            mCustomViewCallback?.onCustomViewHidden()
            mCustomView = null
            mCustomViewCallback = null
        }
    }
}
@Composable
fun WebViewComponent() {
    val context = LocalContext.current
    val activity = context as Activity // Явное приведение Context к Activity
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { WebView(activity) }, // Передаем activity в конструктор WebView
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webChromeClient = MyWebChromeClient(activity)
            webView.loadUrl("https://www.youtube.com/playlist?list=PL-HApJy1B-fd5kjtKfBDtQYuzMmkrTsly")
        }
    )
}












