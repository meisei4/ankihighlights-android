package com.example.anki_highlights

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.anki_highlights.network.HighlightData
import com.example.anki_highlights.network.RetrofitAnkiNetwork
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var retrofitAnkiNetwork: RetrofitAnkiNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VocabHighlighterApp(retrofitAnkiNetwork)
        }
    }
}

@Composable
fun VocabHighlighterApp(retrofitAnkiNetwork: RetrofitAnkiNetwork) {
    MaterialTheme {
        HighlightScreen(retrofitAnkiNetwork)
    }
}

@Composable
fun HighlightScreen(retrofitAnkiNetwork: RetrofitAnkiNetwork) {
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
                modifier = Modifier.fillMaxWidth().height(200.dp)
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

fun sendHighlightToApi(retrofitAnkiNetwork: RetrofitAnkiNetwork, word: String, context: String, timestamp: Long) {
    val highlightData = HighlightData(word, context, timestamp)
    retrofitAnkiNetwork.processHighlights(highlightData).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Toast.makeText(retrofitAnkiNetwork.context, "Highlight sent successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(retrofitAnkiNetwork.context, "Failed to send highlight", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Toast.makeText(retrofitAnkiNetwork.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}
