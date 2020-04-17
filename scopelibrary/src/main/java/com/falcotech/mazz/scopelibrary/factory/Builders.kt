package com.falcotech.mazz.scopelibrary.factory

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **default** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The *default* property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
suspend fun <T> withDefault(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    val newContext = coroutineContext.dispatcherProvider.default + context
    return withContext(newContext, block)
}

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **io** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
suspend fun <T> withIO(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    val newContext = coroutineContext.dispatcherProvider.io + context
    return withContext(newContext, block)
}

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **main** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
suspend fun <T> withMain(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    val newContext = coroutineContext.dispatcherProvider.main + context
    return withContext(newContext, block)
}

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **mainImmediate** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
suspend fun <T> withMainImmediate(
    context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T
): T {
    val newContext = coroutineContext.dispatcherProvider.mainImmediate + context
    return withContext(newContext, block)
}

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **unconfined** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
suspend fun <T> withUnconfined(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    val newContext = coroutineContext.dispatcherProvider.unconfined + context
    return withContext(newContext, block)
}

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.default`) to call `launch(...)`.
 *
 * The `default` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
fun CoroutineScope.launchDefault(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = launch(coroutineContext.dispatcherProvider.default + context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.io`) to call `launch(...)`.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
fun CoroutineScope.launchIO(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = launch(coroutineContext.dispatcherProvider.io + context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.main`) to call `launch(...)`.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
fun CoroutineScope.launchMain(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = launch(coroutineContext.dispatcherProvider.main + context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.mainImmediate`) to call `launch(...)`.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
fun CoroutineScope.launchMainImmediate(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = launch(coroutineContext.dispatcherProvider.mainImmediate + context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.unconfined`) to call `launch(...)`.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
fun CoroutineScope.launchUnconfined(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = launch(coroutineContext.dispatcherProvider.unconfined + context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.default`) to call `async(...)`.
 *
 * The `default` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
fun <T> CoroutineScope.asyncDefault(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(coroutineContext.dispatcherProvider.default + context, start, block)



/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.io`) to call `async(...)`.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
fun <T> CoroutineScope.asyncIO(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(coroutineContext.dispatcherProvider.io + context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.main`) to call `async(...)`.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
fun <T> CoroutineScope.asyncMain(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(coroutineContext.dispatcherProvider.main + context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.mainImmediate`) to call `async(...)`.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
fun <T> CoroutineScope.asyncMainImmediate(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(coroutineContext.dispatcherProvider.mainImmediate + context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [dispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.unconfined`) to call `async(...)`.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
fun <T> CoroutineScope.asyncUnconfined(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(coroutineContext.dispatcherProvider.unconfined + context, start, block)
