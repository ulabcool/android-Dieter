package usabilla.thedieter.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Quadrant
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.main_activity.*
import usabilla.thedieter.R
import usabilla.thedieter.util.loadFeedbacks

class MainActivity : AppCompatActivity() {

    private var colorBg: Int = R.color.background_bad
    private var adapter: FeedbackAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        stackView.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                if (adapter?.count!! > stackView.topIndex + 1) {
                    setBgColor(adapter?.getItem(stackView.topIndex + 1)?.rating!!)
                    container.setBackgroundColor(ContextCompat.getColor(baseContext, colorBg))
                }
            }

            override fun onCardSwiped(quadrant: Quadrant) {
                if (adapter?.count!! < stackView.topIndex + 1) {
                    feedbackView.visibility = View.INVISIBLE
                    finalTextView.visibility = View.VISIBLE
                }
            }

            override fun onCardReversed() {
            }

            override fun onCardMovedToOrigin() {
                setBgColor(adapter?.getItem(stackView.topIndex)?.rating!!)
                container.setBackgroundColor(ContextCompat.getColor(baseContext, colorBg))
            }

            override fun onCardClicked(index: Int) {
            }
        })
        loadFeedbacks()

        startButton.setOnClickListener {
            val startFadeOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
            startFadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    initialView.visibility = View.GONE
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
            initialView.startAnimation(startFadeOutAnimation)
        }

        retryButton.setOnClickListener {
            loadFeedbacks()
            feedbackView.visibility = View.VISIBLE
            finalTextView.visibility = View.INVISIBLE
        }

        icon_hate.setOnClickListener { swipeLeft() }
        icon_share.setOnClickListener { Toast.makeText(baseContext, "Sharing is coming", Toast.LENGTH_SHORT).show() }
        icon_ok.setOnClickListener { swipeRight() }
    }

    private fun loadFeedbacks() {
        val feedbacks = loadFeedbacks(baseContext)
        setBgColor(feedbacks[0].rating)
        adapter = FeedbackAdapter(baseContext, feedbacks)
        stackView.setAdapter(adapter)
    }

    private fun setBgColor(mood: Int) {
        when (mood) {
            1, 2 -> colorBg = R.color.background_bad
            3 -> colorBg = R.color.background_normal
            else -> colorBg = R.color.background_ok
        }
    }

    fun swipeLeft() {
        val target = stackView.getTopView()

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", -10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val set = AnimatorSet()
        set.playTogether(rotation, translateX, translateY)

        stackView.swipe(SwipeDirection.Left, set)
    }

    fun swipeRight() {
        val target = stackView.getTopView()

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val set = AnimatorSet()
        set.playTogether(rotation, translateX, translateY)

        stackView.swipe(SwipeDirection.Right, set)
    }
}
