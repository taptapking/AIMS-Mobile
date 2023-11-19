package vn.edu.hust.listemail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.inbox)

        var toast1: Toast
        val item: ItemModel
        val branddetail = findViewById(R.id.brandDetail) as TextView
        val thumbnaildetail = findViewById(R.id.thumbnailDetail) as ImageView
        val pricedetail = findViewById(R.id.priceDetail) as TextView
        val productnamedetail = findViewById(R.id.productDetail) as TextView
        val imagedetail = findViewById(R.id.imagedetail) as ImageView
        val addtocart = findViewById(R.id.addtocartDetail) as Button
    try {

        item = intent.getSerializableExtra("item") as ItemModel
        val position = intent.getStringExtra("index")?.toInt()
        Log.v("image load", "$position")

        branddetail.text = item.brand
        pricedetail.text = item.price.toString()
        productnamedetail.text = item.product_name

        thumbnaildetail.setImageResource(resources.getIdentifier("thumb$position", "drawable", packageName))
        imagedetail.setImageResource(resources.getIdentifier("wall$position", "drawable", packageName))

        addtocart.setOnClickListener {
            if (CartModel.item.contains(item)) {
                toast1 = Toast.makeText(
                    applicationContext,
                    getString(R.string.cannot_add),
                    Toast.LENGTH_SHORT
                )
                toast1.show()
                Log.v("cannot add to cart", "$position")
            } else {
                CartModel.item.add(item)
                CartModel.amount.add(1)
                toast1 = Toast.makeText(
                    applicationContext,
                    getString(R.string.added),
                    Toast.LENGTH_SHORT
                )
                toast1.show()
                Log.v("added to cart", "$position")
            }
        }
    } catch (ex: Exception) {
        branddetail.text = "An error occured"
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.actionbarhomepage, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu1){
            val intent = Intent(this@SecondActivity, CartActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}