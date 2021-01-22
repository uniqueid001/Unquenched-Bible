package com.theunquenchedservant.granthornersbiblereadingsystem.utilities

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.theunquenchedservant.granthornersbiblereadingsystem.utilities.Marker.markAll
import com.theunquenchedservant.granthornersbiblereadingsystem.utilities.SharedPref.getIntPref
import com.theunquenchedservant.granthornersbiblereadingsystem.utilities.SharedPref.getStringPref

class DoneReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val doneMax = when(SharedPref.getStringPref("planSystem", "pgh")){
            "pgh"->10
            "mcheyne"->4
            else->10
        }
        when(getIntPref("listsDone")) {
            in 0 until doneMax -> {
                if(getStringPref("planSystem", "pgh") == "pgh") {
                    markAll()
                }else{
                    markAll("mcheyne")
                }
                Toast.makeText(context, "Lists Marked!", Toast.LENGTH_LONG)
                        .show()
            }
            doneMax -> Toast.makeText(context, "Already Done!", Toast.LENGTH_LONG).show()
        }
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.cancel(1)
        mNotificationManager.cancel(2)

    }
}