package com.example.feature_login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var wrongDate: String = ""
    private var invalidDate : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel.loginAttemptResultLiveData.observe(viewLifecycleOwner, ::onSuccess)
        loginViewModel.checkLogined()
        wrongDate = getString(R.string.wrongDate)
        invalidDate = getString(R.string.invalidDate)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.authAsGuest.setOnClickListener { goToComponents() }
        binding.loginButton.setOnClickListener {
            binding.root.hideKeyboard()
            signIn()
        }
        binding.createAccount.setOnClickListener { goToRegistration() }
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
                    showSnackBar(wrongDate, binding.root)
                }
            }
        } else {
            showSnackBar(invalidDate, binding.root)
        }
    }
}

