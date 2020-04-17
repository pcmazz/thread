package com.falcotech.mazz.scopelibrary.factory

import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 * Interface corresponding to the different [CoroutineDispatcher]'s offered by [Dispatchers].
 *
 * Implements the [CoroutineContext.Element] interface
 * so that it can be embedded into the [CoroutineContext] map,
 * meaning that a `CoroutineContext` can be composed with a set of pre-set dispatchers,
 * thereby eliminating the need for singleton references or dependency injecting this interface.
 */
interface DispatcherProvider : CoroutineContext.Element {

    override val key: CoroutineContext.Key<*> get() = Key

    val providerName: String

    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

    companion object Key : CoroutineContext.Key<DispatcherProvider>
}

fun dispatcherProvider(): DispatcherProvider =
    DefaultDispatcherProvider()

/**
 * Default implementation of [dispatcherProvider] which simply delegates to the corresponding
 * properties in the [Dispatchers] singleton.
 *
 * This should be suitable for most production code.
 */
class DefaultDispatcherProvider :
    DispatcherProvider {
    override val providerName: String
        get() = this.javaClass.simpleName
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val mainImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}