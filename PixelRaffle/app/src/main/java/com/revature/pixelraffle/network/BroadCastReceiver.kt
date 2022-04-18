package com.revature.pixelraffle.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast

class AirplaneModeChangeReceiver : BroadcastReceiver() {

    // this function will be executed when the user changes his
    // airplane mode
    override fun onReceive(context: Context?, intent: Intent?) {

        // intent contains the information about the broadcast
        // in our case broadcast is change of airplane mode

        // if getBooleanExtra contains null value,it will directly return back
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        // checking whether airplane mode is enabled or not
        if (isAirplaneModeEnabled) {
            // showing the toast message if airplane mode is enabled
            Toast.makeText(context, "Airplane Mode Enabled, Data May Be Outdated", Toast.LENGTH_LONG).show()
        } else {
            // showing the toast message if airplane mode is disabled
            Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
        }
    }
}

class Wifi :  BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?)  {

        // intent contains the information about the broadcast
        // in our case broadcast is change of airplane mode

        // if getBooleanExtra contains null value,it will directly return back


        val isWifiEnabled = intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN) ?: return
        if (isWifiEnabled == WifiManager.WIFI_STATE_ENABLED) {
            // showing the toast message if airplane mode is enabled
            Toast.makeText(context, "Wifi Enabled", Toast.LENGTH_LONG).show()
        } else {
            // showing the toast message if airplane mode is disabled
            Toast.makeText(context, "Wifi Disabled, Displaying Old Data", Toast.LENGTH_LONG).show()
        }

    }

}