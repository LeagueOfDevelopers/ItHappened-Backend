package ru.lod_misis.di

import org.koin.dsl.module
import ru.lod_misis.data.EventRepositoryImpl
import ru.lod_misis.repository.EventRepository

val eventModule = module {
    single<EventRepository> { EventRepositoryImpl() }
}