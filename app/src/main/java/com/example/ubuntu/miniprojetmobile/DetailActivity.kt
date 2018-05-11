package com.example.ubuntu.miniprojetmobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ubuntu.miniprojetmobile.entity.Restaurant
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val i = intent.getIntExtra("pos",0)
        val detailImages= arrayOf(R.drawable.restaurant1,R.drawable.restaurant11,R.drawable.restaurant2,R.drawable.restaurant12,R.drawable.restaurant3,R.drawable.restaurant8,R.drawable.restaurant7,R.drawable.restaurant4)
        val namesTab = resources.getStringArray(R.array.restaurants)
        val addressTab = resources.getStringArray(R.array.address)
        val dishesTab = resources.getStringArray(R.array.dishes)
        val descTab = resources.getStringArray(R.array.description)
        val restaurant = Restaurant(detailImage = detailImages[i],name = namesTab[i], restaurantAdresse = addressTab[i],plats = dishesTab,description = descTab[i])
        displayData(restaurant)

    }


    fun displayData(restaurant: Restaurant){
        imageView3.setImageResource(restaurant.detailImage)
        detailProductName4.text = restaurant.name
        // adress2.text = restaurant.restaurantAdresse
        textView2.text = restaurant.description
        //plats.text = getString(R.string.places)+ restaurant.plats.joinToString(separator = ", ")

    }

}
