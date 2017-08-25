package usabilla.thedieter.util

import android.content.Context
import com.google.gson.GsonBuilder
import org.json.JSONArray
import usabilla.thedieter.main.Feedback

fun loadFeedbacks(context: Context): MutableList<Feedback> {
    val feedbackList = ArrayList<Feedback>()
    try {
        val builder = GsonBuilder()
        val gson = builder.create()
        val array = JSONArray(loadJSONFromAsset(context, "feedbacks.json"))
        (0..array.length() - 1).mapTo(feedbackList) { gson.fromJson(array.getString(it), Feedback::class.java) }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return feedbackList
}

private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
    var json: String? = null
    context.assets.open(jsonFileName).use {
        val size = it.available()
        val buffer = ByteArray(size)
        it.read(buffer)
        json = String(buffer)
    }
    return json
}