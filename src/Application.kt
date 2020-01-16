package ru.lod_misis

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

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

fun Route.authRoutes() {
    route("auth") {
        //TODO oauth methods
        get("/") {
            call.respond("Auth routes")
        }
        get("/vk/{token}") {
            call.respond(call.parameters["token"] ?: "")
        }
        get("/facebook/{token}") {
            call.respond(call.parameters["token"] ?: "")
        }
        get("/google/{token}") {
            call.respond(call.parameters["token"] ?: "")
        }
    }
}

