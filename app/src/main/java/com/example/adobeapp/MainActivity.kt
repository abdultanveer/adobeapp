package com.example.adobeapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun clickHandler(view: View) {
        showSnackbar()
    }

    private fun showSnackbar() {
        var view: ConstraintLayout = findViewById(R.id.maincl)
        var snackbar: Snackbar = Snackbar.make(view, "1 item archived", Snackbar.LENGTH_LONG)
        snackbar.setAction("undo", {})
        snackbar.show()
    }
}