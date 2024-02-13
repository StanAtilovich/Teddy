package ru.stan.teddy

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.webkit.WebChromeClient
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Kcource() {
    val context = LocalContext.current
    val sharedPreferences3 = context.getSharedPreferences("my_shared_preferences3", Context.MODE_PRIVATE)
    val editor3 = sharedPreferences3.edit()
    val correctCode3 = "34" // Предположим, что правильный код - "234"

    val enteredCode3 = remember {
        mutableStateOf(sharedPreferences3.getString("enteredCode3", "") ?: "")
    }

    val isCodeEnteredCorrectly3 = remember {
        mutableStateOf(enteredCode3.value == correctCode3)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (!isCodeEnteredCorrectly3.value) {
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
            WebViewComponent3()
        } else {
            Text("Please enter the correct code")
        }
    }
        else {
            WebViewComponent3()
        }
    }
    if (isCodeEnteredCorrectly3.value) {
        val activity = (LocalContext.current as Activity)
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}

class MyWebChromeClient3(private val activity: Activity) : WebChromeClient() {
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
fun WebViewComponent3() {
    val context = LocalContext.current
    val activity = context as Activity // Явное приведение Context к Activity
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { WebView(activity) }, // Передаем activity в конструктор WebView
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webChromeClient = MyWebChromeClient3(activity)
            webView.loadUrl("https://www.youtube.com/playlist?list=PL-HApJy1B-fdf_NXyIy30mWtDYk1S2nas")
        }
    )
}