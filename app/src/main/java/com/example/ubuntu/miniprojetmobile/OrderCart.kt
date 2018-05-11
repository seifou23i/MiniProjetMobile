package ccom.example.ubuntu.miniprojetmobile

import com.example.ubuntu.miniprojetmobile.OrderModel
import java.util.*

/**
 * Created by sonicmac on 07/04/18.
 */

class OrderCart {

    var  cartTotalAmount: Int = 0
    private val orderModelArrayList = ArrayList<OrderModel>()

    val cartSize: Int
        get() = cartTotalAmount

    fun getOrders(position: Int): OrderModel {
        return orderModelArrayList[position]
    }

    fun setOrders(orders: OrderModel) {
        orderModelArrayList.add(orders)
        cartTotalAmount++
    }
    fun removeOrders(orders: OrderModel) {
        orderModelArrayList.remove(orders)
        cartTotalAmount--

    }


    fun checkOrderInCart(orderModel: OrderModel): Boolean {
        return orderModelArrayList.contains(orderModel)
    }


}
