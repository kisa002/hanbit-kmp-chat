package com.haeyum.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.theme.AppColors
import com.haeyum.Message

@Composable
fun ChatBubble(
    message: Message,
    isMe: Boolean
) {
    Column(
        modifier = Modifier
            .background(
                color = if (isMe) AppColors.Primary else AppColors.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(
            message.content,
            color = if (isMe) AppColors.White else AppColors.Black,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
    }
}