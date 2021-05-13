package com.example.eim_coursepack

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz2Binding
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz3Binding

class Unit6Quiz3Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Personal Protective Device use to protect the eye from heat " +
                "and debris that can the damage the eye.",
            answers = mutableListOf(
                "Sun glass",
                "Eye glass",
                "Eye protection",
                "Hour glass"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "2. In using the gloves, is it important to know the voltage " +
                "capacity of a  certain gloves?",
            answers = mutableListOf(
                "No, because it has already an insulator",
                "Yes, because there are some gloves cannot resist the amount of voltage of a " +
                        "certain electrical equipment",
                "No, No need because it is only for compliance only",
                "Yes, to test how the rubber gloves can protect the electrician"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "3. In selecting safety shoes, what are the best characteristics " +
                "to be considered?",
            answers = mutableListOf(
                "As long it is a leather shoes, it is safety shoes",
                "Safety shoes should have rubber sole only.",
                "Safety shoes has metal top and rubber sole to protect the toes form any " +
                        "hard falling objects and rubber sole to avoid electrocution.",
                "Rubber Shoes is a safety shoes"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "4. What is the PPE which protects electrician’s head form " +
                "falling debris?",
            answers = mutableListOf(
                "Hard hat",
                "Helmet",
                "Cap",
                "Hat"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "5. PPE which used as protection of all body parts except to " +
                "hands and feet which made of asbestos for body insulation.",
            answers = mutableListOf(
                "Apron",
                "Coverall",
                "Gloves",
                "Safety shoes"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "6. Personal Hygiene is another important thing to protect or " +
                "body aside from wearing PPE, which of the following is not correct about " +
                "Personal Hygiene?",
            answers = mutableListOf(
                "If you wear protective clothing at work, such as aprons, laboratory coats, " +
                        "overalls, etc., these should be cleaned regularly and you should " +
                        "inspect them for holes or areas that are worn out.",
                "It may seem that the amount of contaminant you can bring home on your " +
                        "clothes or skin is very small and cannot hurt your family",
                "Spreading the hazard‖ involves asbestos, where wives of asbestos workers " +
                        "have developed asbestosis from exposure to the asbestos on their " +
                        "husbands' work clothes.",
                "If you wear protective clothing at work, such as aprons, laboratory coats, " +
                        "overalls, etc., these should  not be cleaned regularly and not needed  " +
                        "to inspect them for holes or areas that are worn out."),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "7. Personal  Protective Equipment used to protect lungs from " +
                "inhaling dust and gas fumes.",
            answers = mutableListOf(
                "Mask",
                "Respirator",
                "Har hat",
                "Gloves"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "8. Personal Protective Equipment which provide protection for " +
                "the sole and toe.",
            answers = mutableListOf(
                "Mask",
                "Respirator",
                "Har hat",
                "Safety shoes"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "9. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("coverall"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("gloves"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("helmet"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("safety shoes"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("eye protection"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("safety harness"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify the Personal Protective Device used by electricians/",
            answers = mutableListOf("respirator"),
            isCorrect = false,
            enteredAns = ""
        ),

    )

    private lateinit var currentMulChoQuestion: MulChoQuestion
    private lateinit var currentIdenQuestion: IdenQuestion
    lateinit var answers: MutableList<String>
    lateinit var questionText : String
    lateinit var enteredAns : String
    private var questionIndex = 0
    private val numQuestions = mulChoQuestions.size + idenQuestions.size
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentUnit6Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit6_quiz3, container, false
        )
        // SharedPreference Object (for storing data locally)
        val sharedPref = this.activity?.getSharedPreferences(
            getString(R.string.preference_key), Context.MODE_PRIVATE)

        // Initialize questions and set the first question
        initQuestions(binding)

        // Attach quiz variable from quiz 1 layout
        binding.quiz = this

        // Set the onClickListener for the nextButton
        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Handle quiz layout logic and scoring
            handleQuizProper(binding)

            // Show back button after 1st question
            if (questionIndex > 0) {
                binding.backButton.visibility = View.VISIBLE
            }

            // Show submit button and hide next button in last question
            if (questionIndex == numQuestions-1) {
                binding.nextButton.visibility = View.INVISIBLE
                binding.submitButton.visibility = View.VISIBLE
            }

            if (questionIndex >= mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.GONE
                binding.answerText.visibility = View.VISIBLE
            }


        }

        // Set the onClickListener for the backButton
        binding.backButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            // Save answer in multiple choice
            when (binding.questionRadioGroup.checkedRadioButtonId) {
                R.id.firstChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 0
                R.id.secondChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 1
                R.id.thirdChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 2
                R.id.fourthChoiceRadioButton -> currentMulChoQuestion.clickedIdx = 3
            }

            // Save answer in identification
            currentIdenQuestion.enteredAns = binding.answerText.text.toString()

            questionIndex--

            if (questionIndex < mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else {
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                binding.answerText.text.clear()

                setIdenQuestion(binding)
            }

            // Reset fields
            binding.invalidateAll()

            // Change to multiple choice if on questions 1-10
            if (questionIndex < mulChoQuestions.size) {
                binding.questionRadioGroup.visibility = View.VISIBLE
                binding.answerText.visibility = View.GONE
            }

            // Hide submit button if not on last question
            if (questionIndex < numQuestions-1) {
                binding.submitButton.visibility = View.GONE
            }

            // Hide back button on 1st question
            if (questionIndex == 0) {
                binding.backButton.visibility = View.INVISIBLE
            }

            // Show next button if not on last question
            if (questionIndex == numQuestions-2) {
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view : View ->
            // Check last question
            val answerText = binding.answerText.text
            currentIdenQuestion.isCorrect = answerText
                .toString()
                .toLowerCase()
                .replace("\\s+".toRegex()," ")
                .trim() in currentIdenQuestion.answers

//            if (currentIdenQuestion.isCorrect) {
//                Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
//            }

            // To make sure button only works once
            if (score == 0) {

                // Count each correct answer
                mulChoQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }
                idenQuestions.forEach {
                    if (it.isCorrect){
                        score++
                    }
                }

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit6Quiz3Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit6Quiz3Score", score)
                        this?.putString("unit6Quiz3NumQuestions", numQuestions.toString())
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit6Quiz3FragmentDirections
                        .actionUnit6Quiz3FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 6: Lesson 3 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit6Quiz3Binding) {

        // Multiple choice questions 1-10
        if (questionIndex in 0 until mulChoQuestions.size) {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Check if a radio button is selected
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondChoiceRadioButton -> answerIndex = 1
                    R.id.thirdChoiceRadioButton -> answerIndex = 2
                    R.id.fourthChoiceRadioButton -> answerIndex = 3
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                currentMulChoQuestion.isCorrect = answerIndex == currentMulChoQuestion.correctIdx

//                if (currentMulChoQuestion.isCorrect) {
//                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                }

                currentMulChoQuestion.clickedIdx = answerIndex
                binding.questionRadioGroup.clearCheck()


                // Advance to the next question
                questionIndex++


                if (questionIndex in 0 until mulChoQuestions.size) {
                    currentMulChoQuestion = mulChoQuestions[questionIndex]
                    setMulChoQuestion(binding)

                } else if (questionIndex in mulChoQuestions.size until numQuestions) {

                    currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                    setIdenQuestion(binding)
                }
                binding.invalidateAll()


                // Prompt user to select an answer
            } else {
                Toast.makeText(context, "Choose your answer", Toast.LENGTH_SHORT).show()
            }

            // Identification questions 11-15
        } else if (questionIndex in mulChoQuestions.size until numQuestions) {

            val answerText = binding.answerText.text

            if (answerText.isNotEmpty()) {

                if (questionIndex in 5 until numQuestions) {
                    currentIdenQuestion.isCorrect = answerText
                        .toString()
                        .toLowerCase()
                        .replace("\\s+".toRegex()," ")
                        .trim() in currentIdenQuestion.answers

//                    if (currentIdenQuestion.isCorrect) {
//                        Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
//                    }
                }

                currentIdenQuestion.enteredAns = answerText.toString()

                // Advance to the next question
                questionIndex++
                currentIdenQuestion = idenQuestions[questionIndex - mulChoQuestions.size]
                setIdenQuestion(binding)

                // reset fields
                binding.invalidateAll()
                answerText.clear()


            } else {
                Toast.makeText(context,"Enter your answer", Toast.LENGTH_SHORT).show()
            }
        } else {
            // For error checking only
            Toast.makeText(context, "Error index out of range: $questionIndex", Toast.LENGTH_LONG).show()
        }
    }

    // Initialize the questions and set the first question
    private fun initQuestions(binding : FragmentUnit6Quiz3Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit6Quiz3Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text

        binding.quizImage.setImageResource(0)

        answers = currentMulChoQuestion.answers

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion(binding: FragmentUnit6Quiz3Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        questionText = currentIdenQuestion.text

        val questionImage = binding.quizImage

        when(idenQuestionIndex) {
            0 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q9)
            1 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q10)
            2 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q11)
            3 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q12)
            4 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q13)
            5 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q14)
            6 -> questionImage.setImageResource(R.drawable.ic_unit6_quiz3_q15)
        }

        enteredAns = currentIdenQuestion.enteredAns
    }
}