package com.example.testram.domain.repository

import com.example.testram.domain.response.CharacterResult
import com.example.testram.utils.controllerComponent
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(): CharacterRepository {

    override fun getCharactersPerPage(page: Int): Flowable<CharacterResult> {
        return controllerComponent.controller.getCharacters(page = page)
    }
}