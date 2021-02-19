package com.example.testram.ui.presenter

import com.example.testram.data.Character
import com.example.testram.domain.repository.CharacterRepository
import com.example.testram.utils.addToBag
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class CharacterPresenter @Inject constructor(
        private val repository: CharacterRepository
        ): MvpPresenter<CharacterListView>() {

    private val characterList: MutableList<Character> = LinkedList()
    private val disposableBag = CompositeDisposable()
    private var page = 0


    fun pushCharacters(refresh: Boolean){

        viewState.loading()

        when(refresh){
            false -> page++
            true -> page = 1
        }

        repository.getCharactersPerPage(page = page)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.results.map { character ->
                        characterList.add(
                                Character(
                                        character.id,
                                        character.name,
                                        character.status,
                                        character.species,
                                        character.type,
                                        character.gender,
                                        character.image,
                                        character.episode,
                                        character.url,
                                        character.created
                                )
                        )
                        viewState.success(list = characterList)
                    }
                }, {
                    viewState.error("Internet connection error")
                    Timber.d(it)
                }).addToBag(bag = disposableBag)
    }

    override fun onDestroy() {
        disposableBag.dispose()
        super.onDestroy()
    }
}