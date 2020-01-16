package ru.lod_misis.routes

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import ru.lod_misis.EventNotFoundException
import ru.lod_misis.dto.EditEventRequestModel
import ru.lod_misis.dto.NewEventRequestModel
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
                call.respond(HttpStatusCode.BadRequest, Error("UUID is not valid"))
            }
        }
        post("/") {
            call.response.header("Context-Type", "application/json")
            try {
                val newEventData = call.receive<NewEventRequestModel>()
                val addedEvent = eventRepository.addEvent(newEventData)
                call.respond(HttpStatusCode.OK, addedEvent)
            } catch (exc: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest, Error("Invalid data format"))
            }
        }
        put("/{id}") {
            call.response.header("Context-Type", "application/json")
            try {
                val fieldsToEdit = call.receive<EditEventRequestModel>()
                val eventId = UUID.fromString(call.parameters["id"])
                val editedEvent = eventRepository.changeEvent(fieldsToEdit, eventId)
                call.respond(HttpStatusCode.OK, editedEvent)
            } catch (exc: EventNotFoundException) {
                call.respond(HttpStatusCode.NotFound, Error("Event not found"))
            } catch (exc: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, Error("UUID is not valid"))
            } catch (exc: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest, Error("Invalid data format"))
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
                call.respond(HttpStatusCode.BadRequest, Error("UUID is not valid"))
            }
        }
    }
}