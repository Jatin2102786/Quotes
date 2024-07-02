package com.jatin.quotes

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jatin.quotes.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentColorIndex = 0
    private var colorArray = arrayOf(
        Color.GRAY,
        Color.CYAN,
        Color.YELLOW
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        updateBackgroundColor()
        val loading = Loading(this)


        binding.btnNext.setOnClickListener{
//            loading.startLoading()
//            val handler = Handler()
//            handler.postDelayed(object: Runnable{
//                override fun run() {
//                    loading.dismiss()
//                }
//            },1000)

            fetchQuote()
            colorCycle()
        }

    }
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    private fun fetchQuote() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.quotable.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quoteApi = retrofit.create(QuoteApi::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val quote = quoteApi.getRandomQuote()
                binding.quoteTV.text = "\"${quote.content}\""
                binding.authorTV.text = "~${quote.author}"
            } catch (e: Exception) {
                binding.quoteTV.text = "Failed to fetch quote"
                binding.authorTV.text = null
            }
            binding.mainQuoteView.visibility = android.view.View.VISIBLE
        }
    }
    private fun colorCycle(){

        currentColorIndex = (currentColorIndex + 1) % colorArray.size
        updateBackgroundColor()
    }

    private fun updateBackgroundColor(){

        val rootView: View = findViewById(android.R.id.content)
        rootView.setBackgroundColor(colorArray[currentColorIndex])

    }

}