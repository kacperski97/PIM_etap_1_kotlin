package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondScreenActivity : AppCompatActivity() {

    val result: Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val unlocked: ArrayList<Int> = ArrayList()
        result.putExtra("toUnlock", unlocked)
        val tags: ArrayList<String>? = intent.extras?.getStringArrayList("buttons")
        if (tags != null) {
            for (tag in tags) {
                when (tag) {
                    "x5" -> findViewById<Button>(R.id.unlockBetter).isEnabled = false
                    "x10" -> findViewById<Button>(R.id.unlockSuper).isEnabled = false
                    "x50" -> findViewById<Button>(R.id.unlockMega).isEnabled = false
                    "x100" -> findViewById<Button>(R.id.unlockGiga).isEnabled = false
                }
            }
        }
        findViewById<TextView>(R.id.score).text =
            resources.getString(R.string.score_display) + MainActivity.counter.toString()

    }

    fun getPrice(num: Int): Int {
        when (num) {
            5 -> return 25
            10 -> return 100
            50 -> return 1000
            100 -> return 5000
            else -> return -1
        }
    }

    fun unlockItem(view: View) {
        val num: Int = getNumberFromButton(view as Button)
        val price: Int = getPrice(num)
        if (MainActivity.counter < price) {
            val mssg = Toast.makeText(
                applicationContext,
                resources.getString(R.string.shop_warning),
                Toast.LENGTH_SHORT
            )
            mssg.show()
            return
        }
        MainActivity.counter -= price
        var unlocked: ArrayList<Int>? = result.getIntegerArrayListExtra("toUnlock")

        when (num) {
            5 -> unlocked?.add(R.id.BetterButton)
            10 -> unlocked?.add(R.id.SuperButton)
            50 -> unlocked?.add(R.id.MegaButton)
            100 -> unlocked?.add(R.id.GigaButton)
        }
        result.putExtra("toUnlock", unlocked)
        goBack(view)
    }

    private fun getNumberFromButton(button: Button): Int {
        val text: String = button.tag.toString()
        val numStr: String = text.subSequence(text.indexOf("x") + 1, text.length).toString()
        return numStr.toInt()
    }

    fun goBack(view: View) {
        setResult(RESULT_OK, result)
        this.finish()
    }
}