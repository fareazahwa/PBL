package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BerandaDirektoriAgrim : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_direktori_agrim)

        // ═══════════════════════════════════════════════════
        // 1. Helper Aman Memuat Gambar via Glide
        // ═══════════════════════════════════════════════════
        fun loadGlideSafe(viewId: Int, url: String) {
            try {
                val v = findViewById<View?>(viewId)
                if (v is ImageView) {
                    Glide.with(this).load(url).into(v)
                }
            } catch (e: Exception) {
                // Jangan crash jika gagal
            }
        }

        // Avatar Joko Tole
        loadGlideSafe(
            R.id.ivBuyerAvatar,
            "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/7i1nuhz2_expires_30_days.png"
        )

        // Gambar Kartu Pemasok Baru Dilihat
        loadGlideSafe(
            R.id.ivBuyerCard1,
            "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/fnvy5aaq_expires_30_days.png"
        )
        loadGlideSafe(
            R.id.ivBuyerCard2,
            "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/q131xxtp_expires_30_days.png"
        )
        loadGlideSafe(
            R.id.ivBuyerCard3,
            "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/9vgxrjyl_expires_30_days.png"
        )

        // ═══════════════════════════════════════════════════
        // 2. Interaksi Header (Avatar, Notifikasi, Scanner)
        // ═══════════════════════════════════════════════════
        val openAkun = View.OnClickListener {
            val intent = Intent(this, AkunBuyerActivity::class.java)
            startActivity(intent)
        }
        findViewById<View?>(R.id.cardBuyerAvatar)?.setOnClickListener(openAkun)
        findViewById<View?>(R.id.ivBuyerAvatar)?.setOnClickListener(openAkun)

        findViewById<View?>(R.id.btnBuyerNotif)?.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.btnBuyerQrScanner)?.setOnClickListener {
            Toast.makeText(this, "Membuka Pemindai QR Sertifikat & Pemasok...", Toast.LENGTH_SHORT).show()
        }

        // ═══════════════════════════════════════════════════
        // 3. Status Keanggotaan & Aksi Cepat
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.btnKelolaMembership)?.setOnClickListener {
            Toast.makeText(this, "Membuka pengaturan paket Business Pro...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.btnRfqBaru)?.setOnClickListener {
            Toast.makeText(this, "Membuka formulir RFQ (Permintaan Penawaran) Baru...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.btnBuyerMetricsStrip)?.setOnClickListener {
            val intent = Intent(this, FilterUrutkanActivity::class.java)
            startActivity(intent)
        }

        findViewById<View?>(R.id.btnLihatKatalog)?.setOnClickListener {
            val intent = Intent(this, FilterUrutkanActivity::class.java)
            startActivity(intent)
        }

        // Klik Kartu Pemasok
        findViewById<View?>(R.id.cardBuyerSupplier1)?.setOnClickListener {
            Toast.makeText(this, "Membuka profil Agrim PTE Ltd", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardBuyerSupplier2)?.setOnClickListener {
            Toast.makeText(this, "Membuka profil Nusantara Agro", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardBuyerSupplier3)?.setOnClickListener {
            Toast.makeText(this, "Membuka profil Ceylon Harvest", Toast.LENGTH_SHORT).show()
        }

        // ═══════════════════════════════════════════════════
        // 4. Bottom Navigation Bar (5 Tabs)
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.navBuyerBeranda)?.setOnClickListener {
            Toast.makeText(this, "Anda sudah berada di Beranda.", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navBuyerJelajah)?.setOnClickListener {
            val intent = Intent(this, FilterUrutkanActivity::class.java)
            startActivity(intent)
        }

        findViewById<View?>(R.id.navBuyerRfq)?.setOnClickListener {
            Toast.makeText(this, "Membuka Daftar Permintaan Penawaran (RFQ)...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navBuyerBandingkan)?.setOnClickListener {
            val intent = Intent(this, BandingkanBuyerActivity::class.java)
            startActivity(intent)
        }

        findViewById<View?>(R.id.navBuyerAkun)?.setOnClickListener {
            val intent = Intent(this, AkunBuyerActivity::class.java)
            startActivity(intent)
        }
    }
}
