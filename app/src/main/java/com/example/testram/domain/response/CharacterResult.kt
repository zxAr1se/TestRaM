package com.example.testram.domain.response

import com.example.testram.data.Character


data class CharacterResult(
    val info: Info,
    val results: List<Character>
)