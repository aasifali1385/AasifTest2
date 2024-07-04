package com.aasif.test.view

import android.content.Intent
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.aasif.test.R
import com.aasif.test.adapters.RecyclerAdapter
import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Restaurant
import com.aasif.test.databinding.ActivityHomeBinding
import com.aasif.test.databinding.ItemFoodscateBinding
import com.aasif.test.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    private lateinit var bind: ActivityHomeBinding

    private val viewModel: LoginViewModel by viewModels()


    private lateinit var restaurants: List<FoodsCate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.getCategories {
            Snackbar.make(bind.root, it, Snackbar.LENGTH_SHORT).show()
            bind.progressBar.isVisible = false
        }

        viewModel.restaurants.observe(this) {
            bind.progressBar.isVisible = false
            restaurants = it
            loadRestaurants(restaurants)
        }


        bind.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadRestaurants(restaurants, newText.toString())
                return false
            }
        })


    }

    private fun loadRestaurants(foodsCate: List<FoodsCate>, query: String = "") {

        bind.linear.removeAllViews()

        foodsCate.forEach {
            val view = ItemFoodscateBinding.inflate(layoutInflater, bind.root, false)
            view.cateName.text = it.name

            val adapter = RecyclerAdapter { restaurant ->
                val intent = Intent(this, RestaurantActivity::class.java)
                intent.putExtra("restaurant", restaurant)
                startActivity(intent)
            }

            view.recycler.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false
            )

            view.recycler.adapter = adapter

            adapter.loadRestaurants(it.restaurant, query)

            bind.linear.addView(view.root)
        }


    }


}