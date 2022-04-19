package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.DatabaseConst
import com.example.core.database.entity.ComponentsEntity

/**
 * Database Access Object для доступа к компонентам пользователя в базе данных
 *
 * @author Astemir Zashaev on 2022-04-7
 */
@Dao
abstract class ComponentsDao {

    /** Возвращает все комплектующие */
    @Query("SELECT * FROM components")
    abstract fun getAllComponents(): List<ComponentsEntity>

    /** Добавляет компоненты в базу данных */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun setAllComponents(contract: List<ComponentsEntity>)

    /** Возвращает все установленные комплектующие */
    @Query("SELECT * FROM components WHERE ${DatabaseConst.STATUS_OF_COMPONENT} = :status")
    abstract fun getComponentsForStatus(status: String): List<ComponentsEntity>
}