package com.example.kotlin_geeks.ui.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kotlin_geeks.data.Car
import com.example.kotlin_geeks.databinding.ItemTaskBinding

class CarAdapter() : Adapter<CarAdapter.CarViewHolder>() {

    private val list = arrayListOf<Car>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCars(cars: List<Car>) {
        list.clear()
        list.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CarViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun onBind(car: Car) = with(binding) {
            tvTitle.text = car.label
            tvDescription.text = car.model
        }
    }
}