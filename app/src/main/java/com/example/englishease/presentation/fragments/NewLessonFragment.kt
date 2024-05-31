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
import com.example.englishease.databinding.FragmentNewLessonBinding
import com.example.englishease.presentation.viewmodel.CreateLessonViewModel
import com.example.englishease.presentation.viewmodel.CreateLessonViewModelFactory
import com.example.englishease.presentation.viewmodel.CreateTestViewModel
import com.example.englishease.presentation.viewmodel.CreateTestViewModelFactory
import com.google.android.material.snackbar.Snackbar


class NewLessonFragment : Fragment() {
    private lateinit var binding: FragmentNewLessonBinding

    private val createLessonVm: CreateLessonViewModel by activityViewModels {
        CreateLessonViewModelFactory(
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
        binding = FragmentNewLessonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveNewLessonBtn.setOnClickListener {
            val name = binding.newLessonNameEdittext.text.toString()
            val desc = binding.lessonDescEdittext.text.toString()
            val content = binding.lessonContentEdittext.text.toString()

            if (name.isNotEmpty() || desc.isNotEmpty() || content.isNotEmpty()) {
                createLessonVm.saveLessonUseCase(name, desc, content)
                Log.d("TTT", createLessonVm.saveLessonUseCase(name, desc, content).toString())
                findNavController().navigate(R.id.action_newLessonFragment_to_menuFragment)
                Snackbar.make(view, getString(R.string.new_lesson_created), Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(
                    view,
                    getString(R.string.all_fields_must_be_filled),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }
}