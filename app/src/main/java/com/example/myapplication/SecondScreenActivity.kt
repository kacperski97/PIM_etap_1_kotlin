package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SecondScreenActivity : AppCompatActivity() {
    val result: Intent = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val unlocked: ArrayList<Int> = ArrayList()
        result.putExtra("toUnlock", unlocked)
        findViewById<Button>(R.id.unlockx10).setOnLongClickListener { view ->
            showPrice(view)
            true
        }
        findViewById<Button>(R.id.unlockx2).setOnLongClickListener { view ->
            showPrice(view)
            true
        }
        findViewById<Button>(R.id.unlockx5).setOnLongClickListener { view ->
            showPrice(view)
            true
        }
        findViewById<Button>(R.id.unlockx50).setOnLongClickListener { view ->
            showPrice(view)
            true
        }
    }

    fun showPrice(view: View) {
        val button: Button = view as Button
        val num: Int = getNumberFromButton(button)
        val price: Int = num * 1000 + num * num
        val info = getString(R.string.upgrade_info) + price
        val mssg: Snackbar = Snackbar.make(view, info, Snackbar.LENGTH_SHORT)
        mssg.show()
    }

    fun unlockItem(view: View) {
        val num: Int = getNumberFromButton(view as Button)
        val price: Int = num * 1000 + num * num
        Log.d("myLog", price.toString())
        if (MainActivity.counter < price) {
            val mssg: Snackbar = Snackbar.make(
                view, "You don't have enough currency!",
                Snackbar.LENGTH_SHORT
            )
            mssg.show()
            return
        }
        MainActivity.counter -= price
        var unlocked: ArrayList<Int>? = result.getIntegerArrayListExtra("toUnlock")

        when (num) {
            2 -> unlocked?.add(R.id.tapx2)
            5 -> unlocked?.add(R.id.tapx5)
            10 -> unlocked?.add(R.id.tapx10)
            50 -> unlocked?.add(R.id.tapx50)
        }
        result.putExtra("toUnlock", unlocked)
    }

    private fun getNumberFromButton(button: Button): Int {
        val text: String = button.text.toString()
        val numStr: String = text.subSequence(text.indexOf("x") + 1, text.length).toString()
        return numStr.toInt()
    }

    fun goBack(view: View) {
        setResult(RESULT_OK, result)
        this.finish()
    }
}