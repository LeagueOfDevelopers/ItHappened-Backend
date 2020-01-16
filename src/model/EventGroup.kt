package ru.lod_misis.model

import java.util.*

data class EventGroup(
    val id: UUID,
    var name: String,
    var description: String?,
    var color: String,
    var isCommentEnabled: Boolean,
    var isRatingEnabled: Boolean,
    var isScaleEnabled: Boolean,
    var isFileEnabled: Boolean,
    var isLocationEnabled: Boolean,
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