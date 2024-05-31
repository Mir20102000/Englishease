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
import com.example.englishease.databinding.FragmentNewQuestionBinding
import com.example.englishease.presentation.viewmodel.CreateTestViewModel
import com.example.englishease.presentation.viewmodel.CreateTestViewModelFactory
import com.google.android.material.snackbar.Snackbar


class NewQuestionFragment : Fragment() {
    private var curNum = 0
    private lateinit var binding: FragmentNewQuestionBinding

    private val createTestVM: CreateTestViewModel by activityViewModels {
        CreateTestViewModelFactory(
            requireActivity().application
        )
    }

    private val questions = mutableListOf<String>()
    private val answers = mutableListOf<String>()
    private val answers2 = mutableListOf<String>()
    private val answers3 = mutableListOf<String>()
    private val points = mutableListOf<Int>()
    private val points2 = mutableListOf<Int>()
    private val points3 = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createTestVM.resetCurNum()
        val queCount = createTestVM.queCount.value ?: 9
        for (i in 0 until queCount) {
            questions.add("")
            answers.add("")
            answers2.add("")
            answers3.add("")
            points.add(0)
            points2.add(0)
            points3.add(0)
        }

//        Log.d("TTT", "From NewQuestionFragment:" +
//                "questionCount = " + queCount +
//                "createTestVM.queCount.value = " + createTestVM.queCount.value +
//                "\n" +
//                "questions = " + questions.toString() +
//                "answers = " + answers.toString() +
//                "answers2 = " + answers2.toString() +
//                "answers3 = " + answers3.toString() +
//                "points = " + points.toString() +
//                "points2 = " + points2.toString() +
//                "points3 = " + points3.toString()
//        )

        createTestVM.curNum.observe(viewLifecycleOwner) {
            curNum = it

            if (curNum > 0) {
                binding.saveNewQuestionBackBtn.visibility = View.VISIBLE
            } else {
                binding.saveNewQuestionBackBtn.visibility = View.GONE
            }

            val progressText = "Вопрос # ${it + 1}/${createTestVM.queCount.value}"
            binding.newTest.text = progressText

//            with(binding) {
//                newTest.text = progressText
//                if (points[it] != 0) {
//                    corQue1Rb.isChecked = true
//                    corQue2Rb.isChecked = false
//                    corQue3Rb.isChecked = false
//                } else {
//                    corQue1Rb.isChecked = false
//                    corQue2Rb.isChecked = false
//                    corQue3Rb.isChecked = false
//                }
//                answerOneEdittext.setText(answers[it])
//                answerTwoEdittext.setText(answers2[it])
//                answerThreeEdittext.setText(answers3[it])
//                questionTextEdittext.setText(questions[it])
//            }
        }



        binding.saveNewQuestionForwardBtn.setOnClickListener {
            var textPoint = 0
            var textPoint2 = 0
            var textPoint3 = 0

            if (binding.corQue1Rb.isChecked) {
                textPoint = 1
            }

            if (binding.corQue2Rb.isChecked) {
                textPoint2 = 1
            }

            if (binding.corQue3Rb.isChecked) {
                textPoint3 = 1
            }


            val textVariant = binding.answerOneEdittext.text.toString()
            val textVariant2 = binding.answerTwoEdittext.text.toString()
            val textVariant3 = binding.answerThreeEdittext.text.toString()
            val textQuestion = binding.questionTextEdittext.text.toString()


            if (textQuestion.isEmpty()) {
                Snackbar.make(
                    view,
                    "Напишите текст вопроса",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (textVariant.isEmpty() || textVariant2.isEmpty() || textVariant3.isEmpty()
            ) {
                Snackbar.make(
                    view,
                    "Укажите все варианты ответа",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (textPoint.toString().isEmpty() || textPoint2.toString().isEmpty() || textPoint3.toString().isEmpty()
            ) {
                Snackbar.make(
                    view,
                    "Укажите баллы за все варианты ответа",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

//            if (textPoint.toInt() == 0 || textPoint2.toInt() == 0 ||
//                textPoint3.toInt() == 0
//            ) {
//                Snackbar.make(
//                    view,
//                    "Балл за вариант не может быть нулевым",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//                return@setOnClickListener
//            }



            questions[curNum] = textQuestion
            answers[curNum] = textVariant
            answers2[curNum] = textVariant2
            answers3[curNum] = textVariant3

            try {
                if (textPoint != 0) points[curNum] = textPoint.toInt()
                if (textPoint2 != 0) points2[curNum] = textPoint2.toInt()
                if (textPoint3 != 0) points3[curNum] = textPoint3.toInt()
            } catch (e: java.lang.NumberFormatException) {
                Snackbar.make(
                    view,
                    "Проверьте правильность введённых данных",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            if (curNum + 1 == createTestVM.queCount.value) {
                createTestVM.saveQuestions(
                    questions,
                    answers,
                    answers2,
                    answers3,
                    points,
                    points2,
                    points3
                )
                Log.d(
                    "TTT", "From NewQuestionFragment:" +
                            "question = " + questions +
                            "answers = " + answers +
                            "answers2 = " + answers2 +
                            "answers3 = " + answers3 +
                            "points = " + points +
                            "points2 = " + points2 +
                            "points3 = " + points3
                )

                findNavController()
                    .navigate(R.id.action_newQuestionFragment_to_createResultFragment)
            } else {
                createTestVM.updateCurNum(1)
            }

            binding.saveNewQuestionBackBtn.setOnClickListener {
                val textPoint = binding.corQue1Rb.text.toString()
                val textPoint2 = binding.corQue2Rb.text.toString()
                val textPoint3 = binding.corQue3Rb.text.toString()
                val textVariant = binding.answerOneEdittext.text.toString()
                val textVariant2 = binding.answerTwoEdittext.text.toString()
                val textVariant3 = binding.answerThreeEdittext.text.toString()
                val textQuestion = binding.questionTextEdittext.text.toString()

                questions[curNum] = textQuestion
                answers[curNum] = textVariant
                answers2[curNum] = textVariant2
                answers3[curNum] = textVariant3

                try {
                    if (textPoint != "") points[curNum] = textPoint.toInt()
                    if (textPoint2 != "") points2[curNum] = textPoint2.toInt()
                    if (textPoint3 != "") points3[curNum] = textPoint3.toInt()

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

//            findNavController().navigate(R.id.action_newQuestionFragment_to_menuFragment)
        }
    }
}