package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BandingkanBuyerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_bandingkan_buyer)

        // 1. Ekspor PDF
        findViewById<View?>(R.id.btnBuyerEksporPdf)?.setOnClickListener {
            Toast.makeText(this, "Mengunduh Laporan Komparasi Pemasok ke PDF...", Toast.LENGTH_SHORT).show()
        }

        // 2. Bottom Navigation
        findViewById<View?>(R.id.navBeranda3)?.setOnClickListener {
            val intent = Intent(this, BerandaDirektoriAgrim::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navJelajah3)?.setOnClickListener {
            val intent = Intent(this, FilterUrutkanActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navBandingkan3)?.setOnClickListener {
            Toast.makeText(this, "Anda sudah berada di menu Bandingkan Pemasok", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navAkun3)?.setOnClickListener {
            val intent = Intent(this, AkunBuyerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
