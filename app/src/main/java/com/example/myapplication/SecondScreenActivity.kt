package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SecondScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
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
    fun showPrice(view : View) {
        val button: Button = view as Button
        val num: Int = getNumberFromButton(button)
        val price: Int = num * 1000 + num * num
        val info = "This upgrade costs: $price"
        val mssg: Snackbar = Snackbar.make(view, info, Snackbar.LENGTH_SHORT)
        mssg.show()
    }
    fun unlockItem(view: View) {
        val num : Int =getNumberFromButton(view as Button)
        val price : Int = num  * 1000 + num * num
        Log.d("myLog", price.toString())
        if (MainActivity.counter < price) {
            var mssg : Snackbar = Snackbar.make( view, "You don't have enough currency!",
                Snackbar.LENGTH_SHORT)
            mssg.show()
            return
        }
        val id : Int = resources.getIdentifier(resources.getString(R.string.id_prefix)
                    + num.toString(), "id", packageName)
        val button : Button = findViewById(id)
        button.isEnabled=true
        MainActivity.counter -= price
    }

    private fun getNumberFromButton(button: Button): Int {
        var text: String = button.text.toString()
        var numStr: String = text.subSequence(text.indexOf("x") + 1, text.length).toString()
        return numStr.toInt()
    }

     fun goBack(view: View){
        this.finish()
    }
}