package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AkunBuyerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_akun)

        // 1. Tombol Keluar dari Sesi Kerja (Logout) di dalam halaman Akun
        findViewById<View?>(R.id.btnKeluarSesi)?.setOnClickListener {
            Toast.makeText(this, "Berhasil keluar dari sesi buyer.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // 2. Bottom Navigation
        findViewById<View?>(R.id.navBeranda4)?.setOnClickListener {
            val intent = Intent(this, BerandaDirektoriAgrim::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navJelajah4)?.setOnClickListener {
            val intent = Intent(this, FilterUrutkanActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navBandingkan4)?.setOnClickListener {
            val intent = Intent(this, BandingkanBuyerActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navAkun4)?.setOnClickListener {
            Toast.makeText(this, "Anda sudah berada di halaman Akun", Toast.LENGTH_SHORT).show()
        }
    }
}

