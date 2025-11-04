package com.example.data.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@OptIn(ExperimentalSerializationApi::class)
private val jsonFormatter =
    Json {
        prettyPrint = true
        prettyPrintIndent = "  "
        ignoreUnknownKeys = true
    }

fun String.prettyJsonOrNull(): String? {
    val trimmed = trim()
    if (!(trimmed.startsWith("{") && trimmed.endsWith("}")) &&
        !(trimmed.startsWith("[") && trimmed.endsWith("]"))
    ) {
        return null
    }

    return runCatching {
        val element = Json.parseToJsonElement(trimmed)
        jsonFormatter.encodeToString(JsonElement.serializer(), element)
    }.getOrNull()
}
