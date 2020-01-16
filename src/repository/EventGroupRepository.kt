package ru.lod_misis.repository

import ru.lod_misis.model.EventGroup
import java.util.*

interface EventGroupRepository {
    fun addEventGroup(eventGroup: EventGroup)
    fun deleteEventGroup(eventGroupId: UUID)
    fun changeEventGroup(eventGroup: EventGroup)
    fun getAllEventGroups()
    fun getEventGroupById(eventGroupId: UUID)
}