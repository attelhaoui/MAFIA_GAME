package com.mafia.game

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // ربط المحرك (Logic) بالواجهة (UI)
    private val gameLogic = GameLogic()
    private val playersList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // تعريف العناصر من ملف XML
        val etName = findViewById<EditText>(R.id.et_player_name)
        val btnAdd = findViewById<Button>(R.id.btn_add_player)
        val btnStart = findViewById<Button>(R.id.btn_start_game)
        val tvStatus = findViewById<TextView>(R.id.tv_status)

        // منطق إضافة اللاعب
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val error = gameLogic.validateName(name, playersList)
            
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            } else {
                playersList.add(name)
                etName.text.clear()
                tvStatus.text = "عدد اللاعبين: ${playersList.size}"
            }
        }

        // منطق بدء اللعبة
        btnStart.setOnClickListener {
            if (playersList.size < 4) {
                Toast.makeText(this, "يجب إدخال 4 لاعبين على الأقل!", Toast.LENGTH_SHORT).show()
            } else {
                // هنا سنقوم بتوزيع الأدوار لاحقاً
                val roles = gameLogic.distributeRoles(playersList, 2)
                Toast.makeText(this, "تم توزيع الأدوار بنجاح!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}