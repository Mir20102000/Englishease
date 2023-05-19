package com.example.englishease.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentRegistrationBinding
import com.example.englishease.domain.models.User
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentRegistrationBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerBtn.setOnClickListener {
            binding.registerProgressBar.visibility = View.VISIBLE
            val email = binding.emailForRegEdittext.text.toString()
            val password = binding.passForRegEdittext.text.toString()
            val repeatPassword = binding.repeatPassEdittext.text.toString()
            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Snackbar.make(
                    view,
                    "Fill out all input fields",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.registerProgressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (password.length < 6) {
                Snackbar.make(
                    view,
                    "Password must contain at least 6 characters",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.registerProgressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (repeatPassword != password) {
                Snackbar.make(
                    view,
                    "Passwords do not match, check the data you entered",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.registerProgressBar.visibility = View.GONE
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    binding.registerProgressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        vm.authorize(email)
                        Snackbar.make(
                            view,
                            "User ${vm.userName.value.toString()} successfully signed up",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
                    } else {
                        Snackbar.make(
                            view,
                            "Registration failed, check the data entered",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        super.onViewCreated(view, savedInstanceState)

        binding.alreadyHaveAnAccBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
