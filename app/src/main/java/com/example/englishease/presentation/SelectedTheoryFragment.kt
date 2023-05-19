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
import com.example.englishease.databinding.FragmentSelectedTheoryBinding
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SelectedTheoryFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    lateinit var binding: FragmentSelectedTheoryBinding
    private var param1: String? = null
    private var param2: String? = null

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
            vm.getDeclTest(it)
            vm.getQuestionCount(it)
        }
        vm.declaration.observe(viewLifecycleOwner) {
            binding.theoryTextTextView.text = it
        }
        binding.buttonShowResultsStart.setOnClickListener {
            findNavController().navigate(R.id.action_selectedTheoryFragment_to_selectedTestResultFragment)
        }
        binding.startTestBtn.setOnClickListener {
            vm.setQuestionNumber(1)
            vm.setPoints(0)
            vm.testName.value?.let { it1 -> vm.getQuestion(it1, 1) }
            findNavController().navigate(R.id.action_selectedTheoryFragment_to_selectedTestFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}