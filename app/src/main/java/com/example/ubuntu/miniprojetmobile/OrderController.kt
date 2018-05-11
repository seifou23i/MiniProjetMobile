package com.example.ubuntu.miniprojetmobile

import android.app.Application
import ccom.example.ubuntu.miniprojetmobile.OrderCart
import java.util.*

/**
 * Created by sonicmac on 07/04/18.
 */

class OrderController : Application() {
    private val orderModels = ArrayList<OrderModel>()
    val orderCart = OrderCart()

    val orderArrayListSize: Int
        get() = orderModels.size

    fun getProducts(position: Int): OrderModel {
        return orderModels[position]
    }

    fun setOrderModels(orderModel: OrderModel) {
        orderModels.add(orderModel)
    }

}
