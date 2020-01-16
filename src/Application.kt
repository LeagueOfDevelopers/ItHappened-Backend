package ru.lod_misis

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import ru.lod_misis.routes.authRoutes

data class ItHappened(val name: String, val version: String)

fun main() {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                serializeNulls()
            }
        }
        routing {
            get("/") {
                call.response.header("Context-Type", "application/json")
                call.respond(ItHappened("ItHappened", "v2.0"))
            }
            authRoutes()
        }
    }
    server.start(wait = true)
}
