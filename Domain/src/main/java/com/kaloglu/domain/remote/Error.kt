package com.kaloglu.domain.remote

data class Error(
        val domain: String,
        val code: Int,
        val message: String = "null",
        val location: String,
        val locationType: String
)