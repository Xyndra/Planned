package de.xyndra.planned

import android.content.Context
import android.util.Log
import de.xyndra.planned.ContextStore.getContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * A sealed class representing the possible responses to a data request.
 */
sealed class DataResponse {
    /**
     * The requested data object was found.
     */
    data class Found<T>(val response: T) : DataResponse()

    /**
     * The requested data object was not found.
     */
    data object NotFound : DataResponse()

    /**
     * An error occurred while decoding the data, most likely due to a type mismatch.
     */
    data object Error : DataResponse()
}

/**
 * A simple data store that persists data to disk.
 */
object DataStore {
    val data = mutableMapOf<String, String>()

    /**
     * Get a value from the data store.
     * @param key The key of the value to get.
     * @return A [DataResponse] representing the result of the request.
     * @see DataResponse
     * @see Serializable
     */
    inline fun <reified T> get(key: String): DataResponse {
        val value = data[key] ?: return DataResponse.NotFound
        return try {
            DataResponse.Found<T>(Json.decodeFromString(value))
        } catch (e: Exception) {
            DataResponse.Error
        }
    }

    /**
     * Put a value into the data store.
     * @param key The key of the value to put.
     * @param value The value to put.
     * @see Serializable
     */
    inline fun <reified T: Any> put(key: String, value: T) {
        assert(value::class.annotations.any { it is Serializable }) { "Value must be serializable" }
        data[key] = Json.encodeToString(value)
        save()
    }

    /**
     * Save the data store to disk.
     */
    fun save() {
        getContext()?.openFileOutput("data", Context.MODE_PRIVATE).use {
            it?.write(Json.encodeToString(data).toByteArray())
        }
    }

    /**
     * Load the data store from disk.
     */
    fun load() {
        // check if file exists
        if (getContext()?.getFileStreamPath("data")?.exists() == false) {
            return
        }

        getContext()?.openFileInput("data").use {
            it?.readBytes()?.let { bytes ->
                data.putAll(Json.decodeFromString<Map<String, String>>(String(bytes)))

            }
        }
    }

    init {
        load()
    }
}

inline fun <reified T: Any> DataStore.getOrElse(key: String, default: T): T {
    assert(default::class.annotations.any { it is Serializable }) { "Default value must be serializable" }
    return when (val response = get<T>(key)) {
        is DataResponse.Found<*> -> {
            Log.v("DataStore", "Found $key")
            response.response as T
        }
        else -> {
            Log.v("DataStore", "Could not find $key, using default")
            put(key, default)
            default
        }
    }
}