package com.example.homework_2.presentation.base

import androidx.appcompat.app.AppCompatActivity
import vivid.money.elmslie.android.renderer.ElmRendererDelegate
import vivid.money.elmslie.core.store.Store

abstract class ElmBaseActivity<Event : Any, Effect : Any, ViewState : Any> : AppCompatActivity(),
    ElmRendererDelegate<Effect, ViewState> {

    abstract val store: Store<Event, Effect, ViewState>
}