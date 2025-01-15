package com.example.adobeapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adobeapp.database.Item
import com.example.adobeapp.database.ItemDao
import com.example.adobeapp.database.ItemRoomDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var dao: ItemDao
    lateinit var tvMain:TextView

    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.maintv)

        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()

    }

    override fun onStart() {
        super.onStart()
    }



    fun clickHandler(view: View) {
       // showSnackbar()
        insertDataDb()
    }


    private fun insertDataDb() {
        GlobalScope.launch {
            var groceryItem: Item = Item(11,"fruits",11.11,22)
            dao.insert(groceryItem)
        }
    }


    private fun showSnackbar() {
        var view: ConstraintLayout = findViewById(R.id.maincl)
        var snackbar: Snackbar = Snackbar.make(view, "1 item archived", Snackbar.LENGTH_LONG)
        snackbar.setAction("undo", {})
        snackbar.show()
    }

    fun queryDb(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            var item = dao.getItems().first()

            tvMain.setText(item.toString())
        }
    }

    fun incrementCount(view: View) {
        count++
        tvMain.text = ""+count
    }
}