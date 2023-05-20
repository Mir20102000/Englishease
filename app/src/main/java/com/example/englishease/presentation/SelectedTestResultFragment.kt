package com.example.englishease.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishease.R
import com.example.englishease.databinding.FragmentSelectedTestResultBinding
import com.example.englishease.presentation.adapter.ResultAdapter
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class SelectedTestResultFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentSelectedTestResultBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedTestResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.testName.observe(viewLifecycleOwner) {
            val test = "All results of \"$it\""
            binding.textViewAllResults.text = test
            vm.getResults(it)
        }
        vm.results.observe(viewLifecycleOwner) {
            val adapter = ResultAdapter(it)
            binding.recyclerViewResults.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerViewResults.adapter = adapter
        }

        binding.buttonToCatalog.setOnClickListener{
            findNavController().navigate(R.id.action_selectedTestResultFragment_to_theoryFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}