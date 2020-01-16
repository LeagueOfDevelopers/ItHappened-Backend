package ru.lod_misis.routes

import io.ktor.routing.*
import org.koin.ktor.ext.inject
import ru.lod_misis.repository.EventGroupRepository

fun Route.eventGroupRoutes() {

    val eventGroupRepository: EventGroupRepository by inject()

    route("/event_group") {
        get("/{id}") {

        }
        post("/") {

        }
        patch("/{id}") {

        }
        delete("/{id}") {

        }
    }
}