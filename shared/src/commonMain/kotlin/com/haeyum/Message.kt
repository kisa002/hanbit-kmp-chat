package com.haeyum

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val username: String,
    val content: String
)