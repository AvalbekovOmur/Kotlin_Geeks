package com.example.kotlin_geeks.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.kotlin_geeks.databinding.ItemOnboardingBinding
import com.example.kotlin_geeks.model.OnBoarding

class OnBoardingAdapter(private val onClick: () ->Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){


    val list = listOf(
        OnBoarding("First page", "Hello everyone it is so good app :)","https://www4.fisheries.go.th/local/pic_activities/202007211514031_pic.jpg"),
        OnBoarding("Second  page", "Hello everyone it is so good app :)","https://i.pinimg.com/736x/3e/5b/3a/3e5b3a55a757aa664704f6f33f2c1c4b.jpg"),
        OnBoarding("Last  page", "Hello everyone it is so good app :)","https://sun9-55.userapi.com/impg/6w5oz3bmzJL3TIFO0bsoQKOIRqbwMAAPrcDWKQ/krkXvGfo7-4.jpg?size=1024x945&quality=95&sign=c074db9b96717834cad8e963dc99f883&c_uniq_tag=u3ENY-Ynzfqc7o9xVUN-TYimaphuXVwuCePt26rI8bg&type=album")
    )

    inner class  OnBoardingViewHolder(private  val binding: ItemOnboardingBinding):ViewHolder(binding.root){
        fun onBind (onBoarding: OnBoarding){
            binding.tvTitle.text = onBoarding.title
            binding.tvDescription.text = onBoarding.description
            Glide.with(binding.imageView).load(onBoarding.image).into(binding.imageView)
            binding.btnNext.isVisible = adapterPosition == list.lastIndex
            binding.btnNext.setOnClickListener{
                onClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}