package com.handlebroadcast

object IntentConfiguration {
  private val filterMap: MutableMap<String, String> = mutableMapOf()

  fun getMap(): MutableMap<String, String> {
    return filterMap
  }

  fun addToMap(args: List<Pair<String, String>>) {
    args.forEach {
      filterMap[it.first] = it.second
    }
  }
}
