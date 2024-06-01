package com.example.englishease.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentRegistrationBinding
import com.example.englishease.presentation.viewmodel.LoginViewModel
import com.example.englishease.presentation.viewmodel.LoginViewModelFactory
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentRegistrationBinding
    private var auth = FirebaseAuth.getInstance()
    private val loginVM: LoginViewModel by activityViewModels { LoginViewModelFactory(requireActivity().application) }


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

        binding.adminCheckbox.setOnClickListener {
            val isAdminCheck = "icandoit422"
            val title = "Подтвердите права"
            val message = "Введите проверочное слово"
            val yesButtonString = "Подтвердить"
            val noButtonString = "Отмена"
            val builder = AlertDialog.Builder(requireActivity())
            val edittext = EditText(requireActivity());
            edittext.setHint("Слово")
            builder.setTitle(title)
                .setMessage(message)
                .setView(edittext)
                .setPositiveButton(yesButtonString) { _, _ ->
                    val result = edittext.text.toString()
                    if (result == isAdminCheck) {
                        loginVM.setRole(true)
                        Snackbar.make(
                            view,
                            "Права админа подтверждены",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        loginVM.setRole(false)
                        binding.adminCheckbox.isChecked = false
                        Snackbar.make(
                            view,
                            "Проверочное слово неверное",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }


                }
                .setNegativeButton(noButtonString) { dialog, id ->
                    binding.adminCheckbox.isChecked = false
                    dialog.cancel()
                }
            builder.create().show()
        }

        binding.registerBtn.setOnClickListener {
            binding.registerProgressBar.visibility = View.VISIBLE
            val email = binding.emailForRegEdittext.text.toString()
            val password = binding.passForRegEdittext.text.toString()
            val repeatPassword = binding.repeatPassEdittext.text.toString()
            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Snackbar.make(
                    view,
                    getString(R.string.fill_out_all_input_fields),
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.registerProgressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (password.length < 6) {
                Snackbar.make(
                    view,
                    getString(R.string.password_must_contain_at_least_6_characters),
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.registerProgressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (repeatPassword != password) {
                Snackbar.make(
                    view,
                    getString(R.string.passwords_do_not_match_check_the_data_you_entered),
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
                            getString(
                                R.string.user_successfully_signed_up,
                                vm.userName.value.toString()
                            ),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
                    } else {
                         Snackbar.make(
                            view,
                             getString(R.string.registration_failed_check_the_data_entered),
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
