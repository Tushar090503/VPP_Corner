package com.vpppcoe.vppcorner.Adapter

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vpppcoe.vppcorner.Model.Food
import com.vpppcoe.vppcorner.Model.Orders
import com.vpppcoe.vppcorner.R

class ViewOrdersAdapter(private var orders: ArrayList<Orders>) :
    RecyclerView.Adapter<ViewOrdersAdapter.MyViewHolder2>() {

    private lateinit var dbRef: FirebaseDatabase
    private lateinit var dbRef2: FirebaseDatabase

    class MyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var orderNumber: TextView = itemView.findViewById(R.id.tv_order_number)
        var price: TextView = itemView.findViewById(R.id.tv_amount_paid)
        var time: TextView = itemView.findViewById(R.id.tv_order_time)
        var itemList: TextView = itemView.findViewById(R.id.tv_order_list_items)

        //        var avail: LinearLayout = itemView.findViewById(R.id.linear_layout_for_availability)
//        var images: ImageView = itemView.findViewById(R.id.food_images)
//        var add: TextView = itemView.findViewById(R.id.add_to_cart)
        var remove: TextView = itemView.findViewById(R.id.remove_from_cart)
//        var goToCart : TextView = itemView.findViewById(R.id.go_to_cart)
//        var totalAmount: Button = itemView.findViewById(R.id.total_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.orders_list_item,
            parent, false
        )

        return ViewOrdersAdapter.MyViewHolder2(itemView)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val currentItem = orders[position]
//
//        val string :Orders = orders.get(position)

        holder.orderNumber.text = currentItem.order_number.toString()
        holder.price.text = "₹ " + currentItem.amount.toString()
        holder.time.text = currentItem.order_time.toString()
        holder.itemList.text = currentItem.items.toString()
//        holder.avail.text = currentItem.available.toString()

        holder.remove.setOnClickListener {
            dbRef = FirebaseDatabase.getInstance()
            dbRef2 = FirebaseDatabase.getInstance()
            dbRef.getReference("Orders/${currentItem.order_number}").removeValue().addOnCompleteListener {
//                dbRef2.getReference("number").setValue(+1)
                orders.remove(currentItem)
                Toast.makeText(
                    holder.itemView.context,
                    "Order Made Successfully ${currentItem.order_number}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

//    fun updateOrderList(orderList: List<Orders>) {
//        this.orders.clear()
//        this.orders.addAll(orderList)
//        notifyDataSetChanged()
//
//    }

}