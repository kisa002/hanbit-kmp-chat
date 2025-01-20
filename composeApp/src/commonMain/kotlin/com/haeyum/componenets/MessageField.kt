package com.haeyum.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.theme.AppColors

@Composable
fun FieldArea(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    enabled: Boolean,
    onSend: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            state = textFieldState,
            modifier = Modifier.weight(1f).fillMaxHeight()
                .background(color = AppColors.LightGray, shape = RoundedCornerShape(12.dp)),
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textFieldState.text.isEmpty())
                        Text(
                            "Type a message",
                            style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                        )

                    innerTextField()
                }
            }
        )

        Button(
            onClick = {
                onSend(textFieldState.text.toString())
            },
            modifier = Modifier.fillMaxHeight().padding(start = 12.dp),
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.Primary),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }
}