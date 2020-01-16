package ru.lod_misis.model

import java.util.*

data class User(
    val id: UUID,
    val name: String,
    val secondName: String?,
    val avatarUrl: String?
)