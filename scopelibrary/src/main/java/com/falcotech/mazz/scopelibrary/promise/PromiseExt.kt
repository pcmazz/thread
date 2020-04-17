package com.falcotech.mazz.scopelibrary.promise

import android.util.TypedValue
import com.falcotech.mazz.scopelibrary.factory.asyncDefault
import com.falcotech.mazz.scopelibrary.factory.defaultCoroutineScope
import com.falcotech.mazz.scopelibrary.factory.dispatcherProvider
import kotlinx.coroutines.*
import timber.log.Timber

typealias Promise<T> = Deferred<T>


fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}

fun <T> bgPromise(block: suspend CoroutineScope.() -> T): Promise<T> = defaultCoroutineScope()
    .asyncDefault(dispatcherProvider().default, CoroutineStart.LAZY){
        block.invoke(this)
    }

fun <T, Y> Promise<T>.then(handler: (T) -> Y): Promise<Y> = CoroutineScope(Dispatchers.Default)
    .async(Dispatchers.Default, CoroutineStart.LAZY) {
        val res = this@then.await()
        handler.invoke(res)
    }

fun <T, Y> Promise<T>.thenAsync(handler: (T) -> Promise<Y>): Promise<Y> = CoroutineScope(Dispatchers.Default)
    .async(Dispatchers.Default, CoroutineStart.LAZY) {
        val res = this@thenAsync.await()
        handler.invoke(res).await()
    }
