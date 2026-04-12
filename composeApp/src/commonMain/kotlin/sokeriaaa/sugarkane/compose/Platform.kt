package sokeriaaa.sugarkane.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform