package com.example.englishease.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentAuthorizationBinding
import com.example.englishease.domain.models.User
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class AuthorizationFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentAuthorizationBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authorizeBtn.setOnClickListener {
            binding.authProgressBar.visibility = View.VISIBLE
            val email = binding.loginEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    view,
                    "All fields must be filled",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.authProgressBar.visibility = View.GONE
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    binding.authProgressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        vm.authorize(email)
                        Snackbar.make(
                            view,
                            "Login successful",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_authorizationFragment_to_theoryFragment)
                    } else {
                        Snackbar.make(
                            view,
                            "Login failed, check the data entered",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Snackbar.make(
                        view,
                        "Close the application by closing the tab",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
            super.onViewCreated(view, savedInstanceState)
        }

        binding.createAccBtn.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}