package ru.lod_misis.di

import org.koin.dsl.module
import ru.lod_misis.data.EventGroupRepositoryImpl
import ru.lod_misis.repository.EventGroupRepository

val eventGroupModule = module {
    single<EventGroupRepository> { EventGroupRepositoryImpl() }
}