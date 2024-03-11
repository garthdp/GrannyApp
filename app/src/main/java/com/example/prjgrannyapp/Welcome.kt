package com.example.prjgrannyapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class Welcome : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var image: Bitmap? = null
        val imgOutput : ImageView = findViewById(R.id.imWelcome)
        val txtWelcomeMessage : TextView = findViewById(R.id.txtWelcome)
        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()

        executor.execute{
            val imageURL = arrUsers[SignedIndex].imageURL
            try
            {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                Log.d("Welcome", "Image added "+ image.toString())
                handler.post{
                    Log.d("Welcome", "Image added")
                    txtWelcomeMessage.text = "Welcome " + arrUsers[SignedIndex].name
                    imgOutput.setImageBitmap(image)
                }
            }
            catch (e:java.lang.Exception)
            {
                Log.d("Welcome", "Error occurred: $e")
                e.printStackTrace()
            }
        }

        val feed : RecyclerView = findViewById(R.id.recyclerView)

        var userAdapter : UserAdapter
        userAdapter = UserAdapter()
        feed.apply{
            layoutManager = LinearLayoutManager(this@Welcome)
            adapter=userAdapter
        }
        userAdapter.submitList(arrUsers)

    }
}