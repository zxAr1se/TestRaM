package com.example.testram.domain.repository

import com.example.testram.domain.response.CharacterResult
import io.reactivex.rxjava3.core.Flowable

interface CharacterRepository {

    fun getCharactersPerPage(page: Int): Flowable<CharacterResult>
}