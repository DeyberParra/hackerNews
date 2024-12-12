package com.deyber.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deyber.database.daos.HitDao
import com.deyber.database.entities.HighlightResultEntity
import com.deyber.database.entities.HitEntity

@Database(
    entities = [HitEntity::class, HighlightResultEntity::class],
    version = 1,
    exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract fun hitDao(): HitDao
}