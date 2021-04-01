package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.homework4.databinding.ActivityMainBinding
import com.example.homework4.PlaceHolderContent

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        adapter = ItemAdapter()
        binding.list.adapter = adapter

        val toolbar:Toolbar = findViewById(R.id.toolbar);
        val infoShow: ImageView = toolbar.findViewById(R.id.info)
        infoShow.setOnClickListener{
            val dialog = Dialog()
            val manager = supportFragmentManager
            dialog.show(manager, "Dialog")
        }

        val homeShow: ImageView = toolbar.findViewById(R.id.home)
        homeShow.setOnClickListener{
            Toast.makeText(this, "Woop woop!", Toast.LENGTH_SHORT).show()
        }
    }
}
