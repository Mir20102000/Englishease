package com.example.englishease.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentResultBinding
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentResultBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.testName.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameResult.text = it
        }
        vm.pointsResult.observe(viewLifecycleOwner) {
            val result = "Test result: ${it.toInt()}"
            binding.textViewResult.text = result
            vm.testName.value?.let { it1 -> vm.userName.value?.let { it2 ->
                vm.getResult(it1, it.toInt(), it2)
//                Log.d("RRR", "Имя = $it2, тест = $it1, баллы = $it")
//                Log.d("RRR", "${vm.textResult.value}")
            } }
        }




        vm.textResult.observe(viewLifecycleOwner) {
            binding.textViewResultText.text = vm.textResult.value
        }
        binding.buttonShowResultsResult.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_selectedTestResultFragment)
        }
        binding.buttonToCatalog.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_theoryFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}