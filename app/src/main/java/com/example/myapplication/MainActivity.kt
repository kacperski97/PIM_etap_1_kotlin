package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var activeButtons: ArrayList<Button> = ArrayList()

    companion object {
        var counter: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        activeButtons.add(findViewById(R.id.Button))
        findViewById<TextView>(R.id.score).text =
            resources.getString(R.string.score_display) + counter.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun registerClick(view: View) {
        val myButton: Button = view as Button
        val text = myButton.tag as String
        counter += text.subSequence(text.indexOfFirst { it == 'x' } + 1, text.length)
            .toString().toInt()
        findViewById<TextView>(R.id.score).text =
            resources.getString(R.string.score_display) + counter.toString()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == 1) and (resultCode == RESULT_OK)) {
            var buttons: ArrayList<Int>? = data?.getIntegerArrayListExtra("toUnlock")
            if (buttons != null) {
                for (buttonId in buttons) {
                    findViewById<Button>(buttonId).isEnabled = true
                    activeButtons.add(findViewById(buttonId))
                }
            }
        }
        findViewById<TextView>(R.id.score).text =
            resources.getString(R.string.score_display) + counter.toString()
    }

    fun changeScreen(view: View) {
        val tags: ArrayList<String> = ArrayList()
        for (button in activeButtons) {
            tags.add(button.tag.toString())
        }
        val i = Intent(this, SecondScreenActivity::class.java)
        i.putExtra("buttons", tags)
        startActivityForResult(i, 1)
    }

}