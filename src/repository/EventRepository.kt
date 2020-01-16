package ru.lod_misis.repository

import ru.lod_misis.model.Event
import java.util.*

interface EventRepository {
    fun addEvent(event: Event): Event
    fun changeEvent(newEvent: Event): Event
    fun deleteEvent(eventId: UUID): Event
    fun getAllEvents(): List<Event>
    fun getEventById(eventId: UUID): Event
    fun getEventsByGroupId(groupId: UUID): List<Event>
}