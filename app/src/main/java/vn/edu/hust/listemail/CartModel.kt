package vn.edu.hust.listemail

import java.io.Serializable

data class CartModel(var amount_init: Int = 0): Serializable {
    companion object {
        var item = arrayListOf<ItemModel>()
        var amount = arrayListOf<Int>()
    }
}