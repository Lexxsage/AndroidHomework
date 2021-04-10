package com.example.homework4

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.nio.file.Files.size

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerview)

        binding.recyclerview.addItemDecoration(GridSpace(4, 4, 4, 4))

        val list = arrayListOf(
                PlaceholderItem(
                        "Квитанции",
                        "- 40 080,55 ₽ ",
                        ContextCompat.getDrawable(this, R.drawable.ic_bill),
                        ListTileAdapter.VIEW_ONE,
                        true
                ),
                PlaceholderItem(
                        "Счетчики",
                        "Подайте показания",
                        ContextCompat.getDrawable(this, R.drawable.ic_counter),
                        ListTileAdapter.VIEW_ONE,
                        true
                ),
                PlaceholderItem(
                        "Рассрочка",
                        "Сл. платеж 25.02.2018",
                        ContextCompat.getDrawable(this, R.drawable.ic_installment),
                        ListTileAdapter.VIEW_ONE
                ),
                PlaceholderItem(
                        "Страхование ",
                        "Полис до 12.01.2019",
                        ContextCompat.getDrawable(this, R.drawable.ic_insurance),
                        ListTileAdapter.VIEW_ONE
                ),
                PlaceholderItem(
                        "Интернет и ТВ",
                        "Баланс 350 ₽",
                        ContextCompat.getDrawable(this, R.drawable.ic_tv),
                        ListTileAdapter.VIEW_ONE
                ),
                PlaceholderItem(
                        "Домофон",
                        "Подключен",
                        ContextCompat.getDrawable(this, R.drawable.ic_homephone),
                        ListTileAdapter.VIEW_ONE
                ),
                PlaceholderItem(
                        "Охрана",
                        "Нет",
                        ContextCompat.getDrawable(this, R.drawable.ic_guard),
                        ListTileAdapter.VIEW_TWO
                ),
                PlaceholderItem(
                        "Контакты УК и служб",
                        null,
                        ContextCompat.getDrawable(this, R.drawable.ic_uk_contacts),
                        ListTileAdapter.VIEW_TWO
                ),
                PlaceholderItem(
                        "Мои заявки",
                        null,
                        ContextCompat.getDrawable(this, R.drawable.ic_request),
                        ListTileAdapter.VIEW_TWO
                ),
                PlaceholderItem(
                        "Памятка жителя А101",
                        null,
                        ContextCompat.getDrawable(this, R.drawable.ic_about),
                        ListTileAdapter.VIEW_TWO
                )
        )

        recyclerView.adapter = ListTileAdapter(this, list)

        binding.recyclerview.layoutManager = GridLayoutManager(this, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) = when (position) {
                    in 0..5 -> 1
                    else -> 2
                }
            }
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.info -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Важно")
                            .setMessage("Ты пидр")
                            .setPositiveButton("Гыгык")
                            { dialog, _ -> dialog.cancel() }
                            .show()
                    true
                }
                R.id.home -> {
                    Toast.makeText(
                            this,
                            "Woop!",
                            Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }
}
