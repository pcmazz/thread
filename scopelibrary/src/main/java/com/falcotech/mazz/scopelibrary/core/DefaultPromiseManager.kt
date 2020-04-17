package com.falcotech.mazz.scopelibrary.core

import androidx.annotation.CallSuper
import com.falcotech.mazz.scopelibrary.factory.DispatcherProvider
import com.falcotech.mazz.scopelibrary.factory.dispatcherProvider
import com.falcotech.mazz.scopelibrary.factory.providerName
import com.falcotech.mazz.scopelibrary.promise.Promise
import com.falcotech.mazz.scopelibrary.promise.bgPromise
import com.falcotech.mazz.scopelibrary.promise.then
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import kotlin.coroutines.coroutineContext

open class DefaultPromiseManager : PromiseManager{

    private val coldList: ArrayList<Promise<*>> = arrayListOf()
    private var debug = { _: String-> }

    @CallSuper
    @Synchronized
    override fun <T> controlAsync(promise: Promise<T>): Promise<T> {
        coldList.add(promise)
        promise.invokeOnCompletion {
            debug.invoke("DefaultPromiseManager : controlAsync : invokeOnCompletion : promise providerName =  ${promise.providerName}, throwable = $it")
            coldList.remove(promise)
        }
        return promise
    }

    @CallSuper
    @Synchronized
    override suspend fun <T> controlAsyncAwait(promise: Promise<T>): T {
        val titty = promise.await()
        debug.invoke("DefaultPromiseManager : controlAsyncAwait : titty = $titty")
        debug.invoke("DefaultPromiseManager : controlAsyncAwait : then promise.providerName = ${promise.providerName}")
        if(promise.providerName != "DefaultDispatcherProvider"){
            controlCancelAllPromises()
        }
        return titty
        /*val prom2: Promise<T> = promise.then {titty ->
            debug.invoke("DefaultPromiseManager : controlAsyncAwait : titty = $titty")
            debug.invoke("DefaultPromiseManager : controlAsyncAwait : then promise.providerName = ${promise.providerName}")
            if(promise.providerName != "DefaultDispatcherProvider"){
                controlCancelAllPromises()
            }
            titty
        }*/

        /*return controlAsync(promise.then {titty: T ->
            debug.invoke("DefaultPromiseManager : controlAsyncAwait : titty = $titty")
            debug.invoke("DefaultPromiseManager : controlAsyncAwait : then promise.providerName = ${promise.providerName}")
            if(promise.providerName != "DefaultDispatcherProvider"){
                controlCancelAllPromises()
                titty
            }
           titty
        }).await()*/
    }

    @CallSuper
    @Synchronized
    override fun controlCancelAllPromises() {
        if(coldList.isNotEmpty()){
            (coldList.lastIndex downTo 0).forEach {
                coldList[it].cancel()
            }.let {
                coldList.clear()
            }
        }
    }

    override fun controlSetDebug(block: (String) -> Unit) {
        debug = block
    }


}