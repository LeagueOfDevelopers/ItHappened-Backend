package ru.lod_misis.dto

import ru.lod_misis.model.Coordinates
import java.util.*

data class NewEventRequestModel(
    val groupId: UUID,
    val comment: String?,
    val rating: Double?,
    val scale: Double?,
    val coordinates: Coordinates?,
    val date: String
)