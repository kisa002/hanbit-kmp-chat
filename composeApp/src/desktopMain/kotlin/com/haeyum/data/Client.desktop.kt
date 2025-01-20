package com.haeyum.data

import com.haeyum.SERVER_PORT
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets

actual val client: HttpClient = HttpClient(CIO) {
    install(WebSockets)

    defaultRequest {
        url(API_BASE_URL)
        port = SERVER_PORT
    }
}