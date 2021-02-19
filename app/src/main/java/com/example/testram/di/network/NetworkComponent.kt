package com.example.testram.di.network

import com.example.testram.domain.service.CharacterApi
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(
    modules = [
        NetworkModule::class
    ]
)
interface NetworkComponent {

    val characterApi: CharacterApi
}