package com.example.odyssey_day2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BathAdapter: RecyclerView.Adapter<BathAdapter.ViewHolder>() {
    private val list : MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.people_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_user = view.findViewById<TextView>(R.id.tv_user)

        fun bind(name: String){
            tv_user.setText(name)
        }
    }

    fun update(newList: MutableList<String>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}