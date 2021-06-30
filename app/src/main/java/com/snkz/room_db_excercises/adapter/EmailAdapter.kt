package com.snkz.room_db_excercises.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.snkz.room_db_excercises.databinding.ItemMainEmailBinding
import com.snkz.room_db_excercises.entity.Email

class EmailAdapter(var lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {
    val arrEmail = mutableListOf(Email("snkz","lmao", "nothing"))

    class ViewHolder (val binding: ItemMainEmailBinding, val lifecycleOwner: LifecycleOwner)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(email : Email){
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMainEmailBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: EmailAdapter.ViewHolder, position: Int) {
       val email = arrEmail[position]
        holder.binding.textItemSender.text = "Sender: " +email.senderEmail
        holder.binding.textItemTitle.text = "Title: " +email.title
        holder.binding.textItemContent.text = "Content: " +email.content
    }

    override fun getItemCount(): Int {
        if (arrEmail != null){
            return arrEmail.size
        }
        return  0
    }
}