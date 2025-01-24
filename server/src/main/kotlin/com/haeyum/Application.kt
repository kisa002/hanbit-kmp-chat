package com.haeyum

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.receiveAsFlow

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(WebSockets)

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        get("/hanbit") {
            call.respondText("[GET] HANBIT!")
        }

        post("/hanbit") {
            val receivedText = call.receiveText()
            call.respondText("[POST]\n$receivedText")
        }

        val clients = mutableSetOf<DefaultWebSocketSession>()

        webSocket("/chat") {
            clients.add(this)

            incoming
                .receiveAsFlow()
                .onCompletion {
                    clients.remove(this@webSocket)
                }
                .collect { frame ->
                    when (frame) {
                        is Frame.Text -> {
                            val receivedText = frame.readText()

                            clients.forEach { session ->
                                session.send(Frame.Text(receivedText))
                            }
                        }
                        else -> {
                            println("Something Wrong")
                        }
                    }
                }

        }
    }
}