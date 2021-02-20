package com.example.eim_coursepack

data class Question(
    val text: String,
    val answers: List<String>,
    var isCorrect: Boolean)