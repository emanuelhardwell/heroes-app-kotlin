package com.emanuel.mysecondapp.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(@SerializedName("response") val response: String) {
}