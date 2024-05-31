package com.example.englishease.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentNewTestBinding
import com.example.englishease.presentation.viewmodel.CreateTestViewModel
import com.example.englishease.presentation.viewmodel.CreateTestViewModelFactory
import com.google.android.material.snackbar.Snackbar


class NewTestFragment : Fragment() {
    private lateinit var binding: FragmentNewTestBinding

    private val createTestVM: CreateTestViewModel by activityViewModels {
        CreateTestViewModelFactory(
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
        binding = FragmentNewTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveNewTestBtn.setOnClickListener {
            val testName = binding.testNameEdittext.text.toString()
            val queCount = binding.questionCountEdittext.text.toString()
            if (testName.isEmpty() || queCount.isEmpty()) {
                Snackbar.make(
                    view,
                    getString(R.string.all_fields_must_be_filled),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            try {
                if (queCount.toInt() < 5 || queCount.toInt() > 20) {
                    Snackbar.make(
                        view,
                        getString(R.string.questions_must_be_at_least_5_and_no_more_than_20),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                createTestVM.saveTestTitle(testName, queCount.toInt())
                Log.d("TTT", testName + " " + queCount.toInt())

            } catch (e: java.lang.NumberFormatException) {
                Snackbar.make(
                    view,
                    getString(R.string.check_the_data_you_entered),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_newTestFragment_to_newQuestionFragment)
            Snackbar.make(view, getString(R.string.new_question_created), Snackbar.LENGTH_SHORT).show()
        }
    }
}