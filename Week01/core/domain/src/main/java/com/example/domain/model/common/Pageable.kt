package com.example.domain.model.common

data class Pageable<T>(
    val contents: List<T>,
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
    val page: Int,
) {
    fun nextPage(): Pageable<T>? {
        if (isEnd) return null
        return copy(page = page + 1)
    }

    fun merge(other: Pageable<T>): Pageable<T> {
        val mergedContents = (contents + other.contents).distinct()
        return other.copy(contents = mergedContents)
    }
}
