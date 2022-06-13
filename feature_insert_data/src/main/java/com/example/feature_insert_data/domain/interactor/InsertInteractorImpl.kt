/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import android.app.DatePickerDialog
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.FirebaseRepository
import com.example.feature_insert_data.domain.InsertRepository
import com.google.firebase.firestore.DocumentReference

class InsertInteractorImpl(
    private val insertRepository: InsertRepository,
    private val firebaseRepository: FirebaseRepository
) : InsertInteractor {
    override fun addComponentToDB(component: Component) {
        insertRepository.addToDatabase(component)
    }

    override fun insertNewComponent(component: Component) {
        insertRepository.addNewComponentToDb(component)
    }

    override fun getActualCalendarDate() = insertRepository.getActualDate()

    override fun getRepository() = insertRepository

    override fun newComponent(
        contractId : Long,
        componentName: String,
        acceptedPersonName: String,
        itemsCount: String,
        acceptDate: String,
    ) = insertRepository.buildComponent(
        contractId, componentName, acceptedPersonName, itemsCount, acceptDate)

    override fun buildComponentName(category: String, brand: String, model: String) =
        insertRepository.getComponentName(category, brand, model)

    /*override fun getComponentsFromFirebase() =
        firebaseRepository.getComponentsFromFirebase()*/

    override fun addComponentToFirebase(component: Component) =
        firebaseRepository.addComponentToFirebase(component)

    override fun getFirebaseDoc(docName: String): DocumentReference =
        firebaseRepository.getFirebaseDoc(docName)
}