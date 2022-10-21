package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
    }

    fun unlockItem(view: View) {
        var id: Int = getNumberFromButton(Button(view.context))
        findViewById<Button>(resources.getIdentifier("tapx" + id, "int", packageName))
            .isClickable = true
    }

    private fun getNumberFromButton(button: Button): Int {
        var text: String = button.text.toString()
        var numStr: String = text.subSequence(text.indexOf("x") + 1, text.lastIndex).toString()
        return numStr.toInt()
    }


}