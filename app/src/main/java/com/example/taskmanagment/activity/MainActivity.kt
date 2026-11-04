package com.example.taskmanagment.activity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskmanagment.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val prefe by lazy {

        getSharedPreferences("addpre", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (prefe.getBoolean("isFirstlaunch", true).not()) {

            openActivity()

            return
        }

        splashscreen()

    }

    private fun openActivity() {

        startActivity(Intent(this@MainActivity, MainTaskActivity2::class.java))

        finish()
    }

    @SuppressLint("CommitPrefEdits")
    private fun splashscreen() {

        lifecycleScope.launch {

            delay(3000.milliseconds)

            prefe.edit()

                .putBoolean("isFirstlaunch", false)

                .apply()

            openActivity()

        }
    }
}