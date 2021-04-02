package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val listTile = arrayListOf<ListItem>(
            ListItem("Квитанции", "- 40 080,55 ₽ ", R.drawable.ic_bill, ItemAdapter.VIEW_TWO),
            ListItem("Счетчики", "Подайте показания", R.drawable.ic_counter, ItemAdapter.VIEW_TWO),
            ListItem("Рассрочка", "Сл. платеж 25.02.2018",  R.drawable.ic_installment, ItemAdapter.VIEW_TWO),
            ListItem("Страхование", "Полис до 12.01.2019",R.drawable.ic_insurance, ItemAdapter.VIEW_TWO),
            ListItem("Интернет и ТВ", "Баланс 350 ₽",R.drawable.ic_tv, ItemAdapter.VIEW_TWO),
            ListItem("Домофон", "Подключен", R.drawable.ic_homephone, ItemAdapter.VIEW_TWO),
            ListItem("Охрана", "Нет", R.drawable.ic_guard, ItemAdapter.VIEW_ONE),
            ListItem("Контакты УК и служб", "", R.drawable.ic_uk_contacts, ItemAdapter.VIEW_ONE),
            ListItem("Мои заявки", "",R.drawable.ic_request, ItemAdapter.VIEW_ONE),
            ListItem("Памятка жителя А101", "",R.drawable.ic_about, ItemAdapter.VIEW_ONE),
        )
        adapter = ItemAdapter(this , listTile)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = GridLayoutManager(this,2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) = when (position > 5) {
                    true -> ItemAdapter.VIEW_TWO
                    else -> ItemAdapter.VIEW_ONE
                }
            }
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.info1 -> {
                    Toast.makeText(this, "Woop woop!", Toast.LENGTH_SHORT).show()
                    return true
                }
                R.id.home1 -> {
                    onCreateDialog()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }

        fun toastMessage(item: MenuItem) {
            Toast.makeText(this, "Woop woop!", Toast.LENGTH_SHORT).show()
        }

        fun onCreateDialog() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Важная новость")
                .setMessage("Ты пидр")
                .setPositiveButton("Гыгык") { dialog, id ->
                    dialog.cancel()
                }
            builder.show()
        }
    }
}
