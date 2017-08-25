package usabilla.thedieter.main

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import usabilla.thedieter.section.feedback.ViewFeedback
import usabilla.thedieter.section.send.ViewSend

class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(i: Int): android.support.v4.app.Fragment {
        when (i) {
            0 -> return ViewFeedback()
            else -> return ViewSend()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return (position + 1).toString()
    }

}