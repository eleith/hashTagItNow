package abdulla.com.hashtagit

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.submission_fragment.*


class SubmissionFragment : Fragment() {

    // going from portrait to landscape will cause things to be called twice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // so need to make check with orientation
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.submission_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity?.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE) {
            arguments?.apply {
                textView.text = SubmissionFragmentArgs.fromBundle(this).writtenText
                textView.measure(0,0)

                putIntoLeanBackMode()


                val difference = getTextViewWidthDifferenceFromParent(textView, activity)
                Log.e("DIFFERENCE", "" + difference);

                when (Bounds.toBounds(difference)) {
                    Bounds.OUT_OF_BOUNDS -> {
                        val abs = Math.abs(difference)

                        val animator = ObjectAnimator.ofInt(horizontalScroll, "scrollX", abs)

                        // how big the word will easy determines length of animation
                        // for readability
                        animator.duration = (10.0 * abs).toLong()

                        animator.repeatMode = ValueAnimator.REVERSE
                        animator.repeatCount = ValueAnimator.INFINITE
                        animator.start()
                    }
                    Bounds.IN_BOUNDS -> {

                    }
                }
            }
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        }
    }

    // https://developer.android.com/training/system-ui/immersive#leanback
    private fun putIntoLeanBackMode() {
        activity?.window?.decorView?.systemUiVisibility =  View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun getTextViewWidthDifferenceFromParent(tv: TextView, act: Activity?): Int {
        val displayMetrics = DisplayMetrics()
        act?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        return width - tv.measuredWidth
    }


    enum class Bounds {
        IN_BOUNDS,
        OUT_OF_BOUNDS;

        companion object {
            fun toBounds(int: Int): Bounds {
                if (int < 0) {
                    return Bounds.OUT_OF_BOUNDS
                }
                return Bounds.IN_BOUNDS
            }
        }
    }

}