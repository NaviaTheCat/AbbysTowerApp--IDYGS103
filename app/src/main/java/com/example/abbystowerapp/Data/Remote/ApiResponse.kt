package com.example.abbystowerapp.Data.Remote

data class ApiResponse<T>(
    val succeded: Boolean,
    val message: String?,
    val result: T?
)