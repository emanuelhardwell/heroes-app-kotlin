package com.emanuel.mysecondapp.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("response") var response: String,
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("powerstats") var powerstats: SuperHeroPowerstats,
    @SerializedName("image") val image: SuperHeroDetailImage,
    @SerializedName("biography") val biography: Biography
)

data class SuperHeroDetailImage(
    @SerializedName("url") val url: String
)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)

data class SuperHeroPowerstats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
)