package com.example.testram.di.application



import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.testram.R
import com.example.testram.domain.repository.CharacterRepository
import com.example.testram.domain.repository.CharacterRepositoryImpl
import com.example.testram.ui.adapter.CharacterAdapter
import com.example.testram.ui.presenter.CharacterPresenter
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideCharacterRepository() : CharacterRepository = CharacterRepositoryImpl()

    @Singleton
    @Provides
    fun provideCharacterPresenter(
        characterRepository: CharacterRepository
    ): CharacterPresenter = CharacterPresenter(characterRepository)
}