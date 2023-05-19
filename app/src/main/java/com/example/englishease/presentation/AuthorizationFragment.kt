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
import com.example.englishease.databinding.FragmentAuthorizationBinding
import com.example.englishease.domain.models.User
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AuthorizationFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentAuthorizationBinding
    private var param1: String? = null
    private var param2: String? = null

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
            val user = User(
                binding.loginEdittext.text.toString(),
                binding.passwordEdittext.text.toString()
            )
            vm.authorize(user)
            if (vm.success.value == true) {
                Snackbar.make(
                    view,
                    "Вход выполнен",
                    Snackbar.LENGTH_SHORT
                ).show()
                    findNavController().navigate(R.id.action_authorizationFragment_to_theoryFragment)
                } else {
                Snackbar.make(
                    view,
                    "Вход не выполнен, проверьте введённые данные",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.createAccBtn.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}