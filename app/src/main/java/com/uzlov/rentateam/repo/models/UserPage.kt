package com.uzlov.rentateam.repo.models


data class UserPage(
    val data: List<Data>?,
    val page: Int?,
    val per_page: Int?,
    val support: Support?,
    val total: Int?,
    val total_pages: Int?
)