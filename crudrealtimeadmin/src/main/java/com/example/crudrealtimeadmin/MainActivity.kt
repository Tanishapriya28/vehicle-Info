package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainUpload.setOnClickListener {
            val intent=Intent(this,upload::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainUpdate.setOnClickListener{
            val intent=Intent(this,updateActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainDelete.setOnClickListener{
            val intent=Intent(this,DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}