package ru.lod_misis.di

import org.koin.dsl.module
import ru.lod_misis.data.UserRepositoryImpl
import ru.lod_misis.repository.UserRepository

val userModule = module {
    //TODO https://github.com/dedalexij
    single<UserRepository> { UserRepositoryImpl() }
}