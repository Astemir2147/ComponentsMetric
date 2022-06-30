/**
 * Класс для хранения компонентов (комплектующих)
 *
 * contractId : Long - идентификатор контракта (при создании в конструкторе всегда
 * указывается 0 для последующей автогенерации id в entity модели)
 *
 * componentName : String - название компонента, включает в себя названия типа
 * комплектующей брэнда и модели
 *
 * personWitchAccept : String - имя работника, принявшего компонент
 *
 * countOfItem : String - количество комплектующих
 *
 * dateOfAccept : String - дата поступления комплектующих
 *
 * statusOfComponent : String - статус компонента (Installed, ReadyForUse, Discarded)
 *
 * @author Asanov Albek
 */

package com.example.feature_insert_data.data.models

data class Component(
    val contractId: Long,
    val componentName: String,
    val personWitchAccept: String,
    val countOfItem: String,
    val dateOfAccept: String,
    val statusOfComponent: String
)