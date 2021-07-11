package com.example.characters.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.R
import com.example.characters.data.api.response.CharacterResponse
import com.example.characters.databinding.CharacterGridViewItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class CharacterAdapter : ListAdapter<CharacterResponse, CharacterAdapter.MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(CharacterGridViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder( holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)

        holder.bind(movies)
    }

    class MovieViewHolder(val binding: CharacterGridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterResponse) {

            if (character.birthday != "Unknown") {
                val myDate = character.birthday + " 05:30"

                val sdf = SimpleDateFormat("MM-dd-yyyy HH:mm")
                val date: Date = sdf.parse(myDate)
                val timeInMillis: Long = date.getTime()


                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis() - timeInMillis


                var dif = (System.currentTimeMillis() - timeInMillis)

                val days = dif / (1000 * 60 * 60 * 24) % 365

                val month_difference:Long = ((dif / (1000 * 60 * 60 * 24) % 365)/30.4).toLong()
                val days_difference:Long = (days - (month_difference*30.4)).toLong()

                val years_difference = dif / (1000L * 60 * 60 * 24 * 365)

                val seconds_difference = dif / 1000 % 60

                val minutes_difference = dif / (1000 * 60) % 60

                val hours_difference = dif / (1000 * 60 * 60) % 24
                binding.age.text = "age \n"+
                        "Years = "+years_difference+"\n"+
                        "Months = "+month_difference+"\n"+
                        "Days = "+days_difference+"\n"+
                        "Hours = "+hours_difference+"\n"+
                        "Minutes = "+minutes_difference+"\n"+
                        "Seconds = "+seconds_difference

            }else
                binding.age.text = "age "+character.birthday


            binding.name.text = character.name
//            binding.age.text = character.birthday
            val imgUrl = character.img
            imgUrl.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Picasso.get()
                    .load(imgUri)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.characterImage)
            }

        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterResponse>() {

        override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean {
//            return true
            return oldItem.char_id == newItem.char_id
        }

        override fun areContentsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean {
//            return true
            return oldItem == newItem
        }
    }


}