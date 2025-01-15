package com.example.adobeapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adobeapp.database.Item
import com.example.adobeapp.database.ItemDao
import com.example.adobeapp.database.ItemRoomDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var TAG = MainActivity::class.java.simpleName
    lateinit var viewModel:MainViewModel

    lateinit var dao: ItemDao
    lateinit var tvMain:TextView

   // var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.maintv)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        tvMain.text = ""+viewModel.count

        var database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()

        Log.i(TAG,"actvitiy created")
    }

    var secsObserverphno: Observer<Int> = object : Observer<Int> {
        override fun onChanged(seconds: Int) {
            //receiving the update/notification
            tvMain.setText(seconds.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG,"actvitiy started")
        viewModel._seconds.observe(this, secsObserverphno);
    //me giving my phno to the postman

    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"actvitiy paused")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"actvitiy resumed")

    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG,"actvitiy stopped")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"actvitiy destroyed")

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
        //incremenntCounter()
        viewModel.startTimer()
        tvMain.text = "" + viewModel._seconds


    }

    private fun incremenntCounter() {
        //count++
        viewModel.incrementCountVar()
        tvMain.text = "" + viewModel.count
    }


}