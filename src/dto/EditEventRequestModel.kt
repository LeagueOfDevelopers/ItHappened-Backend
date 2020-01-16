package ru.lod_misis.dto

import ru.lod_misis.model.Coordinates

data class EditEventRequestModel(
    val comment: String?,
    val rating: Double?,
    val scale: Double?,
    val coordinates: Coordinates?,
    val date: String?
)