package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentSelectedTheoryBinding
import com.example.englishease.presentation.viewmodel.LoginViewModel
import com.example.englishease.presentation.viewmodel.LoginViewModelFactory
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class SelectedTheoryFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    lateinit var binding: FragmentSelectedTheoryBinding
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

        binding = FragmentSelectedTheoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginVM.superRole.observe(viewLifecycleOwner) {
            if (it) {
                binding.buttonDeleteTest.visibility = View.VISIBLE
            } else {
                binding.buttonDeleteTest.visibility = View.GONE
            }
        }


        vm.testName.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameStart.text = it
            vm.getQuestionCount(it)
        }
        binding.buttonShowResultsStart.setOnClickListener {
            findNavController().navigate(R.id.action_selectedTheoryFragment_to_selectedTestResultFragment)
        }
        binding.startTestBtn.setOnClickListener {
            vm.setQuestionNumber(1)
            vm.setPoints(0)
            vm.testName.let { vm.showQuestionsList(it.toString()) }
            findNavController().navigate(R.id.action_selectedTheoryFragment_to_selectedTestFragment)
        }
        binding.buttonDeleteTest.setOnClickListener {
            val title = getString(R.string.confirm_action)
            val message = getString(R.string.you_want_to_delete_test)
            val yesButtonString = getString(R.string.confirm)
            val noButtonString = getString(R.string.cancel)

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesButtonString) { _, _ ->
                    vm.testName.value?.let { it1 -> vm.deleteTest(it1) }
                    Snackbar.make(
                        view,
                        getString(R.string.test_has_successfully_removed),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_selectedTheoryFragment_to_theoryFragment)
                }
                .setNegativeButton(noButtonString) {
                        dialog, id ->  dialog.cancel()
                }
            builder.create().show()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}