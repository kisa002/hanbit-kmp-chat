package com.haeyum.componenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.theme.AppColors
import com.haeyum.Message

@Composable
fun ChatListArea(
    state: LazyListState,
    modifier: Modifier = Modifier,
    ownerUsername: String,
    messages: List<Message>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Box(modifier = Modifier.height(12.dp))
        }

        itemsIndexed(messages) { index, message ->
            val isMe = ownerUsername == message.username

            Row(modifier = Modifier.fillMaxWidth()) {
                if (isMe)
                    Box(modifier = Modifier.weight(1f))

                Column {
                    if (!isMe && message.username != messages.getOrNull(index - 1)?.username) {
                        Text(
                            text = message.username,
                            modifier = Modifier.padding(bottom = 4.dp),
                            color = AppColors.Black,
                            fontSize = 14.sp
                        )
                    }

                    ChatBubble(message = message, isMe = isMe)
                }
            }
        }

        item {
            Box(modifier = Modifier.height(12.dp))
        }
    }
}