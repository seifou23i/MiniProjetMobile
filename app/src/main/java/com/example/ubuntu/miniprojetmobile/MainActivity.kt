package com.example.ubuntu.miniprojetmobile

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.example.ubuntu.miniprojetmobile.entity.CartItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
       setSupportActionBar(toolbar)






        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val cityAdapter = CartItemAdapter(this, loadData())
        val myModel = ViewModelProviders.of(this).get(MyModel::class.java)
        listcities.adapter = cityAdapter



    }

    fun loadData(): List<CartItem> {

        val imagesTab = arrayOf(R.drawable.restaurant1, R.drawable.restaurant11, R.drawable.restaurant2, R.drawable.restaurant12, R.drawable.restaurant3, R.drawable.restaurant8, R.drawable.restaurant7, R.drawable.restaurant4)
        val namesTab = resources.getStringArray(R.array.restaurants)
        val touristsTab = resources.getStringArray(R.array.address)
        val list = mutableListOf<CartItem>()
        for (i in 0..imagesTab.size - 1) {
            list.add(CartItem(listImage = imagesTab[i], name = namesTab[i], restaurantAdresse = touristsTab[i]))

        }

        return list
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



}




