/**
 * Интерактор для работы с добавлением компонента в базу и на сервер
 *
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import com.example.feature_insert_data.data.models.Component
import com.google.firebase.firestore.DocumentReference

/**
 * Интерактор для работы с данными, записываемыми в Room и Firebase Firestore
 *
 * @author Asanov Albek 25.06.2022
 */

interface InsertInteractor {
    /**
     * Метод для получения сегодняшней даты
     */
    fun getActualCalendarDate() : String

    /**
     * Метод для составления названия компонента из категории, брэнда и модели
     * @param category - категория компонента (тип комплектующей)
     * @param brand - брэнд (производитель)
     * @param model - модель комплектующей
     * @return полное название комплектующей
     */
    fun buildComponentName(category: String, brand: String, model : String) : String

    /**
     * Добавление компонента в room
     * @param component - добавляемый компонент
     */
    fun insertNewComponent(component: Component) : Long

    /**
     * Добавление компонента на сервер
     * @param component - добавляемый компонент
     */
    fun addComponentToFirebase(component : Component)

    /**
     * Получение документа по его названию (пока не используется)
     * @param docName - название документа в FirebaseFirestore
     * @return документ в виде DocumentReference
     */
    fun getFirebaseDoc(docName : String) : DocumentReference
}