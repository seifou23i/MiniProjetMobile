package com.example.ubuntu.miniprojetmobile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.ubuntu.miniprojetmobile.adapter.CustomListAdapter
import java.util.*



class MainActivity2 : AppCompatActivity(), AdapterView.OnItemClickListener, onClickListner {

    internal var nameArray = arrayOf("ABC", "XYZ", "PQR Sandhya", "Rabbit", "Snake", "Spider")

    internal var infoArray = arrayOf("8 tentacled monster", "Delicious in rolls", "Great for jumpers", "Nice in a stew", "Great for shoes", "Scary.")


    lateinit var listView: ListView
    lateinit var mTopToolbar: Toolbar
    lateinit var aController: OrderController
    lateinit var orderModel: OrderModel
    lateinit var orderModelArrayList: ArrayList<OrderModel>
    internal var textViewBadge: TextView? = null
    internal var mCartItemCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        mTopToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(mTopToolbar)


        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        aController = applicationContext as OrderController
        orderModelArrayList = ArrayList()

        for (i in nameArray.indices) {
            val price = 10 + i
            // Create product model class object
            orderModel = OrderModel(nameArray[i], infoArray[i], price)
            //store product object to arraylist in controller
            aController.setOrderModels(orderModel)
            orderModelArrayList.addAll(Arrays.asList(orderModel))
        }

        val adapter = CustomListAdapter(this, orderModelArrayList)

        listView = findViewById<View>(R.id.listviewID) as ListView
        listView.adapter = adapter
        listView.onItemClickListener = this

        //Product arraylist size
        val ProductsSize = aController.orderArrayListSize
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

        val tempOrderObject = aController.getProducts(position)
        if (!aController.orderCart.checkOrderInCart(tempOrderObject)) {
            // Product not Exist in cart so add product to
            // Cart product arraylist
            aController.orderCart.setOrders(tempOrderObject)
            Toast.makeText(applicationContext, "Now Cart size: " + aController.orderCart.cartSize, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "Product " + (position + 1) + " already added in cart.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_item, menu)

        val menuItem = menu.findItem(R.id.badge)

        val actionView = menuItem.actionView
        textViewBadge = actionView.findViewById<View>(R.id.actionbar_notifcation_textview) as TextView
       // mCartItemCount = aController.orderCart.cartSize
        setupBadge()

        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.badge) {
            val i = Intent(baseContext, ChartActivity::class.java)
            startActivity(i)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Hide unhide and increment the counter
     */
        fun setupBadge() {
        if (textViewBadge != null) {
            if (aController.orderCart.cartSize == 0) {
                if (textViewBadge!!.visibility != View.GONE) {
                    textViewBadge!!.visibility = View.GONE
                }
            } else {
                textViewBadge!!.text = Math.min(aController.orderCart.cartSize, 99).toString()
                if (textViewBadge!!.visibility != View.VISIBLE) {
                    textViewBadge!!.visibility = View.VISIBLE
                }
            }
        }
    }

//    private fun buildCounterDrawable(count: Int, backgroundImageId: Int): Drawable {
//        val inflater = LayoutInflater.from(this)
//        val view = inflater.inflate(R.layout.badge_menu, null)
//        //view.setBackgroundResource(backgroundImageId);
//
//        val textView = view.findViewById<View>(R.id.actionbar_notifcation_textview) as TextView
//        textView.text = "" + count
//        //Toast.makeText(this, "count" +count, Toast.LENGTH_SHORT).show();
//
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.AT_MOST),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.AT_MOST))
//        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
//
//        view.isDrawingCacheEnabled = true
//        view.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
//        val bitmap = Bitmap.createBitmap(view.drawingCache)
//        view.isDrawingCacheEnabled = false
//
//        return BitmapDrawable(resources, bitmap)
//    }


    override fun onButtonClick(position: Int) {
        val tempOrderObject = aController.getProducts(position)

        //aController = applicationContext as OrderController
        if (!aController.orderCart.checkOrderInCart(tempOrderObject)) {
            // Product not Exist in cart so add product to
            // Cart product arraylist
            invalidateOptionsMenu()
            aController.orderCart.setOrders(tempOrderObject)
            //Toast.makeText(getApplicationContext(), "Now Cart size: "+aController.getOrderCart().getCartSize(), Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(applicationContext, "Product " + (position + 1) + " already added in cart.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        setupBadge()

    }
    companion object {
        internal var notifCount: Button? = null
        internal var mNotifCount = 0
    }

}
