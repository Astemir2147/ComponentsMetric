/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import android.app.DatePickerDialog
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.InsertRepository
import com.google.firebase.firestore.DocumentReference

interface InsertInteractor {
    fun addComponentToDB(component : Component)
    fun getActualCalendarDate() : String
    fun newComponent(
        contractId : Long,
        componentName: String,
        senderName: String, itemsCount: String,
        acceptDate: String) : Component
    fun buildComponentName(category: String, brand: String, model : String) : String
    fun getRepository() : InsertRepository
    fun insertNewComponent(component: Component)

    //fun getComponentsFromFirebase()
    fun addComponentToFirebase(component : Component)
    fun getFirebaseDoc(docName : String) : DocumentReference
}