package com.haeyum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haeyum.componenets.ChatListArea
import com.haeyum.componenets.FieldArea
import com.haeyum.componenets.Header
import com.haeyum.theme.AppColors
import com.haeyum.theme.AppTypography
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(typography = AppTypography()) {
        val lazyListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val textFieldState = rememberTextFieldState()

        val username by remember {
            mutableStateOf("팡무")
        }

        val messages = remember {
            mutableStateListOf(
                Message(
                    username = "Hanbit",
                    content = "Welcome to Hanbit KMP Chat!"
                ),
                Message(
                    username = "Hanbit",
                    content = "반가워요 여러분!"
                ),
                Message(
                    username = "팡무",
                    content = "라이브코딩에 오신 것을 환영해요!"
                ),
            )
        }

        val session = produceState<WebSocketSession?>(null) {

        }

        Column(modifier = Modifier.fillMaxSize().background(AppColors.White).safeDrawingPadding()) {
            Header(title = "Hanbit KMP Chat")

            Column(modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 16.dp)) {
                ChatListArea(
                    modifier = Modifier.weight(1f),
                    state = lazyListState,
                    ownerUsername = username,
                    messages = messages
                )

                FieldArea(
                    modifier = Modifier.padding(bottom = 24.dp),
                    textFieldState = textFieldState,
                    enabled = true,
                    onSend = { messageText ->
                        coroutineScope.launch {
                            textFieldState.clearText()
                            lazyListState.scrollToBottom()
                        }
                    }
                )
            }
        }
    }
}

private suspend fun LazyListState.scrollToBottom() = scrollToItem(Int.MAX_VALUE)