package com.example.ubuntu.miniprojetmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ubuntu.miniprojetmobile.entity.CartItem


/**
 * Created by ubuntu on 4/16/18.
 */
class CartItemAdapter(_ctx: Context, _listCartItems:List<CartItem>): BaseAdapter() {
    var ctx = _ctx
    val listRestaurant = _listCartItems


    override fun getItem(p0: Int) = listRestaurant.get(p0)

    override fun getItemId(p0: Int) = listRestaurant.get(p0).hashCode().toLong()

    override fun getCount()= listRestaurant.size

    override fun getView(position: Int, p0: View?, parent: ViewGroup?): View {
        var view = p0
        var viewHolder: ViewHolder
        if(view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.restaurant_layout,parent,false)
            val imageList = view?.findViewById<ImageView>(R.id.listimage) as ImageView
            val name = view?.findViewById<TextView>(R.id.name) as TextView
            val numberTourist = view?.findViewById<TextView>(R.id.numbertourist) as TextView
            viewHolder= ViewHolder(imageList,name,numberTourist)
            view.setTag(viewHolder)
        }
        else {
            viewHolder = view.getTag() as ViewHolder

        }


        viewHolder.imageList.setBackgroundResource(listRestaurant.get(position).listImage)
        viewHolder.name.setText(listRestaurant.get(position).name)
        viewHolder.numberTourist.setText(listRestaurant.get(position).restaurantAdresse)
        return view

    }

    private data class ViewHolder(var imageList: ImageView, var name: TextView, var numberTourist: TextView)


}