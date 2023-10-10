package com.handlebroadcast

import android.content.IntentFilter
import android.util.Log
import com.facebook.react.BuildConfig
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule

class HandleBroadcastModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext), LifecycleEventListener {
  private var broadcastReceiver = BroadcastReceiverReceiver()

  init {
    cReactContext = reactContext
    cReactContext.addLifecycleEventListener(
      this
    )
  }

  override fun getName(): String {
    return Constants.ModuleName
  }

  private fun getReactContext(): ReactApplicationContext {
    return cReactContext
  }

  @ReactMethod
  fun setIntentConfig(args: ReadableArray, promise: Promise) {
    if (BuildConfig.DEBUG) {
      Log.d(name, args.toString())
      Log.d(name, "addActions: register receiver")
    }
    val argsArray = args.toArrayList().filterIsInstance<List<String>>()
    val data = argsArray.map {
      Pair(it[0], it[1])
    }
    IntentConfiguration.addToMap(data)
    registerBroadcastReceiver()
    promise.resolve(true)
  }

  companion object {
    lateinit var cReactContext: ReactApplicationContext

    fun sendEvent(eventName: String, params: WritableMap) {
      if (cReactContext.hasCatalystInstance()) {
        cReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
          .emit(eventName, params)
      }
    }
  }

  private fun registerBroadcastReceiver() {
    if (BuildConfig.DEBUG) Log.d(name, "register receiver")

    val filter = IntentFilter()
    val filterMap = IntentConfiguration.getMap()

    filterMap.forEach { filter.addAction(it.key) }
    filter.addCategory("android.intent.category.DEFAULT")

    getReactContext().registerReceiver(broadcastReceiver, filter)
  }

  private fun unregisterBroadcastReceiver() {
    getReactContext().unregisterReceiver(broadcastReceiver)
  }

  override fun onHostResume() {
    if (BuildConfig.DEBUG) Log.d(name, "onHostResume: register Application receivers")
    try {
      registerBroadcastReceiver()
    } catch (e: Exception) {
      // REGISTER ERROR
    }
  }

  override fun onHostPause() {
    if (BuildConfig.DEBUG) Log.d(name, "onHostPause: unregister receivers")
    try {
      unregisterBroadcastReceiver()
    } catch (e: Exception) {
      // UNREGISTER ERROR
    }
  }

  override fun onHostDestroy() {
    if (BuildConfig.DEBUG) Log.d(name, "onHostDestroy: Destroy host")
    try {
      unregisterBroadcastReceiver()
    } catch (e: Exception) {
      // UNREGISTER ERROR
    }
  }
}
