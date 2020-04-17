package com.falcotech.mazz.scope

import com.falcotech.mazz.scopelibrary.factory.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay

class SomeRepository(private val coroutineScope: IOCoroutineScope, private val unconfinedCoroutineScope: UnconfinedCoroutineScope) {



    suspend fun getSomethingExpensive() = withIO {
        delay(5000)
        "suspend function is complete!"
    }

    fun getSomethingExpensiveUnstructured(unconfined: Boolean = false): Deferred<String> {
        return if(!unconfined){
            coroutineScope.asyncIO {
                delay(1000)
                "deferred function is complete!"
            }
        }else{
            unconfinedCoroutineScope.asyncUnconfined {
                delay(1000)
                "deferred function is complete! UNCONFINED"

            }
        }
    }
    /*fun getSomethingExpensiveUnstructured(unconfined: Boolean = false) = if(unconfined){
        coroutineScope.asyncUnconfined(coroutineScope.unconfinedDispatcher){
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : asyncUnconfined = %s", this)
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : asyncUnconfined : coroutineContext = %s", this.coroutineContext)
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : asyncUnconfined : dispatcherProvider = %s", this.dispatcherProvider)
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : asyncUnconfined : dispatcherProvider.key = %s", this.dispatcherProvider.key)
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : asyncUnconfined : coroutineContext.key get = %s", this.coroutineContext[this.dispatcherProvider.key])
            delay(1000)
            "deferred function is complete! ASS UNCONFINED KEY"
        }.let {
            Timber.d("SomeRepository : getSomethingExpensiveUnstructured : promise = %s", it)
            it
        }
        //DeferredCoroutine{Active}@dcf1635
        *//*coroutineScope.asyncIO {
            this.plus(coroutineScope.dispatcherProvider.unconfined).let {
                Timber.d("SomeRepository : getSomethingExpensiveUnstructured : plus result = %s", it)
            }
            delay(1000)
            "deferred function is complete! ASS UNCONFINED KEY"
        }*//*
    }else{
        coroutineScope.asyncIO {
            delay(1000)
            "deferred function is complete!"
        }
    }*/
}