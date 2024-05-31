package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentLessonsBinding
import com.example.englishease.databinding.FragmentSelectedLessonBinding
import com.example.englishease.presentation.viewmodel.LoginViewModel
import com.example.englishease.presentation.viewmodel.LoginViewModelFactory
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar


class SelectedLessonFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentSelectedLessonBinding
    private val loginVM: LoginViewModel by activityViewModels {
        LoginViewModelFactory(
            requireActivity().application
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedLessonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginVM.superRole.observe(viewLifecycleOwner) {
            if (it) {
                binding.buttonDeleteLesson.visibility = View.VISIBLE
            } else {
                binding.buttonDeleteLesson.visibility = View.GONE
            }
        }
        vm.testName.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameStart.text = it
            vm.getDeclTest(it)
//            vm.getQuestionCount(it)
        }
        vm.declaration.observe(viewLifecycleOwner) {
            binding.theoryTextTextView.text = it
        }

        binding.buttonDeleteLesson.setOnClickListener {
            vm.testName.value?.let { it1 -> vm.deleteLesson(it1) }
            Snackbar.make(
                view,
                getString(R.string.lesson_has_successfully_removed),
                Snackbar.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_selectedLessonFragment_to_lessonsFragment)
//            val builder = AlertDialog.Builder(requireActivity())
//            val inflater = layoutInflater
//            val dialogLayout = inflater.inflate(R.layout.confirm_alert_dialog, null)
//            val passwordConfirmEditText =
//                dialogLayout.findViewById<EditText>(R.id.editTextPasswordConfirm)
//
//            with(builder) {
//                setTitle("Подтвердите действие")
//                setPositiveButton("OK") { dialog, which ->
//                    if (passwordConfirmEditText.text.toString() == loginVM.password.value) {
//                        catalogVM.testName.value?.let { it1 -> testVM.deleteTest(it1) }
//                        Snackbar.make(
//                            view,
//                            "Тест удалён успешно",
//                            Snackbar.LENGTH_SHORT
//                        ).show()
//                        binding.buttonDeleteTestStart.visibility = View.GONE
//                        binding.buttonStartTest.visibility = View.GONE
//                        binding.buttonShowResultsStart.visibility = View.GONE
//                    } else {
//                        Snackbar.make(
//                            view,
//                            "Пароль неверный",
//                            Snackbar.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//                setView(dialogLayout)
//                show()
//            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}