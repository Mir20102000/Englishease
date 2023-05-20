package com.example.englishease.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englishease.R
import com.example.englishease.databinding.FragmentTheoryBinding
import com.example.englishease.presentation.adapter.TestAdapter
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory


class TheoryFragment : Fragment(), TestAdapter.OnItemClickListener {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentTheoryBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTheoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.userName.observe(viewLifecycleOwner) {
            val greeting = "Choose lesson you want to learn"
            binding.helloTextView.text = greeting
        }
        vm.showTheoryList()
        vm.catalog.observe(viewLifecycleOwner) {
            val listAdapter = TestAdapter(it, this)
            binding.theoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.theoryRecyclerView.adapter = listAdapter
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClickListener(testName: String) {
        vm.saveTestName(testName)
        findNavController().navigate(R.id.action_theoryFragment_to_selectedTheoryFragment)
    }
}