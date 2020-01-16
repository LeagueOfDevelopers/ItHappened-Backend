package ru.lod_misis.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

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