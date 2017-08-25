package usabilla.thedieter.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import usabilla.thedieter.R

class FeedbackAdapter(context: Context, feedbacks: List<Feedback>) : ArrayAdapter<Feedback>(context, 0, feedbacks) {

    override fun getView(position: Int, cntView: View?, parent: ViewGroup): View {
        var contentView = cntView
        val holder: ViewHolder

        if (contentView == null) {
            val inflater = LayoutInflater.from(context)
            contentView = inflater.inflate(R.layout.feedback_item, parent, false)
            holder = ViewHolder(contentView)
            contentView!!.tag = holder
        } else {
            holder = contentView.tag as ViewHolder
        }

        val feedback = getItem(position)

        holder.message.text = feedback!!.comment
        holder.date.text = feedback.date

        when(feedback.rating) {
            1 -> holder.icon.setImageResource(R.drawable.ic_mood0)
            2 -> holder.icon.setImageResource(R.drawable.ic_mood1)
            3 -> holder.icon.setImageResource(R.drawable.ic_mood2)
            4 -> holder.icon.setImageResource(R.drawable.ic_mood3)
            else -> holder.icon.setImageResource(R.drawable.ic_mood4)
        }
        return contentView
    }

    private class ViewHolder(view: View) {
        var message: TextView = view.findViewById<View>(R.id.feedbackText) as TextView
        var date: TextView = view.findViewById<View>(R.id.feedbackDate) as TextView
        var icon: ImageView = view.findViewById<View>(R.id.feedbackMood) as ImageView
    }

}

