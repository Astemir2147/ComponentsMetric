package com.example.feature_login.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_login.R
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.data.model.authDateUserToCookie
import com.example.feature_login.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private var registrationBinding: FragmentRegistrationBinding? = null
    private val binding get() = registrationBinding!!
    private val registrationViewModel by viewModel<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationBinding = FragmentRegistrationBinding.bind(view)
        binding.back.setOnClickListener { findNavController().popBackStack() }
        binding.registrationButton.setOnClickListener {
            hideKeyboard()
            registration()
        }
    }

    private fun getUser(): AuthDateUser {
        val email = binding.mailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return AuthDateUser(email, password)
    }

    private fun registration() {
        val user = getUser()
        val invalidDate = getString(R.string.invalidDate)
        if (registrationViewModel.validateFields(user)) {
            if (registrationViewModel.passwordDifficult(user.password)) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(user.email, user.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveCookie(user)
                            goToComponents()
                        }
                    }
            } else {
                seekPassword()
            }
        } else {
            showSnackBar(invalidDate, getString(R.string.cancel))
        }
    }

    private fun goToComponents() {
        findNavController().navigate(R.id.registration_to_components)
    }

    private fun saveCookie(user: AuthDateUser) {
        registrationViewModel.saveCookieFromPreferences(user.authDateUserToCookie())
    }

    private fun seekPassword() {
        binding.passwordInputLayout.error = getString(R.string.passSeekRecommendation)
    }
}