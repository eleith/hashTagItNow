package abdulla.com.hashtagit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

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

    override fun onResume() {
        super.onResume()
    }
}
