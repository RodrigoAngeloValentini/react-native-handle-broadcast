package com.handlebroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Arguments

class BroadcastReceiverReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context?, intent: Intent?) {
    val data = getDataFromIntent(intent)
    if ((data != null) && data.isNotEmpty()) {
      val params = Arguments.createMap()
      params.putString(Constants.DataProp, data)
      HandleBroadcastModule.sendEvent(Constants.BroadcastEventName, params)
    }
  }

  private fun getDataFromIntent(intent: Intent?): String? {
    val action = intent?.action
    var data: String? = null
    val intentFilterMap = IntentConfiguration.getMap()

    Log.d(TAG, intentFilterMap.toString())
    if (intentFilterMap.containsKey(action)) {
      val dataKey = intentFilterMap[action]
      data = intent?.extras?.get(dataKey).toString()
    }

    return data?.trim()
  }

  companion object {
    const val TAG = "Receiver"
  }
}
