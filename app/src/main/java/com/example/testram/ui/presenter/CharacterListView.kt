package com.example.testram.ui.presenter

import com.example.testram.data.Character
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CharacterListView : MvpView {

    fun success(list: MutableList<Character>)
    fun loading()
    fun error(msg: String)
}