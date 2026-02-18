package io.mastercoding.planetsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast



class MycustomAdapter(val context:Context,val planets:List<planet>): BaseAdapter() {
    override fun getCount(): Int {
        return planets.size
    }
//Return
    override fun getItem(position: Int): Any? {
        return planets[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val inflatter=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view: View
        if(convertView==null)
        {
            view=inflatter.inflate(R.layout.item_list_layout,parent,false)

        }
        else{
            view=convertView
        }
        //bind data to view
        val item=getItem(position)as planet
        //ini views
        val titleView=view.findViewById<TextView>(R.id.planet_name)
        val moonCountTextView=view.findViewById<TextView>(R.id.moon_count_text)
        val moonimage=view.findViewById<ImageView>(R.id.imageView)

        titleView.text=item.title
        moonCountTextView.text=item.moonCount
        moonimage.setImageResource(item.imagePlanet)

        view.setOnClickListener {
            Toast.makeText(context,"The clicked is: ${planets[position].title}", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
