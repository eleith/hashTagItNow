package com.eleith.hashtagit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class HashTagItActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_tag_it)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.icon_inverse -> {

                // actually, probably dont need view model
                // and can just change theme here on the clicks
                val vm = ViewModelProviders.of(this)[BackgroundViewModel::class.java]
                vm.backgroundColor.value?.apply {
                    !this
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
