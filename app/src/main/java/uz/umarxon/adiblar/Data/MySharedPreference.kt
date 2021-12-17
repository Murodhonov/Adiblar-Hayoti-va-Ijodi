package uz.umarxon.adiblar.Data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.umarxon.adiblar.models.AdibClass

object MySharedPreference {
    private const val NAME = "forCache"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operations: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operations(editor)
        editor.apply()
    }

    var adibList: ArrayList<AdibClass>
        get() = Gson().fromJson(preferences.getString("adib", "[]")!!,
            object : TypeToken<ArrayList<AdibClass>>() {}.type
        )
        set(value) = preferences.edit {
            it.putString("adib", Gson().toJson(value))
        }

    var currentPos = 0
    var selectedPos = 0
    var listSize = 0
}