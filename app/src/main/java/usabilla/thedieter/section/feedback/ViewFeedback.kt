package usabilla.thedieter.section.feedback

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Quadrant
import kotlinx.android.synthetic.main.view_feedback.*
import usabilla.thedieter.R
import usabilla.thedieter.util.loadFeedbacks


class ViewFeedback : Fragment() {

    private var adapter: FeedbackAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.view_feedback, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stackView.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(quadrant: Quadrant) {
                Log.d("CardStackView", "onCardSwiped: " + quadrant.toString())
                Log.d("CardStackView", "topIndex: " + stackView.topIndex)
//                if (stackView.topIndex == adapter?.count() - 5) {
//                    Log.d("CardStackView", "Paginate: " + stackView.topIndex)
//                    paginate()
//                }
            }

            override fun onCardReversed() {
                Log.d("CardStackView", "onCardReversed")
            }

            override fun onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin")
            }

            override fun onCardClicked(index: Int) {
                Log.d("CardStackView", "onCardClicked: " + index)
            }
        })
        load()
    }

    private fun load() {
        stackView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            adapter = FeedbackAdapter(context, loadFeedbacks(context))
            stackView.setAdapter(adapter)
            stackView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }, 1000)
    }
}