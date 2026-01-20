package com.sankranthivinod.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sankranthivinod.newsapp.ui.NewsScreen
import com.sankranthivinod.newsapp.ui.NewsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsScreen(viewModel = viewModel)
        }
    }
}