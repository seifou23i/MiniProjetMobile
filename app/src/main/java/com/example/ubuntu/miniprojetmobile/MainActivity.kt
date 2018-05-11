package com.example.ubuntu.miniprojetmobile

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.example.ubuntu.miniprojetmobile.entity.Restaurant
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.intentFor
import java.io.IOException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {


    private var map: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
       setSupportActionBar(toolbar)





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        var fragment = supportFragmentManager.findFragmentById(R.id.map)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragment)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val cityAdapter = RestaurantAdapter(this, loadData())
        val myModel = ViewModelProviders.of(this).get(MyModel::class.java)
        listcities.adapter = cityAdapter



        if (isTwoPane()) {
            displayData2(myModel.restaurant)
        }
        listcities.setOnItemClickListener { adapterView, view, i, l ->
            if (isTwoPane()) {
                val detailImages = arrayOf(R.drawable.restaurant1, R.drawable.restaurant11, R.drawable.restaurant2, R.drawable.restaurant12, R.drawable.restaurant3, R.drawable.restaurant8, R.drawable.restaurant7, R.drawable.restaurant4)
                val namesTab = resources.getStringArray(R.array.restaurants)
                val touristsTab = resources.getStringArray(R.array.address)
                val placesTab = resources.getStringArray(R.array.dishes)
                val descTab = resources.getStringArray(R.array.description)
                myModel.restaurant = Restaurant(detailImage = detailImages[i], name = namesTab[i], restaurantAdresse = touristsTab[i], plats = placesTab, description = descTab[i])
                displayData2(myModel.restaurant)


            } else {
                startActivity(intentFor<DetailActivity>("pos" to i))

            }

        }
    }

    fun loadData(): List<Restaurant> {

        val imagesTab = arrayOf(R.drawable.restaurant1, R.drawable.restaurant11, R.drawable.restaurant2, R.drawable.restaurant12, R.drawable.restaurant3, R.drawable.restaurant8, R.drawable.restaurant7, R.drawable.restaurant4)
        val namesTab = resources.getStringArray(R.array.restaurants)
        val touristsTab = resources.getStringArray(R.array.address)
        val list = mutableListOf<Restaurant>()
        for (i in 0..imagesTab.size - 1) {
            list.add(Restaurant(listImage = imagesTab[i], name = namesTab[i], restaurantAdresse = touristsTab[i]))

        }

        return list
    }

    fun isTwoPane() = findViewById<View>(R.id.fragment4) != null
    fun displayData2(restaurant: Restaurant) {
        imageView3.setImageResource(restaurant.detailImage)
        detailProductName4.text = restaurant.name
        // adress2.text = restaurant.restaurantAdresse
        textView2.text = restaurant.description
        //plats.text = getString(R.string.places)+ restaurant.plats.joinToString(separator = ", ")

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        val inflater = menuInflater
//        inflater.inflate(R.menu.options_menu, menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("ByteOrderMark", "MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap
        var latitude: Double
        var longitude: Double
        val touristsTab = resources.getStringArray(R.array.address)
        val namesTab = resources.getStringArray(R.array.restaurants)
        lateinit var fusedLocationClient: FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        lateinit var lastLocation: Location


        var i: Int = 0
        while (i < touristsTab.size) {
            var geocodeMatches: List<Address>? = null

            try {

                geocodeMatches = Geocoder(this).getFromLocationName(
                        touristsTab[i], 1)

            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (geocodeMatches != null) {
                    latitude = geocodeMatches[0].latitude
                    longitude = geocodeMatches[0].longitude
                    var restaurant = LatLng(latitude, longitude)
                    map!!.addMarker(MarkerOptions().position(restaurant).title(namesTab[i]))
            }

            i++

        }
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 9f))
            }

        }


    }


}




