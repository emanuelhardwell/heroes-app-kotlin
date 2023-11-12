package com.emanuel.mysecondapp.superhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.emanuel.mysecondapp.R
import com.emanuel.mysecondapp.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    //NOTA: se debe habilitar el viewBinding en el build.gradle.kts
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var superHeroAdapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ANTES ASÍ ERA
        //setContentView(R.layout.activity_super_hero_list)

        binding= ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit= getRetrofit()
        initUI()

    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        superHeroAdapter = SuperHeroAdapter(){selectedSuperHero -> navigateToDetailSuperHero(selectedSuperHero)}
        binding.recyclerViewHero.setHasFixedSize(true)
        binding.recyclerViewHero.layoutManager= LinearLayoutManager(this)
        binding.recyclerViewHero.adapter= superHeroAdapter

    }

    private fun searchByName(query: String) {
        binding.progressBarHero.isVisible= true;
        CoroutineScope(Dispatchers.IO).launch {
            val req: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (req.isSuccessful){
                Log.i("req", "Successfully")

                val res: SuperHeroDataResponse? = req.body()
                if (res != null){
                    Log.i("req", res.toString())
                    runOnUiThread {
                        superHeroAdapter.updateSuperHeroItemList(res.results)
                        binding.progressBarHero.isVisible= false;
                    }
                }
            }else{
                Log.i("req", "Unsuccessfully")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        val retrofit= Retrofit.Builder().baseUrl("https://superheroapi.com/api/").addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }

    private fun navigateToDetailSuperHero(id: String){
        val intent= Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(DetailSuperHeroActivity.ID_SUPERHERO, id)
        startActivity(intent)
    }

}