package vn.edu.hust.listemail
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ItemAdapterRecycle(val items: ArrayList<ItemModel>, val listener: ItemClickListener? = null, val listener2: addtocartClickListener? = null) : RecyclerView.Adapter<ItemAdapterRecycle.ItemViewHolderRecycle>() {
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderRecycle {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itememail, parent, false)
        return ItemViewHolderRecycle(itemView, listener, listener2)
    }

    override fun onBindViewHolder(holder: ItemViewHolderRecycle, position: Int) {
        holder.thumbnail?.setImageResource(items[position].avatar)
        holder.brand?.text = items[position].brand
        holder.product_name?.text = items[position].product_name
        holder.price?.text = items[position].price.toString()
    }
    class ItemViewHolderRecycle(itemView: View, val listener: ItemClickListener?, val listener2: addtocartClickListener?): RecyclerView.ViewHolder(itemView){
        var brand: TextView? = null
        var product_name: TextView? = null
        var price: TextView? = null
        var thumbnail: ImageView? = null
        init {
            brand = itemView.findViewById(R.id.brand)
            product_name = itemView.findViewById(R.id.productname)
            price = itemView.findViewById(R.id.price)
            thumbnail = itemView.findViewById(R.id.thumbnail)

            itemView.setOnClickListener{
                listener?.ItemClick(adapterPosition)
            }
            itemView.findViewById<Button>(R.id.addtocart).setOnClickListener {
                listener2?.addtocartClick(adapterPosition)
            }
            Log.v("load", "findViewById")
        }

    }
    interface ItemClickListener{
        fun ItemClick(position: Int)
    }
    interface addtocartClickListener{
        fun addtocartClick(position: Int)
    }
}