package com.example.englishease.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentSelectedTestBinding
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class SelectedTestFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentSelectedTestBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.question.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameQuest.text = vm.testName.value
            val text = "${it.questionNumber}/${vm.queCount.value}"
            binding.textViewProgress.text = text
            binding.textViewQuestDeclaration.text = it.questionText
            binding.radioButton1.text = it.variants[0]
            binding.radioButton2.text = it.variants[1]
            binding.radioButton3.text = it.variants[2]
            binding.radioButton4.text = it.variants[3]
        }
        binding.buttonContinue.setOnClickListener {
            var pointResult = 0
            if (binding.radioButton1.isChecked) {
                pointResult += vm.question.value?.points?.get(0) ?: 0
            }
            if (binding.radioButton2.isChecked) {
                pointResult += vm.question.value?.points?.get(1) ?: 0
            }
            if (binding.radioButton3.isChecked) {
                pointResult += vm.question.value?.points?.get(2) ?: 0
            }
            if (binding.radioButton4.isChecked) {
                pointResult += vm.question.value?.points?.get(3) ?: 0
            }

            vm.setPoints(pointResult + (vm.pointsResult.value ?: 0))

        if (vm.queCount.value == vm.question.value?.questionNumber) {
            findNavController().navigate(R.id.action_selectedTestFragment_to_resultFragment)
        } else {
            vm.testName.value?.let { it1 ->
                vm.getQuestion(
                    it1,
                    (vm.question.value?.questionNumber?.plus(1) ?: 1)) }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}