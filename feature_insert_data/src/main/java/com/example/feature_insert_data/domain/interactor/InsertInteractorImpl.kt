/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.FirebaseRepository
import com.example.feature_insert_data.domain.InsertRepository
import com.google.firebase.firestore.DocumentReference

/**
 * Имплементация методов из InsertInteractor
 *
 * @author Asanov Albek 25.06.2022
 */

class InsertInteractorImpl(
    private val insertRepository: InsertRepository,
    private val firebaseRepository: FirebaseRepository
) : InsertInteractor {

    override fun insertNewComponent(component: Component) {
        insertRepository.addNewComponentToDb(component)
    }

    override fun getActualCalendarDate() = insertRepository.getActualDate()

    override fun buildComponentName(category: String, brand: String, model: String) =
        insertRepository.getComponentName(category, brand, model)

    override fun addComponentToFirebase(component: Component) =
        firebaseRepository.addComponentToFirebase(component)

    override fun getFirebaseDoc(docName: String): DocumentReference =
        firebaseRepository.getFirebaseDoc(docName)
}