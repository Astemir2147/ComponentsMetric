package com.example.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.DatabaseConst.ACCEPTED
import com.example.core.database.DatabaseConst.COMPONENTS
import com.example.core.database.DatabaseConst.COMPONENT_NAME
import com.example.core.database.DatabaseConst.COUNT_OF_ITEM
import com.example.core.database.DatabaseConst.DATE_OF_ACCEPT
import com.example.core.database.DatabaseConst.ID

/**
 * Entity компонентов
 *
 * @author Astemir Zashaev on 2022-04-7
 */

@Entity(tableName = COMPONENTS)
data class ComponentsEntity (
    @PrimaryKey
    @ColumnInfo(name = ID)
    val contractId: Long,

    @ColumnInfo(name = COMPONENT_NAME)
    val componentName: String,

    @ColumnInfo(name = ACCEPTED)
    val accepted: String,

    @ColumnInfo(name = COUNT_OF_ITEM)
    val countOfItem: String,

    @ColumnInfo(name = DATE_OF_ACCEPT)
    val dateOfAccept: String,
)
