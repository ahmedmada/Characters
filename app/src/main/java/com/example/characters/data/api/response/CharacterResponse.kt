package com.gnova.data.api.response

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "char_id")
    val char_id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "img")
    val img: String,
    @Json(name = "status")
    val status: String,
    )