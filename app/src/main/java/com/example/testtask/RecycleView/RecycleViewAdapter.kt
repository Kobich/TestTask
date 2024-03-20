package com.example.testtask.RecycleView

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.APi.DataUserModel.DataModel
import com.example.testtask.Activity.UserProfileActivity
import com.example.testtask.R

class RecycleViewAdapter(private val dataList: List<DataModel>) : RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val fullNameTextView: TextView = itemView.findViewById(R.id.fullNameTextView)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumberTextView)
        val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val person = dataList[position]
        holder.fullNameTextView.text = "${person.results[0].name.title} " +
                "${person.results[0].name.first} ${person.results[0].name.last}"

        holder.addressTextView.text = "${person.results[0].location.street.number} ${person.results[0].location.street.name}, " +
                "${person.results[0].location.city}, ${person.results[0].location.country}"

        holder.phoneNumberTextView.text = person.results[0].phone
        Glide.with(holder.itemView.context)
            .load(person.results[0].picture.thumbnail)
            .into(holder.photoImageView)

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, UserProfileActivity::class.java)

            // Если нужно передать какие-то данные в новую Activity, используйте метод putExtra
            intent.putExtra("person_id", person) // Пример передачи идентификатора выбранного элемента
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
