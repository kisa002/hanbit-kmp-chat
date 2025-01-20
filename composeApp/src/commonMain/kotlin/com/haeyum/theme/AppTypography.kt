package com.haeyum.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import hanbit_kmp_chat.composeapp.generated.resources.Pretendard_Bold
import hanbit_kmp_chat.composeapp.generated.resources.Pretendard_Medium
import hanbit_kmp_chat.composeapp.generated.resources.Pretendard_Regular
import hanbit_kmp_chat.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun AppFontFamily() = FontFamily(
    Font(Res.font.Pretendard_Regular, weight = FontWeight.Normal),
    Font(Res.font.Pretendard_Medium, weight = FontWeight.Medium),
    Font(Res.font.Pretendard_Bold, weight = FontWeight.Bold),
)

@Composable
fun AppTypography() = Typography(defaultFontFamily = AppFontFamily())