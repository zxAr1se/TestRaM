package com.example.testram.domain.service



import com.example.testram.domain.response.CharacterResult
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    companion object{
        const val CHARACTERS = "/api/character/"
    }

    @GET(CHARACTERS)
    fun getCharacters(@Query("page")page: Int) : Flowable<CharacterResult>
}