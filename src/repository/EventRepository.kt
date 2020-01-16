package ru.lod_misis.repository

import ru.lod_misis.dto.EditEventRequestModel
import ru.lod_misis.dto.NewEventRequestModel
import ru.lod_misis.model.Event
import java.util.*

interface EventRepository {
    fun addEvent(newEvent: NewEventRequestModel): Event
    fun changeEvent(newEvent: EditEventRequestModel, eventId: UUID): Event
    fun deleteEvent(eventId: UUID): Event
    fun getAllEvents(): List<Event>
    fun getEventById(eventId: UUID): Event
    fun getEventsByGroupId(groupId: UUID): List<Event>
}