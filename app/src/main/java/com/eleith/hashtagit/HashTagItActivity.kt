package com.eleith.hashtagit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_hash_tag_it.*


class HashTagItActivity : AppCompatActivity() {

    fun showToolbar(toggle: Boolean) {
        if (toggle) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_tag_it)
        setSupportActionBar(toolbar)
    }
}
