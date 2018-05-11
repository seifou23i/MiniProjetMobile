package com.example.ubuntu.miniprojetmobile.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.ubuntu.miniprojetmobile.OrderModel
import com.example.ubuntu.miniprojetmobile.R
import com.example.ubuntu.miniprojetmobile.onClickListner
import java.util.*

/**
 * Created by sonicmac on 21/04/18.
 */

class CustomListAdapter(context: Context, //to store the animal images
        //to store the list of countries
                        internal var orderModelArrayList: ArrayList<OrderModel>) : ArrayAdapter<OrderModel>(context, R.layout.listview_row, orderModelArrayList) {
    //to reference the Activity
    private val context: Activity
    internal var onClickListner: onClickListner

    init {
        this.context = context as Activity
        this.onClickListner = context as onClickListner
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_row, null, true)
        //this code gets references to objects in the listview_row.xml file

        val nameTextField = rowView.findViewById<View>(R.id.nameTextViewID) as TextView
        val infoTextField = rowView.findViewById<View>(R.id.infoTextViewID) as TextView
        val imageView = rowView.findViewById<View>(R.id.imageView1ID) as ImageView
        val buttonAddToCart = rowView.findViewById<View>(R.id.buttonAddToCart) as Button

        //this code sets the values of the objects to values from the arrays
        nameTextField.text = orderModelArrayList[position].orderName

        infoTextField.text = "" + orderModelArrayList[position].orderPrice

        buttonAddToCart.setOnClickListener {
            onClickListner.onButtonClick(position)
            buttonAddToCart.text = "Already Added"
        }

        return rowView

    }
}
