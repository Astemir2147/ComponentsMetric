package com.example.feature_insert_data.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.feature_insert_data.R
import com.example.feature_insert_data.databinding.FragmentInsertDataBinding
import com.example.feature_insert_data.di.insertInject
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class InsertDataFragment : Fragment(R.layout.fragment_insert_data) {

    companion object {
        const val DEBUG_TAG = "Insert debug"
    }

    private var homePageBinding: FragmentInsertDataBinding? = null
    private val binding get() = homePageBinding!!
    private val insertDataViewModel by viewModel<InsertDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insertInject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homePageBinding = FragmentInsertDataBinding.inflate(inflater, container, false)
        binding.clickableDateTv.text = insertDataViewModel.getDateToday()
        setObservers()
        setClickListeners()
        setAdapters()
        setFocusChanges()
        return binding.root
    }

    private fun setClickListeners() {
        binding.clickableDateTv.setOnClickListener {
            openCalendar()
        }

        binding.addComponentButton.setOnClickListener {

            if (isAnyEmptyField()) {
                snackBar("Пожалуйста, заполните все поля")
            }

            else {
                val componentName = insertDataViewModel.getComponentName(
                    binding.categoryAutoTv.text.toString(),
                    binding.brandAutoTv.text.toString(),
                    binding.modelAutoTv.text.toString()
                )

                val insertedComponent = insertDataViewModel.buildInsertComponent(
                    componentName,
                    binding.acceptedNameIet.text.toString(),
                    binding.countOfComponentsIet.text.toString(),
                    binding.clickableDateTv.text.toString()
                )

                insertDataViewModel.insertNewComponent(insertedComponent)
                insertDataViewModel.addComponentToFirebase(insertedComponent)

                Log.d(DEBUG_TAG, insertedComponent.contractId.toString())

                snackBar("Компонент добавлен")
            }
        }
    }

    private fun snackBar(message : String) {
        Snackbar.make(
            binding.insertFragmentContainer,
            message,
            Snackbar.LENGTH_SHORT)
            .setTextColor(resources.getColor(R.color.light_green, null))
            .show()
    }

    private fun setObservers() {
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

    // Функция проверки на заполненность всех полей
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
            // "$day.${month + 1}.$year"
            val date = "${month+1}.$day.$year"
            if (insertDataViewModel.isCurrentDate(date)) {
                binding.clickableDateTv.text = date
            }
            else {
                snackBar("Некорректная дата")
            }
        }

        DatePickerDialog(
            requireContext(), dateSetListener, year, month, day
        ).show()
    }
}