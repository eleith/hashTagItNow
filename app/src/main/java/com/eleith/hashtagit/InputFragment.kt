package com.eleith.hashtagit

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_input.*
import android.view.inputmethod.EditorInfo



class InputFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onStart() {
        super.onStart()
        mEditText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                button.performClick();
                true
            } else {
                false
            }
        }

        button.setOnClickListener {
            val directions = InputFragmentDirections.actionInputFragmentToSubmissionFragment()
            directions.writtenText = mEditText.text.toString()
            findNavController().navigate(directions)
        }
    }

    override fun onResume() {
        super.onResume()

        // if go to another fragment && it is landscape
        // then make sure each time on resume to here, puts it back to portrait
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}