package com.example.core.database.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.database.dao.ComponentsDao
import com.example.core.database.entity.ComponentsEntity

/**
 * My database
 *
 * @author Zashaev Astemir on 2022-04-09
 */
@Database(
    entities = [ComponentsEntity::class], version = 2, exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    /** для получения зависимости в data module в koin */
    abstract fun getDao(): ComponentsDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }

}
