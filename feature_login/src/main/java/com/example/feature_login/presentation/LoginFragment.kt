package com.example.feature_login.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.data.model.authDateUserToCookie
import com.example.feature_login.R
import com.example.feature_login.databinding.FragmentLoginBinding
import com.example.feature_login.di.inject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var loginBinding: FragmentLoginBinding? = null
    private val binding get() = loginBinding!!
    private val loginViewModel by viewModel<LoginViewModel>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding = FragmentLoginBinding.bind(view)
        loginViewModel.loginAttemptResultLiveData.observe(viewLifecycleOwner, ::onSuccess)
        loginViewModel.checkLogined()
        binding.authAsGuest.setOnClickListener { goToComponents() }
        binding.createAccount.setOnClickListener { goToRegistration() }
        binding.loginButton.setOnClickListener {
            hideKeyboard(binding.root,requireContext())
            signIn()
        }
    }

    private fun goToRegistration() {
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    private fun getAuthenticateUser(): AuthDateUser {
        val email = binding.loginLoginEditText.text.toString()
        val password = binding.loginPasswordEditText.text.toString()
        return AuthDateUser(email, password)
    }

    private fun goToComponents() {
        findNavController().navigate(R.id.auth_to_components)
    }

    private fun onSuccess(success: Boolean) {
        goToComponents()
    }

    private fun saveCookie(user: AuthDateUser) {
        loginViewModel.saveCookieFromPreferences(user.authDateUserToCookie())
    }

    private fun signIn() {
        val user = getAuthenticateUser()

        if (loginViewModel.validateFields(user)) {
            auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(requireActivity())
            { task ->
                if (task.isSuccessful) {
                    auth.currentUser
                    saveCookie(user)
                    goToComponents()
                } else {
                    showSnackBar(getString(R.string.wrongDate),getString(R.string.cancel))
                }
            }
        } else {
            showSnackBar(getString(R.string.invalidDate),getString(R.string.cancel))
        }
    }
}

