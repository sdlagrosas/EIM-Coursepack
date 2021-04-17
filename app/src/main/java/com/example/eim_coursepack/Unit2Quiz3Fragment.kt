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
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz2Binding
import com.example.eim_coursepack.databinding.FragmentUnit2Quiz3Binding

class Unit2Quiz3Fragment : Fragment() {

    private val mulChoQuestions: MutableList<MulChoQuestion> = mutableListOf(
        MulChoQuestion(text = "1. It is used where the tap wire is under considerable tensile " +
                "stress circuit.",
            answers = mutableListOf(
                "Plain Tap Joint",
                "Aerial Tap Joint",
                "Rat Tail Joint",
                "Y-splice"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "2. It is a kind of joint is commonly used to join two or more " +
                "conductors inside the junction box. It is suitable for service where there is no" +
                " mechanical stress when wires are to be connected in an outlet box, switch, or " +
                "conduit fitting.",
            answers = mutableListOf(
                "Rat Tap Joint ",
                "Plain Tap Joint",
                "Aerial Tail Joint",
                "Y-splice"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "3. Is used as a temporary tap usually done in constructions sites." +
                " The easy twist will facilitate tap wire movement.",
            answers = mutableListOf(
                "Aerial Tap Joint ",
                "Plain Tap Joint",
                "Rat Tail Joint",
                "Y-splice"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "4. A two-tap wire turned simultaneously and is used where the " +
                "two-tap wire is under heavy tensile stress.",
            answers = mutableListOf(
                "Duplex cross joint ",
                "Plain Tap Joint",
                "Rat Tail Joint",
                "Aerial Tap Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "5. This is used where the tap wire is under heavy tensile stress.",
            answers = mutableListOf(
                "Knotted Tap",
                "Aerial Tap Joint",
                "Rat Tail Joint",
                "Duplex Cross Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "6. This is the most widely used splice or joint in interior " +
                "wiring installation to extend the length of wire from one point to another.",
            answers = mutableListOf(
                "Western Union Long Tie",
                "Western Union Short",
                "Duplex Cross Joint",
                "Cross Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "7. This is used on large solid conductors where it is difficult " +
                "to wrap the heavy tap wire around the main wire.",
            answers = mutableListOf(
                "Wrapped Tap or Tee Joint",
                "Western Union Short",
                "Western Union Long Tie",
                "Cross Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "8. It is the method of wrapping is generally used on small cables " +
                "because the strands are flexible, and all can be wrapped in one operation.",
            answers = mutableListOf(
                "Y-splice",
                "Wrapped Tap or Tee Joint",
                "Western Union Long Tie",
                "Cross Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "9. The same application is done as in plain tap and the only " +
                "difference is that this tap is a combination of two plain taps place side by " +
                "side with each other.",
            answers = mutableListOf(
                "Cross Joint",
                "Y-splice",
                "Western Union Long Tie",
                "Wrapped Tap or Tee Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1),
        MulChoQuestion(text = "10. This is the most widely used splice or joint in interior " +
                "wiring installation to extend the length of wire from one point to another.",
            answers = mutableListOf(
                "Western Union Short",
                "Wrapped Tap or Tee Joint",
                "Western Union Long Tie",
                "Cross Joint"),
            isCorrect = false,
            clickedIdx = -1,
            correctIdx = -1)
    )

    private val idenQuestions : MutableList<IdenQuestion> = mutableListOf(
        IdenQuestion(
            text = "11. Identify the type of slice or joint.",
            answers = mutableListOf("western union short"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "12. Identify the type of slice or joint.",
            answers = mutableListOf("rat tail", "rat tail joint"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "13. Identify the type of slice or joint.",
            answers = mutableListOf("duplex cross joint", "duplex cross"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "14. Identify the type of slice or joint.",
            answers = mutableListOf("knotted tap"),
            isCorrect = false,
            enteredAns = ""
        ),
        IdenQuestion(
            text = "15. Identify the type of slice or joint.",
            answers = mutableListOf("plain tap joint"),
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
        val binding : FragmentUnit2Quiz3Binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_unit2_quiz3, container, false
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

            // Hide radio group and show edit text in identification part of the quiz
            if (questionIndex > mulChoQuestions.size-1) {
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


            if (questionIndex in 0 until mulChoQuestions.size) {
                currentMulChoQuestion = mulChoQuestions[questionIndex]
                binding.questionRadioGroup.clearCheck()

                setMulChoQuestion(binding)

            } else if (questionIndex in mulChoQuestions.size until numQuestions) {
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
            val answerText = binding.answerText.text.toString()
            currentIdenQuestion.isCorrect = answerText
                .toLowerCase()
                .replace("\\s+".toRegex()," ")
                .trim() in currentIdenQuestion.answers

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
                    this?.putInt("unit2Quiz3Score", score)
                    this?.putString("unit2Quiz3NumQuestions", numQuestions.toString())
                    this?.apply()
                }
//
//                 Navigate to score screen
                view.findNavController().navigate(
                    Unit2Quiz3FragmentDirections
                        .actionUnit2Quiz3FragmentToQuizScoreFragment(
                            numQuestions,
                            score,
                            "Unit 2: Lesson 3 Quiz"
                        ))
            }
        }

        return binding.root
    }

    // Handles scoring and quiz logic
    private fun handleQuizProper(binding : FragmentUnit2Quiz3Binding) {

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

                if (currentMulChoQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT!", Toast.LENGTH_SHORT).show()
                }

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
                currentIdenQuestion.isCorrect = answerText
                    .toString()
                    .toLowerCase()
                    .replace("\\s+".toRegex()," ")
                    .trim() in currentIdenQuestion.answers

                if (currentIdenQuestion.isCorrect) {
                    Toast.makeText(context, "CORRECT! Score:", Toast.LENGTH_SHORT).show()
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
    private fun initQuestions(binding : FragmentUnit2Quiz3Binding) {
        questionIndex = 0

        mulChoQuestions.forEach {
            val correct = it.answers[0]

            it.answers.shuffle()

            it.correctIdx = it.answers.indexOf(correct)
        }

        enteredAns = ""

        currentIdenQuestion = idenQuestions[0]

        setMulChoQuestion(binding)
    }

    private fun setMulChoQuestion(binding : FragmentUnit2Quiz3Binding) {
        currentMulChoQuestion = mulChoQuestions[questionIndex]

        questionText = currentMulChoQuestion.text
        // randomize the answers into a copy of the array
        answers = currentMulChoQuestion.answers

        binding.quizImage.visibility = View.GONE


        when (currentMulChoQuestion.clickedIdx) {
            0 -> binding.firstChoiceRadioButton.isChecked = true
            1 -> binding.secondChoiceRadioButton.isChecked = true
            2 -> binding.thirdChoiceRadioButton.isChecked = true
            3 -> binding.fourthChoiceRadioButton.isChecked = true
        }

    }

    private fun setIdenQuestion(binding: FragmentUnit2Quiz3Binding) {
        val idenQuestionIndex = questionIndex - mulChoQuestions.size
        currentIdenQuestion = idenQuestions[idenQuestionIndex]

        val quizImage = binding.quizImage

        quizImage.visibility = View.VISIBLE

        when (idenQuestionIndex) {
            0 -> quizImage.setImageResource(R.drawable.ic_unit2_quiz3_q11)
            1 -> quizImage.setImageResource(R.drawable.ic_unit2_quiz3_q12)
            2 -> quizImage.setImageResource(R.drawable.ic_unit2_quiz3_q13)
            3 -> quizImage.setImageResource(R.drawable.ic_unit2_quiz3_q14)
            4 -> quizImage.setImageResource(R.drawable.ic_unit2_quiz3_q15)
        }

        questionText = currentIdenQuestion.text
        // randomize the answers into a copy of the array

        enteredAns = currentIdenQuestion.enteredAns
    }

}