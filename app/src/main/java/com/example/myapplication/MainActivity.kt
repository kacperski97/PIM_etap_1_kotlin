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

    companion object {
        var counter: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun registerClick(view: View) {
        val myButton: Button = view as Button
        val text = myButton.text as String
        counter += text.subSequence(text.indexOfFirst { it == 'x' } + 1, text.length)
            .toString().toInt()
        findViewById<TextView>(R.id.score).text = counter.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if((requestCode == 1) and (resultCode == RESULT_OK)) {
            var buttons: ArrayList<Int>? = data?.getIntegerArrayListExtra("toUnlock")
            if (buttons != null) {
                for (buttonId in buttons){
                    findViewById<Button>(buttonId).isEnabled = true
                }
            }
        }
    }

    fun changeScreen(view: View) {
        startActivityForResult(Intent(this, SecondScreenActivity::class.java), 1)
    }

}