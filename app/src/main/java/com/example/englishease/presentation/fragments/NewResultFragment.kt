package com.example.englishease.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.englishease.R
import com.example.englishease.databinding.FragmentNewResultBinding
import com.example.englishease.presentation.viewmodel.CreateTestViewModel
import com.example.englishease.presentation.viewmodel.CreateTestViewModelFactory
import com.google.android.material.snackbar.Snackbar


class NewResultFragment : Fragment() {
    private lateinit var binding: FragmentNewResultBinding
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
        binding = FragmentNewResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createTestVM.resetCurNum()
        for (i in 0..8) {
            borders.add(0)
            textResults.add("")
        }
        borders[0] = 0

        createTestVM.curNum.observe(viewLifecycleOwner) {
            curNum = it
            if (curNum > 0) {
                binding.buttonCreateResultBack.visibility = View.VISIBLE
            } else {
                binding.buttonCreateResultBack.visibility = View.GONE
            }
            with(binding) {
                val borderText = "В диапазоне от ${borders[curNum]} до:"
                textViewMinBorder.text = borderText
                if (borders[it + 1] != 0) {
                    editTextMaxBorder.setText(borders[it + 1].toString())
                } else {
                    editTextMaxBorder.setText("")
                }
                editTextTextResult.setText(textResults[it])
            }
        }

        binding.buttonCreateResultToCatalog.setOnClickListener {
            val textResult = binding.editTextTextResult.text.toString()
            val maxBorder = binding.editTextMaxBorder.text.toString()
            if (maxBorder.isEmpty()) {
                Snackbar.make(
                    view,
                    "Укажите верхнюю границу диапазона",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (textResult.isEmpty()) {
                Snackbar.make(
                    view,
                    "Укажите комментарий к результату",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            try {
                if (maxBorder.toInt() > 20) {
                    Snackbar.make(
                        view,
                        "Верхняя граница не может быть больше 20",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (maxBorder.toInt() <= borders[curNum]) {
                    Snackbar.make(
                        view,
                        "Верхняя граница должна быть больше нижней",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                borders[curNum + 1] = maxBorder.toInt()
            } catch (e: java.lang.NumberFormatException) {
                Snackbar.make(
                    view,
                    "Проверьте правильность введённых данных",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            textResults[curNum] = textResult
            if (borders[curNum + 1] == 5) {
                borders[curNum + 1] = 6
                createTestVM.saveTextResults(textResults, borders)
                createTestVM.saveTest()
                Snackbar.make(
                    view,
                    "Тест создан успешно",
                    Snackbar.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_createResultFragment_to_menuFragment)
            } else {
                createTestVM.updateCurNum(1)
            }
        }

        binding.buttonCreateResultBack.setOnClickListener {
            val textResult = binding.editTextTextResult.text.toString()
            val maxBorder = binding.editTextMaxBorder.text.toString()
            textResults[curNum] = textResult
            try {
                if (maxBorder != "") borders[curNum + 1] = maxBorder.toInt()
            } catch (e: java.lang.NumberFormatException) {
                Snackbar.make(
                    view,
                    "Проверьте правильность введённых данных",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            createTestVM.updateCurNum(-1)
        }


        super.onViewCreated(view, savedInstanceState)
    }
}