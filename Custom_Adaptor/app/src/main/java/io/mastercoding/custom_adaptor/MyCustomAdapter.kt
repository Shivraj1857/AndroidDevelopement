package io.mastercoding.custom_adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyCustomAdapter(private val context: Context,private val items: List<String>): BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any? {
      return items[position]
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view:View
        if(convertView==null)
        {
            view=inflater.inflate(R.layout.my_custom_layout,parent,false)
        }
        else{
            view=convertView
        }
        val item=getItem(position)as String
        val titleTextView=view.findViewById<TextView>(R.id.textViewCustom)
        titleTextView.text=item
        return view
    }
}