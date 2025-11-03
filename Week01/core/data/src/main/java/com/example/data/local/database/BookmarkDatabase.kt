package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookmarkEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        private const val DATABASE_NAME = "bookmark_database"

        internal fun create(context: Context): BookmarkDatabase =
            Room
                .databaseBuilder(
                    context = context.applicationContext,
                    klass = BookmarkDatabase::class.java,
                    name = DATABASE_NAME,
                ).fallbackToDestructiveMigration(false)
                .build()
    }
}
