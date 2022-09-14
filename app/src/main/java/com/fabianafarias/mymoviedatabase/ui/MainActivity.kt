package com.fabianafarias.mymoviedatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fabianafarias.mymoviedatabase.R
import com.fabianafarias.mymoviedatabase.databinding.ActivityMainBinding
import com.fabianafarias.mymoviedatabase.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var movieViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }







}