package com.mastercoding.sportsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SportsAdapter(val sportsList:ArrayList<SportModel>)
    : RecyclerView.Adapter<SportsAdapter.MyViewHolder>()
{

    // ViewHolder: Holds references to the views within
    // each item in the recyclerView
    inner class MyViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

            var sportsImg: ImageView
            var sportsName: TextView

            init {
                sportsImg = itemView.findViewById(R.id.imageViewCard)
                sportsName = itemView.findViewById(R.id.textView)

                // Handling the click events on cardviews
                itemView.setOnClickListener(){
                    Toast.makeText(
                        itemView.context,
                        "You Clicked: ${sportsList[adapterPosition].sportName}"
                        , Toast.LENGTH_SHORT
                    ).show()
                }





            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Create and return a new ViewHolder Instance for each item
        val v = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.card_item_layout,
                parent,
                false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        // Returns the total number of items in the dataset
        return sportsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data to views based on the item at the specified position
        holder.sportsImg.setImageResource(sportsList[position].sportImg)
        holder.sportsName.setText(sportsList[position].sportName)
    }


}