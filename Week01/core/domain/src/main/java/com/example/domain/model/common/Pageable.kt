package com.example.domain.model.common

data class Pageable<T>(
    val query: String = "",
    val contents: List<T> = emptyList(),
    val isEnd: Boolean = false,
    val pageableCount: Int = 0,
    val totalCount: Int = 0,
    val page: Int = 0,
) {
    fun nextPage(query: String): Pageable<T>? {
        if (this.query != query) return Pageable(query = query)
        if (isEnd) return null
        return copy(page = page + 1)
    }

    fun merge(other: Pageable<T>): Pageable<T> {
        val mergedContents = (contents + other.contents).distinct()
        return other.copy(contents = mergedContents)
    }
}
