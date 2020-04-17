package com.falcotech.mazz.scopelibrary.core

import com.falcotech.mazz.scopelibrary.promise.Promise

interface PromiseManager {

    fun <T> controlAsync(promise: Promise<T>): Promise<T>

    suspend fun <T> controlAsyncAwait(promise: Promise<T>): T

    fun controlCancelAllPromises()

    fun controlSetDebug(block: (String)->Unit)
}