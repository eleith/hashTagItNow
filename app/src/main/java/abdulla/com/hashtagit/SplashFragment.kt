package abdulla.com.hashtagit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*

class SplashFragment: Fragment() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onStart() {
        super.onStart()

        // splash fragment is in charge of setting that it has seen it
        // because there could always be a navigation trouble and user has actually seen this yet
        (activity as? HashTagItActivity)?.sharedPrefs?.apply {
            this.edit().putBoolean("seenSplash", true).apply()
        }

        uiScope.launch {
            withContext(Dispatchers.IO) {
                delay(5000)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToInputFragment())
            }
        }
    }

    override fun onPause() {
        super.onPause()

        // make sure if user leaves screen to early before delay, we have killed the couroutine
        job.cancel()
    }
}