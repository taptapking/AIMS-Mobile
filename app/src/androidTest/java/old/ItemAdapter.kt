package old
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import vn.edu.hust.listemail.ItemModel
import vn.edu.hust.listemail.R

class ItemAdapter(val items: ArrayList<ItemModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View
        var viewHolder: ItemViewHolder
        if (convertView == null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.itememail, parent, false)
            viewHolder = ItemViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ItemViewHolder
        }
        viewHolder.sender?.text  = items[position].name
        viewHolder.title?.text = items[position].title
        viewHolder.time?.text = items[position].time
        viewHolder.content?.text = items[position].content

        viewHolder.thumbnail?.setImageResource(items[position].avatar)


        return itemView
    }
    class ItemViewHolder(itemView: View){
        var sender: TextView? = null
        var title: TextView? = null
        var content: TextView? = null
        var time: TextView? = null
        var thumbnail: ImageView? = null

        init {
            sender = itemView.findViewById(R.id.sender)
            title = itemView.findViewById(R.id.title)
            content = itemView.findViewById(R.id.productname)
            time = itemView.findViewById(R.id.price)
            thumbnail = itemView.findViewById(R.id.thumbnail)


            Log.v("tag", "findViewById")
        }
    }
}