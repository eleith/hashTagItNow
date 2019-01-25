package com.eleith.hashtagit

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

        (activity as? HashTagItActivity)?.showToolbar(true)

        uiScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToInputFragment())
            }
        }
    }

    override fun onPause() {
        super.onPause()

        (activity as? HashTagItActivity)?.showToolbar(false)

        // make sure if user leaves screen to early before delay, we have killed the couroutine
        job.cancel()
    }
}