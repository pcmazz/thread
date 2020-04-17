package com.falcotech.mazz.scopelibrary.factory

import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 * Extracts the **default** [CoroutineDispatcher] out of the [CoroutineScope],
 * creating a new instance of a [DefaultDispatcherProvider] to provide one if necessary.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.defaultDispatcher: CoroutineDispatcher
    get() = dispatcherProvider.default

/**
 * Extracts the **io** [CoroutineDispatcher] out of the [CoroutineScope],
 * creating a new instance of a [DefaultDispatcherProvider] to provide one if necessary.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.ioDispatcher: CoroutineDispatcher
    get() = dispatcherProvider.io

/**
 * Extracts the **main** [CoroutineDispatcher] out of the [CoroutineScope],
 * creating a new instance of a [DefaultDispatcherProvider] to provide one if necessary.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.mainDispatcher: CoroutineDispatcher
    get() = dispatcherProvider.main

/**
 * Extracts the **mainImmediate** [CoroutineDispatcher] out of the [CoroutineScope],
 * creating a new instance of a [DefaultDispatcherProvider] to provide one if necessary.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.mainImmediateDispatcher: CoroutineDispatcher
    get() = dispatcherProvider.mainImmediate

/**
 * Extracts the **unconfined** [CoroutineDispatcher] out of the [CoroutineScope],
 * creating a new instance of a [DefaultDispatcherProvider] to provide one if necessary.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.unconfinedDispatcher: CoroutineDispatcher
    get() = dispatcherProvider.unconfined

/**
 * Extracts the [dispatcherProvider] out of the [CoroutineScope],
 * or returns a new instance of a [DefaultDispatcherProvider] if the `coroutineContext`
 * does not have one specified.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineScope.dispatcherProvider: DispatcherProvider
    get() = coroutineContext.dispatcherProvider

/**
 * Extracts the [dispatcherProvider] out of the [CoroutineContext],
 * or returns a new instance of a [DefaultDispatcherProvider] if the `CoroutineContext`
 * does not have one specified.
 *
 * Note that `CoroutineContext` is immutable, so if a new `DefaultDispatcherProvider` is needed,
 * a new instance will be created each time.
 */
val CoroutineContext.dispatcherProvider: DispatcherProvider
    get() = get(DispatcherProvider) ?: DefaultDispatcherProvider()

val CoroutineContext.providerName: String
    get() = (get(DispatcherProvider) ?: DefaultDispatcherProvider()).providerName