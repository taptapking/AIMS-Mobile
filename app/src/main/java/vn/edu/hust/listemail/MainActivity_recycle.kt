package vn.edu.hust.listemail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity_recycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.inbox)

        var toast1: Toast
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

        val items = arrayListOf<ItemModel>()
        repeat(28){
            val index = it+1
            val displayname : Int = resources.getIdentifier("name_$index", "string", packageName)
            Log.v("name",getString(displayname))
            items.add(ItemModel(getString(displayname),"Description $index",index*1000, resources.getIdentifier("thumb$index", "drawable", packageName) ))
        }
        val adapter = ItemAdapterRecycle(items, object: ItemAdapterRecycle.ItemClickListener{
            override fun ItemClick(position: Int){
                Log.v("clicked an item", "$position")
                val intent = Intent(this@MainActivity_recycle, SecondActivity::class.java)
                intent.putExtra("index",(position+1).toString())
                intent.putExtra("item",items[position])
                startActivity(intent)
            }
        }, object: ItemAdapterRecycle.addtocartClickListener{
            override fun addtocartClick(position: Int) {
                Log.v("pushed cart button","$position")
                if (    CartModel.item.contains(items[position])   ){
                    toast1 = makeText( applicationContext, getString(R.string.cannot_add), Toast.LENGTH_SHORT)
                    toast1.show()
                    Log.v("cannot add to cart","$position")
                } else
                {
                    CartModel.item.add(items[position])
                    CartModel.amount.add(1)
                   toast1 = makeText( applicationContext, getString(R.string.added), Toast.LENGTH_SHORT)
                   toast1.show()
                    Log.v("added to cart","$position")
                }

            }
        }
            )

        val l = findViewById(R.id.recycler_view) as RecyclerView
        l.layoutManager = LinearLayoutManager(this)
        l.adapter =  adapter


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.actionbarhomepage, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu1){
            val intent = Intent(this@MainActivity_recycle, CartActivity::class.java)
            startActivity(intent)
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