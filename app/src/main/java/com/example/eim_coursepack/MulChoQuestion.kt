package com.example.eim_coursepack

data class MulChoQuestion(
    val text: String,
    val answers: MutableList<String>,
    var isCorrect: Boolean,
    var clickedIdx : Int,
    var correctIdx : Int)

data class IdenQuestion (
    val text: String,
    val answers: MutableList<String>,
    var isCorrect: Boolean,
    var enteredAns : String)