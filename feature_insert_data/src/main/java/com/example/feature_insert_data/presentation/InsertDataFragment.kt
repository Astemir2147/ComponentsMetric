package com.example.feature_insert_data.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.feature_insert_data.R
import com.example.feature_insert_data.data.extantion.snackBar
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.databinding.FragmentInsertDataBinding
import com.example.feature_insert_data.di.insertInject
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

/**
 * Фрагмент для добавления комплектующих
 *
 * @author Asanov Albek 25.06.2022
 */
class InsertDataFragment : Fragment(R.layout.fragment_insert_data) {

    private var homePageBinding: FragmentInsertDataBinding? = null
    private val binding get() = homePageBinding!!
    private val insertDataViewModel by viewModel<InsertDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insertInject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePageBinding = FragmentInsertDataBinding.bind(view)
        binding.clickableDateTv.text = insertDataViewModel.getDateToday()
        setClickListeners()
        setAdapters()
        setFocusChanges()
    }

    private fun setClickListeners() {
        binding.clickableDateTv.setOnClickListener { openCalendar() }

        binding.addComponentButton.setOnClickListener {
            if (isAnyEmptyField()) {
                val fillAllFieldsMessage =
                    requireActivity().resources.getString(R.string.please_fill_all_fields)
               snackBar(fillAllFieldsMessage)
            }

            else {
                val componentName = insertDataViewModel.getComponentName(
                    binding.categoryAutoTv.text.toString(),
                    binding.brandAutoTv.text.toString(),
                    binding.modelAutoTv.text.toString()
                )

                val insertedComponent = Component(
                    componentName = componentName,
                    personWitchAccept = binding.acceptedNameIet.text.toString(),
                    countOfItem = binding.countOfComponentsIet.text.toString(),
                    dateOfAccept = binding.clickableDateTv.text.toString()
                )

                insertDataViewModel.insertNewComponent(insertedComponent)
                insertDataViewModel.addComponentToFirebase(insertedComponent)

                val componentAddedMessage =
                    requireActivity().resources.getString(R.string.component_added)
                snackBar(componentAddedMessage)
            }
        }
    }

    private fun setAdapters() {

        val categoryAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.categories)
        )

        val brandAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.brands)
        )

        val modelsAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.models)
        )

        with(binding) {
            categoryAutoTv.setAdapter(categoryAdapter)
            brandAutoTv.setAdapter(brandAdapter)
            modelAutoTv.setAdapter(modelsAdapter)
        }
    }

    private fun setFocusChanges() {
        binding.brandAutoTv.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.brandAutoTv.showDropDown()
            }
        }

        binding.categoryAutoTv.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.categoryAutoTv.showDropDown()
            }
        }

        binding.modelAutoTv.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.modelAutoTv.showDropDown()
            }
        }
    }

    /** Функция проверки на заполненность всех полей */
    private fun isAnyEmptyField() : Boolean =
        with(binding) {
            brandAutoTv.text.isEmpty() || categoryAutoTv.text.isEmpty() ||
                    modelAutoTv.text.isEmpty() || acceptedNameIet.text.isNullOrEmpty() ||
                    countOfComponentsIet.text.isNullOrEmpty()
        }

    private fun openCalendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = DatePickerDialog.OnDateSetListener {
                _, year, month, day ->
            val date = "$day.${month + 1}.$year"
            if (insertDataViewModel.isCurrentDate(date)) {
                binding.clickableDateTv.text = date
            }
            else {
                val incorrectDateMessage =
                    requireActivity().resources.getString(R.string.incorrect_date)
                snackBar(incorrectDateMessage)
            }
        }

        DatePickerDialog(
            requireContext(), dateSetListener, year, month, day
        ).show()
    }
}