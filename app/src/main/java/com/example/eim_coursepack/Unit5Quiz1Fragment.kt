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
import com.example.eim_coursepack.databinding.FragmentUnit5Quiz1Binding
import com.example.eim_coursepack.databinding.FragmentUnit6Quiz1Binding

class Unit5Quiz1Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. They are those that are not able to perform their regular " +
                "function because of impaired and damaged part.",
            answers = mutableListOf(
                "Functional",
                "Functional but repairable",
                "Condemnable",
                "Non-functional"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3),
        MulChoQuestion(text = "2. Those that are in good condition and can perform its regular " +
                "functions.",
            answers = mutableListOf(
                "Functional",
                "Functional but repairable",
                "Condemnable",
                "Non-functional"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "3. Methods of identifying non-functional tools and equipment, " +
                "which refers to the observation of an expert to the tools and expert.",
            answers = mutableListOf(
                "Visual Inspection",
                "Functionality",
                "Performance",
                "Power supply"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 0),
        MulChoQuestion(text = "4. Methods of identifying non-functional tools and equipment, " +
                "refers to the technical person who has the knowledge and skills about the " +
                "technology.",
            answers = mutableListOf(
                "Visual Inspection",
                "Functionality",
                "Performance",
                "Power supply"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 1),
        MulChoQuestion(text = "5. The tools or instruments activated by air pressure are ______.",
            answers = mutableListOf(
                "Driving tools",
                "Holding tools",
                "Cutting tools",
                "Pneumatic tools"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = 3)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "6. This uses compressed air to flow into the jack cylinder and causes the " +
                    "ram to extend and raise the vehicle.",
            answers = mutableListOf("pneumatic floor jack", "pneumatic floor jacks"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "7. These are tools manipulated by our hands without using electrical energy.",
            answers = mutableListOf("hand tools", "hand tool"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "8. These are used to drive, or turn screws. The common type has a single " +
                    "flat blade for driving screws with slotted heads. The other type has the " +
                    "cross slotted head.",
            answers = mutableListOf("screw drivers", "screw driver", "screwdrivers", "screwdriver"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "9. A tool used to turn screws, nuts and bolts with hexagonal (six-sided) " +
                    "heads. A variety of wrenches are used in the shop.",
            answers = mutableListOf("wrenches", "wrench"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "10. A tool used to remove gears and hubs from shafts, bushings from blind " +
                    "holes, and cylinders’ liners from the engine blocks.",
            answers = mutableListOf("pullers", "puller"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "11. This is used for cleaning the floor and car interiors after service.",
            answers = mutableListOf("vacuum cleaner", "vacuum cleaners"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. This is lighter than a comparable electric drill. Repeatedly stalling " +
                    "or overloading does not damage or overheat the air drill.",
            answers = mutableListOf("air drill", "air drills"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. A gadget that protects workers from injury or illness caused by having " +
                    "contact with the dangers/hazards in the workplace, Used by linemen to " +
                    "remove insulation of wire and cables in low and high voltage transmission " +
                    "lines.",
            answers = mutableListOf("personal protective equipment", "personal protective equipments"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. This wrench uses compressed air to quickly and powerfully turn nuts, " +
                    "bolts, and other objects.",
            answers = mutableListOf("pneumatic torque wrench", "pneumatic torque wrenches"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. These are tools manipulated by our hands and with the use of electrical " +
                    "energy",
            answers = mutableListOf("machine tools", "power tools", "machine tool", "power tool"),
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
        val binding : FragmentUnit5Quiz1Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit5_quiz1, container, false
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

                // Update if new score > prev score
                val prevScore = sharedPref?.getInt("unit5Quiz1Score", 0)!!
                if (prevScore < score) {
                    // Save score and number of questions in shared preferences
                    with (sharedPref?.edit()) {
                        this?.putInt("unit5Quiz1Score", score)
                        this?.putInt("unit5Quiz1NumQuestions", numQuestions)
                        this?.apply()
                    }
                }

                // Navigate to score screen
                view.findNavController().navigate(
                    Unit5Quiz1FragmentDirections
                        .actionUnit5Quiz1FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 5: Lesson 1 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit5Quiz1Binding) {

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
    private fun initQuestions(binding : FragmentUnit5Quiz1Binding) {
        questionIndex = 0

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit5Quiz1Binding) {
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