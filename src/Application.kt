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
import org.koin.core.context.startKoin
import ru.lod_misis.di.eventGroupModule
import ru.lod_misis.di.eventModule
import ru.lod_misis.di.userModule
import ru.lod_misis.routes.authRoutes
import ru.lod_misis.routes.eventGroupRoutes
import ru.lod_misis.routes.eventRoutes

data class ItHappened(val name: String, val version: String)

fun main() {
    startKoin {
        modules(listOf(userModule, eventModule, eventGroupModule))
    }
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
            eventRoutes()
            eventGroupRoutes()
        }
    }
    server.start(wait = true)
}
