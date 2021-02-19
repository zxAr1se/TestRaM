package com.example.testram.di.controller

import com.example.testram.di.network.NetworkModule
import com.example.testram.domain.controller.CharacterController
import com.example.testram.utils.api
import dagger.Module

@Module(
    includes = [
        NetworkModule::class
    ]
)
object CharacterActivityModule : ControllerComponent {
    override val controller: CharacterController
        get() = CharacterController(api = api.characterApi)
}