package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AdminKelolaStafPengguna : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_kelola_staf_pengguna)

        // ═══════════════════════════════════════════════════
        // 1. Helper Aman Memuat Gambar via Glide (Tanpa Crash)
        // ═══════════════════════════════════════════════════
        fun loadGlideSafe(viewId: Int, url: String) {
            try {
                val v = findViewById<View?>(viewId)
                if (v is ImageView) {
                    Glide.with(this).load(url).into(v)
                }
            } catch (e: Exception) {
                // Ignore jika view tidak kompatibel
            }
        }

        loadGlideSafe(R.id.rwg155ojoele, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/lxnbq587_expires_30_days.png")
        loadGlideSafe(R.id.r011vuv3p09kvh, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/uj682bbp_expires_30_days.png")
        loadGlideSafe(R.id.rj76c6d9wcf, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/i0ozndie_expires_30_days.png")
        loadGlideSafe(R.id.rzbr863feljk, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/j1cv7c2s_expires_30_days.png")
        loadGlideSafe(R.id.rytptnundn3q, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/y4n5bl35_expires_30_days.png")
        loadGlideSafe(R.id.rgil4m6vvjqm, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/9cmdkc6q_expires_30_days.png")
        loadGlideSafe(R.id.rpv7ajb8v3j, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/xfcouukk_expires_30_days.png")
        loadGlideSafe(R.id.r12rx9n1u15eh, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/3d8pofbb_expires_30_days.png")
        loadGlideSafe(R.id.rah0amu0d8ye, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/k7figug1_expires_30_days.png")
        loadGlideSafe(R.id.rfvne348e5, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/vgqbb60w_expires_30_days.png")
        loadGlideSafe(R.id.r4twi2x7u9f6, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/s60guoen_expires_30_days.png")
        loadGlideSafe(R.id.raoyql4rghsb, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/1yj6ml5c_expires_30_days.png")
        loadGlideSafe(R.id.ro6kfup21gw, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/iob30yxu_expires_30_days.png")
        loadGlideSafe(R.id.rr11j659d1s, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/m2khr9al_expires_30_days.png")
        loadGlideSafe(R.id.rm5qn8ahsu2, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/2l25dnh7_expires_30_days.png")
        loadGlideSafe(R.id.rzihiq5wuo5, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/3hgvuotq_expires_30_days.png")
        loadGlideSafe(R.id.r9x9476mmx7r, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/93z4qmu4_expires_30_days.png")
        loadGlideSafe(R.id.rj8agduz7w6o, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/6nk918np_expires_30_days.png")
        loadGlideSafe(R.id.r4b8xsdhz66c, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/ptedr1ok_expires_30_days.png")
        loadGlideSafe(R.id.r17vbwhg8xrl, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/7f3forj5_expires_30_days.png")
        loadGlideSafe(R.id.r84jdabv9oaa, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/fbhvwrx3_expires_30_days.png")
        loadGlideSafe(R.id.r0sblrg14e14r, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/qishb3ap_expires_30_days.png")
        loadGlideSafe(R.id.rh61gq6bfurp, "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/0vy22y6a_expires_30_days.png")

        // ═══════════════════════════════════════════════════
        // 2. Tombol Aksi Staf & Logout
        // ═══════════════════════════════════════════════════
        val button1: View? = findViewById(R.id.rxbcrh7w598l)
        button1?.setOnClickListener {
            Toast.makeText(this, "Membuka formulir pendaftaran staf operasional baru...", Toast.LENGTH_SHORT).show()
        }

        val button2: View? = findViewById(R.id.rsursd7f2he)
        button2?.setOnClickListener {
            Toast.makeText(this, "Keluar dari sesi Admin...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // ═══════════════════════════════════════════════════
        // 3. Bottom Navigation Bar
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.navStafBeranda)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaPemasokWhatsApp::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navStafJelajah)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaPemasokWhatsApp::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navStafBandingkan)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaMatriksBandingkan::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navStafAkun)?.setOnClickListener {
            Toast.makeText(this, "Anda sudah berada di menu Akun Staf", Toast.LENGTH_SHORT).show()
        }
    }
}
