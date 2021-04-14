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
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz2Binding

class Unit6Quiz2Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. Chemicals with lower flash points present a greater __________.",
            answers = mutableListOf(
                "Personal Hazard",
                "Flammability Hazard",
                "Explosion Hazard",
                "Skin Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "2. Process where a person identify, evaluate and determine the " +
                "solution to the risks.",
            answers = mutableListOf(
                "Chemical Assessment",
                "Risk Assessment",
                "Physical Assessment",
                "NC Assessment"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "3. Any alteration of the physical, chemical and biological " +
                "properties of the atmospheric air, or any discharge thereto of any liquid, " +
                "gaseous or solid substances that will or is likely to create or to render the " +
                "air resources of the country harmful, detrimental, or injurious to public " +
                "health, safety or welfare or which will adversely affect their utilization for " +
                "domestic, commercial, industrial, agricultural, recreational, or other " +
                "legitimate purposes.",
            answers = mutableListOf(
                "Pollution",
                "Atmoshperic Pollution",
                "Air Pollution",
                "Surrounding Pollution"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "4. The cross disciplinary area concerned with the safety, " +
                "health and welfare of people engaged in work or employment.",
            answers = mutableListOf(
                "Waste Management",
                "PEC",
                "ACGIH",
                "OHS"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "5. The collection, transport, processing or disposal, managing " +
                "and monitoring of waste materials.",
            answers = mutableListOf(
                "Waste Management",
                "PEC",
                "ACGIH",
                "OHS"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "6. Once a hazard has been identified and the risk assessed, " +
                "control measures should be put into place. Which of the following is the " +
                "correct hierarchy of control?",
            answers = mutableListOf(
                "Identify the hazard, assess the risk, eliminate the hazard/risk, " +
                        "engineering control, administrative controls, substitution",
                "Identify the risk, assess the hazard, eliminate the hazard/risk, " +
                        "administrative control, engineering control, substitution",
                "Identify the hazard, assess the risk, engineering control, eliminate the " +
                        "hazard and risk, engineering control, substitution",
                "Identify the risk, assess the hazard, eliminate the hazard/risk, " +
                        "engineering control, administrative controls, substitution"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "7. Layla the supervisor wants to replace Mang Sun in his job " +
                "because he cannot perform his job efficiently anymore which can cause accident " +
                "to himself and may affect other workers, what particular control does Layla " +
                "implement in this situation?",
            answers = mutableListOf(
                "Eliminates hazard/risk",
                "Substitution",
                "Identify Hazard",
                "Assess Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "8. Nana decided to used cut off  which can cut 50 pcs of pipe in " +
                "one hour instead of manual cutting which 3 cutters can 40 pipe in 1 hour, what " +
                "control did Nana considered in this situation?",
            answers = mutableListOf(
                "Eliminates hazard/risk",
                "Substitution",
                "Identify Hazard",
                "Assess Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 2),
        MulChoQuestion(text = "9. Mia is very consistent in Identifying hazards, Analyzing or " +
                "evaluate the risk associated with that hazard and determining appropriate ways " +
                "to eliminate or control the hazard. What stage being execute by Mia?",
            answers = mutableListOf(
                "Eliminates hazard/risk",
                "Substitution",
                "Identify Hazard",
                "Assess Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "10. Working a limited number of hours in a hazardous area is an " +
                "example of what stage of hierarchy?",
            answers = mutableListOf(
                "Eliminates hazard/risk",
                "Substitution",
                "Identify Hazard",
                "Assess Hazard"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. It includes ear and eye protection, respirators, and protective clothing.",
            answers = mutableListOf("personal protective equipment", "personal protective device"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. It may mean changing a piece of machinery (for example, using proper " +
                    "machine guards) or a work process to reduce exposure to a hazard.",
            answers = mutableListOf("engineering control"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Working a limited number of hours in a hazardous area ",
            answers = mutableListOf("administrative control"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. The process where you identify hazards, analyze or evaluate the risk " +
                    "associated with that hazard, and determine appropriate ways to eliminate or " +
                    "control the hazard.",
            answers = mutableListOf(
                "assess the risks",
                "assess risks",
                "assess risk",
                "assess the risk"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify the source of the problem.",
            answers = mutableListOf(
                "identify hazards",
                "identify hazard",
                "identify the hazards",
                "identify the hazard"),
            isCorrect = false,
            enteredAns = ""
        )
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
        val binding : FragmentUnit6Quiz2Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit6_quiz2, container, false
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

                setIdenQuestion()
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

//              Save score and number of questions in shared preferences
                with (sharedPref?.edit()) {
                    this?.putString("unit6Quiz2Score", score.toString())
                    this?.putString("unit6Quiz2NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
                // Navigate to score screen
                view.findNavController().navigate(
                    Unit6Quiz2FragmentDirections
                        .actionUnit6Quiz2FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 6: Lesson 2 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit6Quiz2Binding) {

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
                    setIdenQuestion()
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
                setIdenQuestion()

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
    private fun initQuestions(binding : FragmentUnit6Quiz2Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit6Quiz2Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }


    private fun setIdenQuestion() {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}