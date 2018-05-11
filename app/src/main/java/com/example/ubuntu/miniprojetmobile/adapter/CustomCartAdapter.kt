package com.example.ubuntu.miniprojetmobile.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ccom.example.ubuntu.miniprojetmobile.OrderCart
import com.example.ubuntu.miniprojetmobile.R
import com.example.ubuntu.miniprojetmobile.cardInterface
import java.util.*



class CustomCartAdapter(context: Context, internal var orderCartArrayList: ArrayList<OrderCart>, arrayAdapter: ArrayAdapter<*>) : ArrayAdapter<OrderCart>(context, R.layout.listview_row, orderCartArrayList) {
    private val context: Activity
    internal var cardInterface: cardInterface
    internal var Spinneradapter: ArrayAdapter<Int>

    init {
        this.context = context as Activity
        this.Spinneradapter = arrayAdapter as ArrayAdapter<Int>
        this.cardInterface = context as cardInterface
    }

    inner class Holder {
        internal var nameTextField: TextView? = null
        internal var infoTextField: TextView? = null
        internal var imageViewPlus: ImageView? = null
        internal var imageViewMinus: ImageView? = null
        internal var textViewQuantity: TextView? = null
        internal var spinnerQuanity: Spinner? = null
        internal var editTextQuantity: EditText? = null
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val holder = Holder()
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.cart_list_item, null, true)

        holder.nameTextField = rowView.findViewById<View>(R.id.nameTextViewCartID) as TextView
        holder.infoTextField = rowView.findViewById<View>(R.id.infoTextViewCartID) as TextView
        holder.imageViewPlus = rowView.findViewById<View>(R.id.plus) as ImageView
        holder.imageViewMinus = rowView.findViewById<View>(R.id.minus) as ImageView
        holder.textViewQuantity = rowView.findViewById<View>(R.id.textviewQuantity) as TextView
        holder.spinnerQuanity = rowView.findViewById<View>(R.id.spinnerQuanity) as Spinner
        holder.editTextQuantity = rowView.findViewById<View>(R.id.editTextQuantity) as EditText

        val name = orderCartArrayList[position].getOrders(position).orderName
        val quantity = orderCartArrayList[position].getOrders(position).itemQuantity
        holder.editTextQuantity!!.setText("" + quantity)

        val price = orderCartArrayList[position].getOrders(position).orderPrice

        //this code sets the values of the objects to values from the arrays
        holder.nameTextField!!.text = orderCartArrayList[position].getOrders(position).orderName

        holder.infoTextField!!.text = "Item Price : " + price

        holder.spinnerQuanity!!.adapter = Spinneradapter
        holder.spinnerQuanity!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val orderPrice = orderCartArrayList[position].getOrders(position).orderPrice
                //holder.infoTextField.setText();
                val value = parent.selectedItemPosition
                var subTotal = 0.0

                for (p in orderCartArrayList) {
                    subTotal += (p.getOrders(position).orderPrice * value).toDouble()
                }

                Toast.makeText(context, "Position " + subTotal, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        //plus minus
        holder.imageViewPlus!!.setOnClickListener {

            cardInterface.increaseItems(position)

        }

        holder.imageViewMinus!!.setOnClickListener {


            cardInterface.decreaseItems(position)

        }

        return rowView
    }

    companion object {
        internal var counter = 0
    }
}
