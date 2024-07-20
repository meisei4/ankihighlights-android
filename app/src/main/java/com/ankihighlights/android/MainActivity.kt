package com.ankihighlights.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ankihighlights.android.network.HighlightData
import com.ankihighlights.android.network.RetrofitHighlightController
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var retrofitHighlightController: RetrofitHighlightController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VocabHighlighterApp(retrofitHighlightController)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun VocabHighlighterApp(retrofitAnkiNetwork: RetrofitHighlightController) {
    MaterialTheme {
        HighlightScreen(retrofitAnkiNetwork)
    }
}

// TODO: Shouldnt Composable annotation overpower the function naming (k)linter issue?
@Suppress("ktlint:standard:function-naming")
@Composable
fun HighlightScreen(retrofitAnkiNetwork: RetrofitHighlightController) {
    var textState by remember { mutableStateOf(TextFieldValue("This is an example text that you can highlight to create an Anki card.")) }
    var selectedText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        SelectionContainer {
            TextField(
                value = textState,
                onValueChange = {
                    textState = it
                    selectedText = it.text.substring(it.selection.start, it.selection.end)
                },
                modifier = Modifier.fillMaxWidth().height(200.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (selectedText.isNotEmpty()) {
                val timestamp = System.currentTimeMillis()
                sendHighlightToApi(retrofitAnkiNetwork, selectedText, textState.text, timestamp)
            }
        }) {
            Text("Send Highlight")
        }
    }
}

fun sendHighlightToApi(
    retrofitAnkiNetwork: RetrofitHighlightController,
    word: String,
    context: String,
    timestamp: Long,
) {
    val highlightData = HighlightData(word, context, timestamp)
    retrofitAnkiNetwork.processHighlights(highlightData).enqueue(
        object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>,
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(retrofitAnkiNetwork.context, "Highlight sent successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(retrofitAnkiNetwork.context, "Failed to send highlight", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: Call<ResponseBody>,
                t: Throwable,
            ) {
                Toast.makeText(retrofitAnkiNetwork.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        },
    )
}
