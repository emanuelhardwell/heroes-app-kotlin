package com.emanuel.mysecondapp.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("10229233666327556/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("10229233666327556/{id}")
    suspend fun getSuperHero(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>
}