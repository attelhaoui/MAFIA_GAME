package com.mafia.game

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role)

        // جلب البيانات التي سنمررها (الاسم والدور)
        val playerName = intent.getStringExtra("PLAYER_NAME") ?: "لاعب"
        val playerRole = intent.getStringExtra("PLAYER_ROLE") ?: "مواطن"

        val tvName = findViewById<TextView>(R.id.tv_player_name)
        val btnReveal = findViewById<Button>(R.id.btn_reveal)
        val tvRole = findViewById<TextView>(R.id.tv_player_role)
        val btnDone = findViewById<Button>(R.id.btn_done)

        tvName.text = playerName
        
        btnReveal.setOnClickListener {
            // إخفاء الزر وإظهار الدور
            btnReveal.visibility = View.GONE
            tvRole.text = playerRole
            tvRole.visibility = View.VISIBLE
            btnDone.visibility = View.VISIBLE
        }

        btnDone.setOnClickListener {
            finish() // إغلاق الشاشة ليعود الدور للاعب التالي
        }
    }
}