package vn.edu.hust.listemail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.cart)
        val totalprice: TextView = findViewById(R.id.totalprice) as TextView

        //var cart = CartModel(1)
        var toast1: Toast

        var adapter = CartItemAdapter(object : CartItemAdapter.removeClickListener {
            override fun removeClick(position: Int) {
                TODO("Not yet implemented")
            }

        }
        , object : CartItemAdapter.minusClickListener {
            override fun minusClick(position: Int) {
                TODO("Not yet implemented")
            }

        }, object : CartItemAdapter.plusClickListener {
            override fun plusClick(position: Int) {
                TODO("Not yet implemented")
            }
        }
        )

        adapter.listener = object : CartItemAdapter.removeClickListener{
            override fun removeClick(position: Int){
                CartModel.item.removeAt(position)
                CartModel.amount.removeAt(position)
                adapter.notifyDataSetChanged()
                totalprice.text = totalprice().toString()
            }
        }
        adapter.listener1 = object: CartItemAdapter.minusClickListener{
            override fun minusClick(position: Int) {
                if (CartModel.amount[position] <= 1) {
                    CartModel.item.removeAt(position)
                    CartModel.amount.removeAt(position)
                    adapter.notifyDataSetChanged()
                } else
                {
                    CartModel.amount[position] = CartModel.amount[position] - 1
                    adapter.notifyItemChanged(position)
                }
                totalprice.text = totalprice().toString()

            }
        }
        adapter.listener2 = object: CartItemAdapter.plusClickListener{
            override fun plusClick(position: Int) {
                CartModel.amount[position] = CartModel.amount[position]+1
                adapter.notifyItemChanged(position)
                totalprice.text = totalprice().toString()

            }
        }


        val l = findViewById(R.id.cart_view) as RecyclerView
        l.layoutManager = LinearLayoutManager(this)
        l.adapter =  adapter

        totalprice.text = totalprice().toString()

    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun totalprice(): Int{
        var sum: Int = 0
        for (i in CartModel.item.indices )
        {
            sum += CartModel.item[i].price * CartModel.amount[i]
        }
        return sum
    }


}