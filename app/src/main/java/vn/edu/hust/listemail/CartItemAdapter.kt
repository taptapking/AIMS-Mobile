package vn.edu.hust.listemail
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CartItemAdapter(var listener: removeClickListener? = null, var listener1: minusClickListener?, var listener2: plusClickListener? = null) : RecyclerView.Adapter<CartItemAdapter.ItemViewHolderRecycle>() {
    override fun getItemCount(): Int {
        return CartModel.item.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderRecycle {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemcart, parent, false)
        return ItemViewHolderRecycle(itemView, listener, listener1, listener2)
    }

    override fun onBindViewHolder(holder: ItemViewHolderRecycle, position: Int) {
        holder.thumbnail?.setImageResource(CartModel.item[position].avatar)
        holder.brand?.text = CartModel.item[position].brand
        holder.product_name?.text = CartModel.item[position].product_name
        holder.price?.text = CartModel.item[position].price.toString()
        holder.amount?.text = CartModel.amount[position].toString()

    }
    class ItemViewHolderRecycle(itemView: View, val listener: removeClickListener?, val listener1: minusClickListener?, val listener2: plusClickListener?): RecyclerView.ViewHolder(itemView){
        var brand: TextView? = null
        var product_name: TextView? = null
        var price: TextView? = null
        var thumbnail: ImageView? = null
        var amount: TextView? = null
        init {
            brand = itemView.findViewById(R.id.brandcart)
            product_name = itemView.findViewById(R.id.productnamecart)
            price = itemView.findViewById(R.id.pricecart)
            thumbnail = itemView.findViewById(R.id.thumbnailcart)
            amount = itemView.findViewById(R.id.amountcart)

            itemView.findViewById<Button>(R.id.remove).setOnClickListener{
                listener?.removeClick(adapterPosition)
            }
            itemView.findViewById<Button>(R.id.plusamount).setOnClickListener {
                listener1?.minusClick(adapterPosition)
            }
            itemView.findViewById<Button>(R.id.minusamount).setOnClickListener {
                listener2?.plusClick(adapterPosition)
            }
            Log.v("load in cart", "findViewById")
        }

    }
    interface removeClickListener{
        fun removeClick(position: Int)
    }
    interface minusClickListener{
        fun minusClick(position: Int)
    }
    interface plusClickListener{
        fun plusClick(position: Int)
    }
}