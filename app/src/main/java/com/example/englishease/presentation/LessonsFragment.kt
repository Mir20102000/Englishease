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
import com.example.englishease.databinding.FragmentLessonsBinding
import com.example.englishease.presentation.adapter.LessonAdapter
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory

class LessonsFragment : Fragment(), LessonAdapter.OnItemClickListener {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentLessonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.userName.observe(viewLifecycleOwner) {
            val greeting = "Choose lesson you want to learn"
            binding.helloTextView.text = greeting
        }
        vm.showLessonsList()
        vm.lesson.observe(viewLifecycleOwner) {
            val listAdapter = LessonAdapter(it, this)
            binding.lessonsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.lessonsRecyclerView.adapter = listAdapter
        }
    }

    override fun onItemClickListener(testName: String) {
        vm.saveTestName(testName)
        findNavController().navigate(R.id.action_lessonsFragment_to_selectedTheoryFragment)
    }
}