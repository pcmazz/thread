package com.falcotech.mazz.scopelibrary.core

import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel
constructor(promiseManager: PromiseManager) : ViewModel(), PromiseManager by promiseManager{

    override fun onCleared() {
        Timber.d("BaseViewModel : onCleared")
        super.onCleared()
        controlCancelAllPromises()
    }
}