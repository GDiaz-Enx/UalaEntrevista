package ar.com.ualaentrevista.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class CustomBasePresenter<V : CustomBaseView> : CoroutineScope {

    protected var view: V? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val ioScope = CoroutineScope (Dispatchers.IO)
    val uiScope = CoroutineScope (Dispatchers.Main)

    fun attachView(view: V) {
        this.view = view
    }

    fun destroyView() {
        view = null
    }
}