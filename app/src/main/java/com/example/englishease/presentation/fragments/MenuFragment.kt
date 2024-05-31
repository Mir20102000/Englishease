package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentMenuBinding
import com.example.englishease.presentation.viewmodel.LoginViewModel
import com.example.englishease.presentation.viewmodel.LoginViewModelFactory
import com.example.englishease.presentation.viewmodel.MainViewModel
import com.example.englishease.presentation.viewmodel.MainViewModelFactory


class MenuFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentMenuBinding

    private val loginVM: LoginViewModel by activityViewModels { LoginViewModelFactory(requireActivity().application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginVM.superRole.observe(viewLifecycleOwner) {
            if (it) {
                binding.toNewLessonFragmentCardView.visibility = View.VISIBLE
                binding.toNewTestFragmentCardView.visibility = View.VISIBLE
            } else {
                binding.toNewLessonFragmentCardView.visibility = View.GONE
                binding.toNewTestFragmentCardView.visibility = View.GONE
            }
        }
        binding.toLessonsFragmentCardView.setOnClickListener() {
            findNavController().navigate(R.id.action_menuFragment_to_lessonsFragment)
        }
        binding.toTestsFragmentCardView.setOnClickListener() {
            findNavController().navigate(R.id.action_menuFragment_to_theoryFragment)
        }
        binding.toNewLessonFragmentCardView.setOnClickListener() {
            findNavController().navigate(R.id.action_menuFragment_to_newLessonFragment)
        }
        binding.toNewTestFragmentCardView.setOnClickListener() {
            findNavController().navigate(R.id.action_menuFragment_to_newTestFragment)
        }
    }
}