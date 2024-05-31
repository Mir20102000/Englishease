package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentSelectedTheoryBinding
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory

class SelectedTheoryFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    lateinit var binding: FragmentSelectedTheoryBinding

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
        super.onViewCreated(view, savedInstanceState)
    }
}