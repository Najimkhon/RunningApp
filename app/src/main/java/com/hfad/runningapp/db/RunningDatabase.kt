package com.hfad.runningapp.db

import androidx.room.Database
import androidx.room.TypeConverters
import com.hfad.runningapp.db.models.Run

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase {
    abstract fun getRunDao(): RunDao
}