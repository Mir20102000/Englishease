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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegistrationFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentRegistrationBinding
    private var param1: String? = null
    private var param2: String? = null

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
        binding.registerBtn.setOnClickListener{
            val user = User(
                binding.emailForRegEdittext.text.toString(),
                binding.passForRegEdittext.text.toString()
                )
            vm.register(user)
            if (vm.success.value == true) {
                Snackbar.make(
                    view,
                    "Пользователь ${vm.userName.value.toString()} успешно зарегистрирован",
                    Snackbar.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
            } else {
                Snackbar.make(
                    view,
                    "Регистрация не выполнена, проверьте введённые данные",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.alreadyHaveAnAccBtn.setOnClickListener{
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
