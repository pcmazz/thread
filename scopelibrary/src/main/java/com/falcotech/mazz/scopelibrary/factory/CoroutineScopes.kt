package com.falcotech.mazz.scopelibrary.factory

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * Marker interface which designates a [CoroutineScope] with a [CoroutineDispatcher] of `default`.
 */
interface DefaultCoroutineScope : CoroutineScope

/**
 * Marker interface which designates a [CoroutineScope] with a [CoroutineDispatcher] of `io`.
 */
interface IOCoroutineScope : CoroutineScope

/**
 * Marker interface which designates a [CoroutineScope] with a [CoroutineDispatcher] of `main`.
 */
interface MainCoroutineScope : CoroutineScope

/**
 * Marker interface which designates a [CoroutineScope] with a [CoroutineDispatcher] of `mainImmediate`.
 */
interface MainImmediateCoroutineScope : CoroutineScope

/**
 * Marker interface which designates a [CoroutineScope] with a [CoroutineDispatcher] of `unconfined`.
 */
interface UnconfinedCoroutineScope : CoroutineScope

/**
 * Factory function for a [defaultCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `default` property of the `DispatcherProvider`.
 *
 * @param job [Job] to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob] if one is not provided.
 * @param dispatcherProvider [dispatcherProvider] to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider] if one is not provided.
 *
 * @see CoroutineScope
 */
fun defaultCoroutineScope(
    job: Job = SupervisorJob(),
    dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
): DefaultCoroutineScope = object :
    DefaultCoroutineScope {
    override val coroutineContext = job + dispatcherProvider.default + dispatcherProvider
}

/**
 * Factory function for a [defaultCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `default` property of the `DispatcherProvider`.
 *
 * @param coroutineContext [CoroutineContext] to be used for the resulting `CoroutineScope`.
 * Any existing [ContinuationInterceptor] will be overwritten.
 * If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider] will be added.
 *
 * @see CoroutineScope
 */
fun defaultCoroutineScope(
    coroutineContext: CoroutineContext
): DefaultCoroutineScope = object :
    DefaultCoroutineScope {
    override val coroutineContext = coroutineContext.withDefaultElements { default }
}

/**
 * Factory function for an [iOCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `io` property of the `DispatcherProvider`.
 *
 * @param job [Job] to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob] if one is not provided.
 * @param dispatcherProvider [dispatcherProvider] to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider] if one is not provided.
 *
 * @see CoroutineScope
 */
fun iOCoroutineScope(
    job: Job = SupervisorJob(),
    dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
): IOCoroutineScope = object :
    IOCoroutineScope {
    override val coroutineContext = job + dispatcherProvider.io + dispatcherProvider
}

/**
 * Factory function for a [iOCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `io` property of the `DispatcherProvider`.
 *
 * @param coroutineContext [CoroutineContext] to be used for the resulting `CoroutineScope`.
 * Any existing [ContinuationInterceptor] will be overwritten.
 * If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider] will be added.
 *
 * @see CoroutineScope
 */
fun iOCoroutineScope(
    coroutineContext: CoroutineContext
): IOCoroutineScope = object :
    IOCoroutineScope {
    override val coroutineContext = coroutineContext.withDefaultElements { io }
}

/**
 * Factory function for a [mainCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `main` property of the `DispatcherProvider`.
 *
 * @param job [Job] to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob] if one is not provided.
 * @param dispatcherProvider [dispatcherProvider] to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider] if one is not provided.
 *
 * @see CoroutineScope
 */
fun mainCoroutineScope(
    job: Job = SupervisorJob(),
    dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
): MainCoroutineScope = object :
    MainCoroutineScope {
    override val coroutineContext = job + dispatcherProvider.main + dispatcherProvider
}

/**
 * Factory function for a [mainCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `main` property of the `DispatcherProvider`.
 *
 * @param coroutineContext [CoroutineContext] to be used for the resulting `CoroutineScope`.
 * Any existing [ContinuationInterceptor] will be overwritten.
 * If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider] will be added.
 *
 * @see CoroutineScope
 */
fun mainCoroutineScope(
    coroutineContext: CoroutineContext
): MainCoroutineScope = object :
    MainCoroutineScope {
    override val coroutineContext = coroutineContext.withDefaultElements { main }
}

/**
 * Factory function for a [mainImmediateCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `mainImmediate` property of the `DispatcherProvider`.
 *
 * @param job [Job] to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob] if one is not provided.
 * @param dispatcherProvider [dispatcherProvider] to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider] if one is not provided.
 *
 * @see CoroutineScope
 */
fun mainImmediateCoroutineScope(
    job: Job = SupervisorJob(), dispatcherProvider:
    DispatcherProvider = DefaultDispatcherProvider()
): MainImmediateCoroutineScope = object :
    MainImmediateCoroutineScope {
    override val coroutineContext = job + dispatcherProvider.mainImmediate +
            dispatcherProvider
}

/**
 * Factory function for a [mainImmediateCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `mainImmediate` property of the `DispatcherProvider`.
 *
 * @param coroutineContext [CoroutineContext] to be used for the resulting `CoroutineScope`.
 * Any existing [ContinuationInterceptor] will be overwritten.
 * If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider] will be added.
 *
 * @see CoroutineScope
 */
fun mainImmediateCoroutineScope(
    coroutineContext: CoroutineContext
): MainImmediateCoroutineScope = object :
    MainImmediateCoroutineScope {
    override val coroutineContext = coroutineContext.withDefaultElements { mainImmediate }
}

/**
 * Factory function for a [unconfinedCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `unconfined` property of the `DispatcherProvider`.
 *
 * @param job [Job] to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob] if one is not provided.
 * @param dispatcherProvider [dispatcherProvider] to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider] if one is not provided.
 *
 * @see CoroutineScope
 */
fun unconfinedCoroutineScope(
    job: Job = SupervisorJob(),
    dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
): UnconfinedCoroutineScope = object :
    UnconfinedCoroutineScope {
    override val coroutineContext = job + dispatcherProvider.unconfined + dispatcherProvider
}

/**
 * Factory function for a [unconfinedCoroutineScope] with a [dispatcherProvider].
 * Dispatch defaults to the `unconfined` property of the `DispatcherProvider`.
 *
 * @param coroutineContext [CoroutineContext] to be used for the resulting `CoroutineScope`.
 * Any existing [ContinuationInterceptor] will be overwritten.
 * If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider] will be added.
 *
 * @see CoroutineScope
 */
fun unconfinedCoroutineScope(
    coroutineContext: CoroutineContext
): UnconfinedCoroutineScope = object :
    UnconfinedCoroutineScope {
    override val coroutineContext = coroutineContext.withDefaultElements { unconfined }
}

private inline fun CoroutineContext.withDefaultElements(
    dispatcherPromise: DispatcherProvider.() -> ContinuationInterceptor
): CoroutineContext {

    val job = get(Job) ?: SupervisorJob()
    val provider = get(DispatcherProvider) ?: DefaultDispatcherProvider()

    return this + job + provider + provider.dispatcherPromise()
}