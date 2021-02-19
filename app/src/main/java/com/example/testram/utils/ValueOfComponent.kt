package com.example.testram.utils

import com.example.testram.di.controller.CharacterActivityModule
import com.example.testram.di.controller.ControllerComponent
import com.example.testram.di.network.NetworkModule
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

val controllerComponent: ControllerComponent
    get() = CharacterActivityModule

val api: NetworkModule
    get() = NetworkModule

fun Disposable.addToBag(bag: CompositeDisposable){
    bag.add(this)
}