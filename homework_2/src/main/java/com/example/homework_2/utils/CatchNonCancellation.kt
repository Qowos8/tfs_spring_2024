package com.example.homework_2.utils

import java.util.concurrent.CancellationException

suspend fun <T> runCatchingNonCancellation(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}