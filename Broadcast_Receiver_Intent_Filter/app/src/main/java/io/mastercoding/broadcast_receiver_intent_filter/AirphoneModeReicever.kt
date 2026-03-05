package io.mastercoding.broadcast_receiver_intent_filter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirphoneModeReicever: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent?.action!=null && intent.action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){

            var isAirplanemodeOn: Boolean=intent.getBooleanExtra("State",false)
            if(isAirplanemodeOn)
            {
                Toast.makeText(context,"On........................ ", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"OfF/////////////////////", Toast.LENGTH_LONG).show()
            }
        }
    }
}