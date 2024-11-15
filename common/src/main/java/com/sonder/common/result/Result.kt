package com.sonder.common.result

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Represents the different states of a result.
 */
sealed class Result<out T> {
	data object Loading : Result<Nothing>()
	data class Success<T>(val data: T) : Result<T>()
	data class Error(val error: Throwable) : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
	.onStart { emit(Result.Loading) }
	.catch {
		// Log error (could switch this for Timber or another logging library, causes Unit test to hang, needs mocking)
		//Log.e("Result", "Error: $it")
		// Emit error
		emit(Result.Error(it))
	}