package com.example.ubuntu.miniprojetmobile

import android.arch.lifecycle.ViewModel
import com.example.ubuntu.miniprojetmobile.entity.CartItem

/**
 * Created by ubuntu on 4/16/18.
 */
class MyModel: ViewModel() {
    var cartItem: CartItem = CartItem()
}