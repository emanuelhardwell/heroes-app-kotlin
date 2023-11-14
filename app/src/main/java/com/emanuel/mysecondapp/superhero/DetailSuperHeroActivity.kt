package com.emanuel.mysecondapp.superhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.emanuel.mysecondapp.R
import com.emanuel.mysecondapp.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailSuperHeroBinding

    companion object {
        const val ID_SUPERHERO = "id_superhero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_super_hero)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(ID_SUPERHERO).orEmpty()
        Log.i("id", id)

        searchById(id)
    }

    private fun searchById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val req = getRetrofit().create(ApiService::class.java).getSuperHero(id)

            if (req.isSuccessful) {
                val res = req.body()

                if (res != null) {
                    runOnUiThread {
                        createUI(res)
                    }
                }
            } else {
                Log.i("searchById", "ERROR ERROR")
            }
        }
    }

    private fun createUI(body: SuperHeroDetailResponse) {
        Log.i("body", body.toString())

        binding.textViewSuperHeroNameDetail.text= body.name
        Picasso.get().load(body.image.url).into(binding.imageViewSuperHeroDetail)

        prepareStats(body.powerstats)

        binding.textViewSuperHeroFullName.text= body.biography.fullName
        binding.textViewSuperHeroPublisher.text= body.biography.publisher

    }

    private fun prepareStats(powerstats: SuperHeroPowerstats) {
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height= pxToDp(stat.toFloat())
        view.layoutParams= params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        val retrofit =
            Retrofit.Builder().baseUrl("https://superheroapi.com/api/").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        return retrofit
    }


}