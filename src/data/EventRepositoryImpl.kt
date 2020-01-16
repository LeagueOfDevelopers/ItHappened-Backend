package ru.lod_misis.data

import ru.lod_misis.EventNotFoundException
import ru.lod_misis.dto.EditEventRequestModel
import ru.lod_misis.dto.NewEventRequestModel
import ru.lod_misis.model.Coordinates
import ru.lod_misis.model.Event
import ru.lod_misis.repository.EventRepository
import java.util.*

class EventRepositoryImpl : EventRepository {

    private val events = mutableListOf(
        Event(
            id = UUID.randomUUID(),
            groupId = UUID.randomUUID(),
            comment = "Test1",
            rating = 1.0,
            scale = 117.0,
            coordinates = Coordinates(15.6, 17.1),
            filePath = null
        ),
        Event(
            id = UUID.randomUUID(),
            groupId = UUID.randomUUID(),
            comment = "Test2",
            rating = 2.0,
            scale = 11127.0,
            coordinates = Coordinates(15.6, 17.1),
            filePath = null
        ),
        Event(
            id = UUID.randomUUID(),
            groupId = UUID.randomUUID(),
            comment = "Test3",
            rating = 5.0,
            scale = 117123.0,
            coordinates = Coordinates(15.6, 17.1),
            filePath = null
        ),
        Event(
            id = UUID.randomUUID(),
            groupId = UUID.randomUUID(),
            comment = "Test4",
            rating = 5.0,
            scale = 117.0,
            coordinates = Coordinates(15.6, 17.1),
            filePath = null
        ),
        Event(
            id = UUID.randomUUID(),
            groupId = UUID.randomUUID(),
            comment = "Test5",
            rating = 5.0,
            scale = 117.0,
            coordinates = Coordinates(15.6, 17.1),
            filePath = null
        )
    )

    override fun addEvent(newEvent: NewEventRequestModel): Event {
        val event = Event(
            id = UUID.randomUUID(),
            groupId = newEvent.groupId,
            comment = newEvent.comment,
            rating = newEvent.rating,
            scale = newEvent.scale,
            coordinates = newEvent.coordinates,
            date = newEvent.date,
            filePath = null
        )
        events.add(event)
        return event
    }

    override fun changeEvent(newEvent: EditEventRequestModel, eventId: UUID): Event {
        val event = events.find { eventId == it.id } ?: throw EventNotFoundException()
        events.remove(event)
        event.apply {
            if (newEvent.comment != null) comment = newEvent.comment
            if (newEvent.rating != null) rating = newEvent.rating
            if (newEvent.scale != null) scale = newEvent.scale
            if (newEvent.coordinates != null) coordinates = newEvent.coordinates
            if (newEvent.date != null) date = newEvent.date
        }
        events.add(event)
        return event
    }

    override fun deleteEvent(eventId: UUID): Event {
        val event = events.find { it.id == eventId } ?: throw EventNotFoundException()
        events.remove(event)
        event.isDeleted = true
        events.add(event)
        return event
    }

    override fun getAllEvents(): List<Event> {
        return events
    }

    override fun getEventById(eventId: UUID): Event {
        return events.find { it.id == eventId } ?: throw EventNotFoundException()
    }

    override fun getEventsByGroupId(groupId: UUID): List<Event> {
        return events.filter { it.groupId == groupId }
    }

}
