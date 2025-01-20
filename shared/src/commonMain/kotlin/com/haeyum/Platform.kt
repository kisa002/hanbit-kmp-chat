package com.haeyum

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform