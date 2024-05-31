package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.englishease.R
import com.example.englishease.databinding.FragmentCreateResultBinding
import com.example.englishease.databinding.FragmentNewQuestionBinding
import com.example.englishease.presentation.viewmodel.CreateTestViewModel
import com.example.englishease.presentation.viewmodel.CreateTestViewModelFactory


class CreateResultFragment : Fragment() {
    private lateinit var binding: FragmentCreateResultBinding
    private var curNum = 0
    private val createTestVM: CreateTestViewModel by activityViewModels {
        CreateTestViewModelFactory(
            requireActivity().application
        )
    }

    private val borders = mutableListOf<Int>()
    private val textResults = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}