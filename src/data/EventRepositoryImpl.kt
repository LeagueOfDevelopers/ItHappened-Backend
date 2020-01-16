package ru.lod_misis.data

import ru.lod_misis.model.Event
import ru.lod_misis.repository.EventRepository
import java.util.*

class EventRepositoryImpl : EventRepository {

    private val events = mutableListOf<Event>()

    override fun addEvent(event: Event): Event {
        events.add(event)
        return event
    }

    override fun changeEvent(newEvent: Event): Event {
        TODO()
    }

    override fun deleteEvent(eventId: UUID): Event {
        val event = events.find { it.id == eventId } ?: throw IllegalStateException("Event not found")
        events.remove(event)
        event.isDeleted = true
        events.add(event)
        return event
    }

    override fun getAllEvents(): List<Event> {
        return events
    }

    override fun getEventById(eventId: UUID): Event {
        return events.find { it.id == eventId } ?: throw IllegalStateException("Event not found")
    }

    override fun getEventsByGroupId(groupId: UUID): List<Event> {
        return events.filter { it.groupId == groupId }
    }

}
