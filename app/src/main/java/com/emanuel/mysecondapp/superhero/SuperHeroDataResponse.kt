package com.emanuel.mysecondapp.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItem>
) {
}

data class SuperHeroItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImage
)

data class SuperHeroImage(
    @SerializedName("url") val url: String
)