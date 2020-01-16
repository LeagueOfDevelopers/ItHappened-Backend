package ru.lod_misis.routes

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import ru.lod_misis.EventNotFoundException
import ru.lod_misis.model.Error
import ru.lod_misis.repository.EventRepository
import java.util.*

fun Route.eventRoutes() {

    val eventRepository: EventRepository by inject()

    route("/event") {
        get("/") {
            val events = eventRepository.getAllEvents()
            call.response.header("Context-Type", "application/json")
            call.respond(HttpStatusCode.OK, events)
        }
        get("/{id}") {
            call.response.header("Context-Type", "application/json")
            try {
                val event = eventRepository.getEventById(UUID.fromString(call.parameters["id"]))
                call.respond(HttpStatusCode.OK, event)
            } catch (exc: EventNotFoundException) {
                call.respond(HttpStatusCode.NotFound, Error("Event not found"))
            } catch (exc: IllegalArgumentException) {
                call.respond(HttpStatusCode.NotFound, Error("UUID is not valid"))
            }
        }
        post("/") {
            //TODO
        }
        patch("/{id}") {
            //TODO
            try {

            } catch (exc: EventNotFoundException) {

            }
        }
        delete("/{id}") {
            call.response.header("Context-Type", "application/json")
            try {
                val deletedEvent = eventRepository.deleteEvent(UUID.fromString(call.parameters["id"]))
                call.respond(HttpStatusCode.OK, deletedEvent)
            } catch (exc: EventNotFoundException) {
                call.respond(HttpStatusCode.NotFound, Error("Event not found"))
            } catch (exc: IllegalArgumentException) {
                call.respond(HttpStatusCode.NotFound, Error("UUID is not valid"))
            }
        }
    }
}