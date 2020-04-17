package com.falcotech.mazz.scope

import androidx.lifecycle.viewModelScope
import com.falcotech.mazz.scopelibrary.core.BaseViewModel
import com.falcotech.mazz.scopelibrary.core.PromiseManager
import com.falcotech.mazz.scopelibrary.factory.asyncDefault
import com.falcotech.mazz.scopelibrary.factory.defaultDispatcher
import com.falcotech.mazz.scopelibrary.promise.Promise
import com.falcotech.mazz.scopelibrary.promise.bgPromise
import com.falcotech.mazz.scopelibrary.promise.then
import com.falcotech.mazz.scopelibrary.promise.thenAsync
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(promiseManager: PromiseManager, val repository: SomeRepository): BaseViewModel(promiseManager) {
    private var cockCounter = 0
    init {
        controlSetDebug {
            Timber.d("MainViewModel : init : controlSetDebug = %s", it)
        }
    }

    val repoProm = controlAsync(repository.getSomethingExpensiveUnstructured())
        .thenAsync {
            bgPromise {
                it + "COCKs"
            }
        }
    /*val repoProm = controlAsync(repository.getSomethingExpensiveUnstructured())
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            repository.getSomethingExpensiveUnstructured()
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            repository.getSomethingExpensiveUnstructured()
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            repository.getSomethingExpensiveUnstructured(true)
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            return@then it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }
        .then{
            cockCounter++
            Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
            it
        }*/
    /*
    .also {
            Timber.d("MainViewModel : init ; RECURSE")
            viewModelScope.asyncDefault {
                val test = controlAsyncAwait(it)
                Timber.d("MainViewModel : init ; RECURSE : test = %s", test)
            }
        }
     */

    /*val repoProm = controlAsync(repository.getSomethingExpensiveUnstructured()).then(null, {
        cockCounter++
        Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
        controlAsync(repository.getSomethingExpensiveUnstructured())
    }).then(null, {
        cockCounter++
        Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
        controlAsync(repository.getSomethingExpensiveUnstructured())
    }).then(null, {
        cockCounter++
        Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
        controlAsync(repository.getSomethingExpensiveUnstructured(true))
    }).then({
        cockCounter++
        Timber.d("MainViewModel : init : repoProm = %s AND COCK = %s", it, cockCounter)
        "$it AND COCK = $cockCounter"
    }, null)*/

    val repoWrapperProm: Promise<String> by lazy {
        CompletableDeferred<String>().also { completable ->
            viewModelScope.launch {
                val repoResult = repository.getSomethingExpensive()
                completable.complete(repoResult)
            }
        }
    }

    val messageFlow = flow<String>{
        emit("Get ready for some coroutine stuff...")
        emit(controlAsyncAwait(repoProm))
        emit(controlAsyncAwait(viewModelScope.asyncDefault {
            cockCounter++
            if(cockCounter == 5){
                "IN THE BUTTTTTTTTTTT"
            }else{
                "NUT SACK"
            }
        }))
        delay(5000)
        emit(controlAsyncAwait(repoWrapperProm.thenAsync{
            if(cockCounter == 5){
                controlAsync(repository.getSomethingExpensiveUnstructured(true))
            }else{
                controlAsync(viewModelScope.asyncDefault {
                    Timber.d("CANCELING")
                    controlCancelAllPromises()
                    "BALLS"
                })
            }
        }))
        /*emit(controlAsyncAwait(repoWrapperProm.then({
            if(cockCounter == 5){
                "IN THE BUTT"
            }else{
                it
            }
        }, null)))*/
    }.flowOn(viewModelScope.defaultDispatcher)

    /*val messageFlow = flow{
        emit("Get ready for some coroutine stuff...")
        emit(repoProm.await())
        delay(5000)
        emit(controlAsyncAwait(repoWrapperProm.then({
            if(cockCounter == 5){
                "IN THE BUTT"
            }else{
                it
            }
        }, null)))
        delay(5000)
        emit(controlAsyncAwait(viewModelScope.asyncDefault {
            cockCounter++
            "MID COCK = $cockCounter"
        }))
        delay(5000)
        emit(controlAsyncAwait(repoWrapperProm.then({
            if(cockCounter == 5){
                "IN THE BUTT"
            }else{
                it
            }
        }, null)))
        delay(5000)
    }.flowOn(viewModelScope.defaultDispatcher)*/


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}