package com.moviecatalog.app.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Type>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    abstract suspend fun execute(params: Params): Type

    suspend operator fun invoke(params: Params): Type =
        withContext(coroutineDispatcher) {
            execute(params)
        }
}
