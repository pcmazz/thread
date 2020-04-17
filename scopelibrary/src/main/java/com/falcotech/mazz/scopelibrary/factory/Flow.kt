package com.falcotech.mazz.scopelibrary.factory

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.coroutineContext

/**
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the **collector** coroutine,
 * then uses its **default** [CoroutineDispatcher] property to call `flowOn(theDispatcher)`,
 * and returns the result.
 *
 * @see flowOn
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.flowOnDefault(): Flow<T> = flow {
    flowOn(coroutineContext.dispatcherProvider.default)
        .collect { emit(it) }
}

/**
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the **collector** coroutine,
 * then uses its **io** [CoroutineDispatcher] property to call `flowOn(theDispatcher)`,
 * and returns the result.
 *
 * @see flowOn
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.flowOnIO(): Flow<T> = flow {
    flowOn(coroutineContext.dispatcherProvider.io)
        .collect { emit(it) }
}

/**
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the **collector** coroutine,
 * then uses its **main** [CoroutineDispatcher] property to call `flowOn(theDispatcher)`,
 * and returns the result.
 *
 * @see flowOn
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.flowOnMain(): Flow<T> = flow {
    flowOn(coroutineContext.dispatcherProvider.main)
        .collect { emit(it) }
}

/**
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the **collector** coroutine,
 * then uses its **mainImmediate** [CoroutineDispatcher] property to call `flowOn(theDispatcher)`,
 * and returns the result.
 *
 * @see flowOn
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.flowOnMainImmediate(): Flow<T> = flow {
    flowOn(coroutineContext.dispatcherProvider.mainImmediate)
        .collect { emit(it) }
}

/**
 * Extracts the [dispatcherProvider] from the `coroutineContext` of the **collector** coroutine,
 * then uses its **unconfined** [CoroutineDispatcher] property to call `flowOn(theDispatcher)`,
 * and returns the result.
 *
 * @see flowOn
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.flowOnUnconfined(): Flow<T> = flow {
    flowOn(coroutineContext.dispatcherProvider.unconfined)
        .collect { emit(it) }
}

fun <T> Flow<T>.distinctWithCache(): Flow<Map<String, T>> = distinctWithCacheBy { it }

fun <T> Flow<T>.distinctWithCache(areEquivalent: (old: T, new: T) -> Boolean): Flow<Map<String, T>> =
    distinctWithCacheBy(keySelector = {it}, areEquivalent = areEquivalent)

fun <T, K> Flow<T>.distinctWithCacheBy(keySelector: (T) -> K): Flow<Map<String, T>> =
    distinctWithCacheBy(keySelector = keySelector, areEquivalent = {old, new -> old == new })

private inline fun <T, K> Flow<T>.distinctWithCacheBy(
    crossinline keySelector: (T) -> K,
    crossinline areEquivalent: (old: K, new: K) -> Boolean
): Flow<Map<String, T>> =
    flow {
        var previousValue: Any? = null
        collect { value ->
            @Suppress("UNCHECKED_CAST")
            if (previousValue === null) {
                previousValue = value
                emit(mapOf("new" to value))
            }else{
                val newKey = keySelector(value)
                val previousKey = keySelector(previousValue as T)
                if(!areEquivalent(previousKey, newKey)){
                    val result = mapOf("new" to value, "old" to (previousValue as T))
                    previousValue = value
                    emit(result)
                }
            }
        }
    }