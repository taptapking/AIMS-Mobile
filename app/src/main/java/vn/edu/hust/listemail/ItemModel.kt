package vn.edu.hust.listemail

import java.io.Serializable

data class ItemModel(val brand: String, val product_name: String, val price: Int, val avatar: Int = 0) : Serializable {
}