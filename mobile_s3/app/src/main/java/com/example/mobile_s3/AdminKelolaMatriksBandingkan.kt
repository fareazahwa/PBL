package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminKelolaMatriksBandingkan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_kelola_matriks_bandingkan)

        // 1. Tombol Simpan Matriks
        findViewById<View?>(R.id.btnSimpanMatriks)?.setOnClickListener {
            Toast.makeText(this, "Konfigurasi parameter komparasi berhasil disimpan!", Toast.LENGTH_SHORT).show()
        }

        // 2. Tombol Tambah Parameter Baru
        findViewById<View?>(R.id.btnTambahParameterBaru)?.setOnClickListener {
            Toast.makeText(this, "Membuka formulir parameter komparasi baru...", Toast.LENGTH_SHORT).show()
        }

        // 3. Segment Tabs (Kelola Parameter vs Uji Tolok Ukur)
        val tabKelola = findViewById<TextView?>(R.id.tabKelolaParam)
        val tabBenchmark = findViewById<TextView?>(R.id.tabUjiBenchmark)

        tabKelola?.setOnClickListener {
            tabKelola.setBackgroundResource(R.drawable.bg_pill_white)
            tabKelola.setTextColor(getColor(R.color.agrim_text_dark))
            tabBenchmark?.setBackgroundResource(0)
            tabBenchmark?.setTextColor(getColor(R.color.agrim_text_body))
            Toast.makeText(this, "Mode: Kelola Parameter (3 Grup)", Toast.LENGTH_SHORT).show()
        }

        tabBenchmark?.setOnClickListener {
            tabBenchmark.setBackgroundResource(R.drawable.bg_pill_white)
            tabBenchmark.setTextColor(getColor(R.color.agrim_text_dark))
            tabKelola?.setBackgroundResource(0)
            tabKelola?.setTextColor(getColor(R.color.agrim_text_body))
            Toast.makeText(this, "Mode: Uji Tolok Ukur (Benchmark Komparasi)", Toast.LENGTH_SHORT).show()
        }

        // 4. Header Icons
        findViewById<View?>(R.id.btnAdminSearchTop)?.setOnClickListener {
            Toast.makeText(this, "Pencarian parameter komparasi...", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnAdminNotifTop)?.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnAdminProfileTop)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }

        // 5. Bottom Navigation Bar
        findViewById<View?>(R.id.navMatriksBeranda)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaPemasokWhatsApp::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navMatriksJelajah)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaPemasokWhatsApp::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navMatriksBandingkan)?.setOnClickListener {
            Toast.makeText(this, "Anda sudah berada di menu Matriks Bandingkan", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navMatriksAkun)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }
    }
}
