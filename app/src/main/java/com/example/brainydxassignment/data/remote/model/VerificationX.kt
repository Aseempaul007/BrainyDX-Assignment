package com.example.brainydxassignment.data.remote.model

data class VerificationX(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)