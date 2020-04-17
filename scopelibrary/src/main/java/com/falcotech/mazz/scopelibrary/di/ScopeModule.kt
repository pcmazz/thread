package com.falcotech.mazz.scopelibrary.di

import com.falcotech.mazz.scopelibrary.factory.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScopeModule {

    @Provides
    @Singleton
    internal fun provideDefaultCoroutineScope(): DefaultCoroutineScope =
        defaultCoroutineScope()

    @Provides
    @Singleton
    internal fun provideIOCoroutineScope(): IOCoroutineScope =
        iOCoroutineScope()

    @Provides
    @Singleton
    internal fun provideMainCoroutineScope(): MainCoroutineScope =
        mainCoroutineScope()

    @Provides
    @Singleton
    internal fun provideMainImmediateCoroutineScope(): MainImmediateCoroutineScope =
        mainImmediateCoroutineScope()

    @Provides
    @Singleton
    internal fun provideUnconfinedCoroutineScope(): UnconfinedCoroutineScope =
        unconfinedCoroutineScope()
}