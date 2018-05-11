package com.example.ubuntu.miniprojetmobile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import ccom.example.ubuntu.miniprojetmobile.OrderCart
import com.example.ubuntu.miniprojetmobile.adapter.CustomCartAdapter
import java.util.*

/**
 * Created by sonicmac on 07/04/18.
 */

class ChartActivity : Activity(), AdapterView.OnItemClickListener, cardInterface {
    lateinit var spinner: Spinner
    lateinit var cartAdapter: CustomCartAdapter
    lateinit var orderCartArrayList: ArrayList<OrderCart>
    lateinit var listView: ListView
    var itemsarray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    lateinit var spinnerAdapter: ArrayAdapter<Int>
    lateinit var textViewshowTotalAmount: TextView
    var totalAmout: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        spinner = findViewById<View>(R.id.spinnerQuanity) as Spinner
        listView = findViewById<View>(R.id.listviewCartID) as ListView
        textViewshowTotalAmount = findViewById<View>(R.id.showTotalAmount) as TextView

        val showCartContent = findViewById<View>(R.id.showCart) as TextView

        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        val aController = applicationContext as OrderController

        // Get Cart Size
        val cartSize = aController.orderCart.cartSize
        orderCartArrayList = ArrayList()

        var showString = ""

        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsarray)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        /******** Show Cart Products on screen - Start  */
        if (cartSize > 0) {
            for (i in 0 until cartSize) {
                //Get product details
                /* String pName    = aController.getOrderCart().getOrders(i).getOrderName();
                int pPrice      = aController.getOrderCart().getOrders(i).getOrderPrice();
                String pDisc    = aController.getOrderCart().getOrders(i).getOrderDesc();
                showString += " Product Name : "+pName+" "+ "Price : "+pPrice+" "+ "Discription : "+pDisc+""+ " -----------------------------------\n\n";
               */
                orderCartArrayList.addAll(Arrays.asList(aController.orderCart))
            }
        } else {
            showString = " Shopping cart is empty. "
        }

        cartAdapter = CustomCartAdapter(this, orderCartArrayList, spinnerAdapter)
        //showCartContent.setText(showString);
        listView.adapter = cartAdapter
        //listView.setOnItemClickListener(this);

        val a = getTotalPrice(orderCartArrayList)
        textViewshowTotalAmount.text = "Total Payable Amount: " + a.toString()



        // Spinner Drop down elements

        // Creating adapter for spinner
        /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelCartListItems);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(this);*/

    }


    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Toast.makeText(this, "Position" + position, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        cartAdapter.notifyDataSetChanged()
    }

    //calculate total amount
    /*  public double getTotal(ArrayList<OrderCart> list){
        double total=0.0;
        for(int i=0;i<list.size();i++){
            total=total+Double.parseDouble(String.valueOf(list.get(i).getCartTotalAmount()));
        }
        return total;
    }*/

    override fun increaseItems(position: Int) {
        var quantity = orderCartArrayList[position].getOrders(position).itemQuantity
        quantity++
        //set quantity in model
        orderCartArrayList[position].getOrders(position).itemQuantity = quantity

        var subTotal: Int? = 0
        subTotal = orderCartArrayList[position].getOrders(position).orderPrice * quantity

        //orderCartArrayList.get(position).setCartTotalAmount(subTotal);

        val total = getTotalPrice(orderCartArrayList)
        textViewshowTotalAmount.text = "Total Payable Amount: " + total.toString()

        cartAdapter.notifyDataSetChanged()
        //holder.editTextQuantity.setText(""+quantity);
        //holder.infoTextField.setText("Total Price : "+ subTotal);
    }

    override fun decreaseItems(position: Int) {
        var subTotal: Int? = 0
        //counter --;
        var quantity = orderCartArrayList[position].getOrders(position).itemQuantity
        quantity--
        if (quantity <= 0) {
            orderCartArrayList[position].removeOrders(orderCartArrayList[position].getOrders(position))

            cartAdapter.remove(orderCartArrayList[position])
            cartAdapter.notifyDataSetChanged()


                    quantity=0

        }else {
            //set quantity in model
            orderCartArrayList[position].getOrders(position).itemQuantity = quantity

            val orderPrice = orderCartArrayList[position].getOrders(position).orderPrice
            subTotal = orderPrice * quantity
            //orderCartArrayList.get(position).setCartTotalAmount(subTotal);

            val total = getTotalPrice(orderCartArrayList)
            textViewshowTotalAmount.text = "Total Payable Amount: " + total.toString()

            cartAdapter.notifyDataSetChanged()
            //holder.editTextQuantity.setText(""+quantity);
            //holder.infoTextField.setText("Total Price : "+ subTotal);
        }
    }

    //calculate total
    private fun getTotalPrice(orderCartList: List<OrderCart>): Double {
        var total = 0.0
        var quantity: Int
        for (i in orderCartList.indices) {
            val cart = orderCartList[i]
            quantity = orderCartList[i].getOrders(i).itemQuantity
            total += (cart.getOrders(i).orderPrice * quantity).toDouble()
            if (BuildConfig.DEBUG) Log.d("TAG", "Position : " + i + "\tOriginal Price : " + cart.getOrders(i).orderPrice + "\t\tQuantity:\t" + quantity + "\t\tTotal Price " + total)
        }
        return total
    }
}
