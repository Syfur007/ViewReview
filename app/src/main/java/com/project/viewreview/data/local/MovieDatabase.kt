package com.project.viewreview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.viewreview.data.remote.dto.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao
}