package com.example.testram.di.controller

import com.example.testram.domain.controller.CharacterController
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CharacterActivityModule::class
    ]
)
interface ControllerComponent {
    val controller: CharacterController
}