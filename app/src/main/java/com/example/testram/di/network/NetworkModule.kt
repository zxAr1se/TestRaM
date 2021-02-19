package com.example.testram.di.network


import com.example.testram.domain.service.CharacterApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule : NetworkComponent{

    override val characterApi : CharacterApi by lazy { retrofit.create(CharacterApi::class.java) }

    private val gson: Gson = GsonBuilder().serializeNulls().create()

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl("https://rickandmortyapi.com")
        .build()
}