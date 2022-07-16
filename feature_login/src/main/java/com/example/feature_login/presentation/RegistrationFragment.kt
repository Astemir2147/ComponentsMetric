package com.example.feature_login.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_login.R
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.data.model.authDateNewUserToCookie
import com.example.feature_login.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private var registrationBinding: FragmentRegistrationBinding? = null
    private val binding get() = registrationBinding!!
    private val registrationViewModel by viewModel<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        registrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener {
            binding.root.hideKeyboard()
            registration()
        }
        binding.back.setOnClickListener { findNavController().popBackStack() }
    }

    private fun getUser(): AuthDateUser {
        val email = binding.mailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return AuthDateUser(email, password)
    }

    private fun registration() {
        val user = getUser()
        if (registrationViewModel.validateFields(user)) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        saveCookie(user)
                        goToComponents()
                    }
                }
        } else {
            showSnackBar(invalidDate)
        }
    }

    private fun goToComponents() {
        findNavController().navigate(R.id.registration_to_components)
    }

    private fun saveCookie(user: AuthDateUser) {
        registrationViewModel.saveCookieFromPreferences(user.authDateNewUserToCookie())
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction(cancel) {}.show()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {
        const val invalidDate = "Invalid mail or password"
        const val cancel = "cancel"
    }
}