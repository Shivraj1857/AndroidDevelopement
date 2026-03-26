package io.mastercoding.myandroidportfolio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.mastercoding.myandroidportfolio.R
import io.mastercoding.myandroidportfolio.model.AppModel



class AppListAdapter(
    private val appList: List<AppModel>,
    private val onItemClick: (AppModel) -> Unit
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {


    // ViewHolder
    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIcon: ImageView = itemView.findViewById(R.id.imgAppIcon)
        val txtName: TextView = itemView.findViewById(R.id.txtAppName)
        val txtDescription: TextView = itemView.findViewById(R.id.txtAppDescription)
    }

    // 2 Create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    // 3️ Bind data
    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = appList[position]
        holder.txtName.text = app.appName
        holder.txtDescription.text = app.appDescription
        holder.imgIcon.setImageResource(app.appIcon)


        holder.itemView.setOnClickListener {
            onItemClick(app)
        }
    }

    // 4️ Item count
    override fun getItemCount(): Int {
        return appList.size
    }
}


