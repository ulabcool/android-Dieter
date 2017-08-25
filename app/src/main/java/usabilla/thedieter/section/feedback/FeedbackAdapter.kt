package usabilla.thedieter.section.feedback

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

        holder.text.text = feedback!!.message
        return contentView
    }

    private class ViewHolder(view: View) {
        var text: TextView
        var icon: ImageView

        init {
            text = view.findViewById<View>(R.id.feedbackText) as TextView
            icon = view.findViewById<View>(R.id.feedbackMood) as ImageView
        }
    }

}

