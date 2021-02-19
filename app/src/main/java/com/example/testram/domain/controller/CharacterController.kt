package com.example.testram.domain.controller

import com.example.testram.domain.service.CharacterApi

class CharacterController(
    private val api: CharacterApi
) {

    fun getCharacters(page: Int) = api.getCharacters(page = page)
}