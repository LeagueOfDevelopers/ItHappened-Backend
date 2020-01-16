package ru.lod_misis.model

import java.util.*

data class Event(
    val id: UUID,
    val groupId: UUID,
    var comment: String?,
    var rating: Double?,
    var scale: Double?,
    var coordinates: Coordinates?,
    var filePath: String?,
    var date: String = Date().toString(),
    var isDeleted: Boolean = false
) {

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Event) {
            other.id == id
        } else {
            false
        }
    }
}