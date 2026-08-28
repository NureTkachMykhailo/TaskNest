package com.mtkach.tasknest.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class TodoStore(context: Context) {
    private val prefs = context.getSharedPreferences("tasknest_todos", Context.MODE_PRIVATE)

    fun load(): List<TodoItem> {
        val raw = prefs.getString(KEY, null) ?: return emptyList()
        val arr = JSONArray(raw)
        return (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            TodoItem(
                id = o.getString("id"),
                title = o.getString("title"),
                done = o.getBoolean("done")
            )
        }
    }

    fun save(items: List<TodoItem>) {
        val arr = JSONArray()
        items.forEach { item ->
            val o = JSONObject()
            o.put("id", item.id)
            o.put("title", item.title)
            o.put("done", item.done)
            arr.put(o)
        }
        prefs.edit().putString(KEY, arr.toString()).apply()
    }

    companion object {
        private const val KEY = "items"
    }
}
