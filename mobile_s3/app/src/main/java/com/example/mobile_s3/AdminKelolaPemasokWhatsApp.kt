package com.example.mobile_s3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class AdminKelolaPemasokWhatsApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_kelola_pemasok_whats_app)

        // ══════════════════════════════════════════════════════════════════
        // 1. Load Foto Pemasok via Glide (Hero Images dari Desain Figma)
        // ══════════════════════════════════════════════════════════════════
        val ivHero1 = findViewById<ImageView?>(R.id.ivHeroPemasok1)
        val ivHero2 = findViewById<ImageView?>(R.id.ivHeroPemasok2)
        val ivHero3 = findViewById<ImageView?>(R.id.ivHeroPemasok3)

        ivHero1?.let {
            Glide.with(this)
                .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/fnvy5aaq_expires_30_days.png")
                .into(it)
        }

        ivHero2?.let {
            Glide.with(this)
                .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/q131xxtp_expires_30_days.png")
                .into(it)
        }

        ivHero3?.let {
            Glide.with(this)
                .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/DPJjSZtfyd/9vgxrjyl_expires_30_days.png")
                .into(it)
        }

        // ══════════════════════════════════════════════════════════════════
        // 2. Helper Membuka WhatsApp PIC Pemasok
        // ══════════════════════════════════════════════════════════════════
        fun openWhatsApp(phoneNumber: String, supplierName: String, picName: String) {
            val cleanPhone = phoneNumber.replace("+", "").replace("-", "").replace(" ", "").trim()
            val message = "Halo $picName ($supplierName), saya dari Agrim Admin Operasional ingin menindaklanjuti verifikasi komoditas dan kapasitas ekspor."
            val url = "https://api.whatsapp.com/send?phone=$cleanPhone&text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Menghubungi WhatsApp $picName ($phoneNumber)...", Toast.LENGTH_SHORT).show()
            }
        }

        // ══════════════════════════════════════════════════════════════════
        // 3. Tombol Aksi Header & Filter
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.btnTambahPemasok)?.setOnClickListener {
            Toast.makeText(this, "Membuka formulir tambah pemasok baru...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.btnFilterSetting)?.setOnClickListener {
            Toast.makeText(this, "Filter & preferensi pemasok...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.tvUrutkan)?.setOnClickListener {
            Toast.makeText(this, "Urutkan: Prioritas Audit / Kapasitas Ekspor", Toast.LENGTH_SHORT).show()
        }

        // Category Chips
        findViewById<View?>(R.id.chipSemua)?.setOnClickListener {
            Toast.makeText(this, "Menampilkan Semua 48 Pemasok Komoditas", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.chipRempah)?.setOnClickListener {
            Toast.makeText(this, "Filter: Kategori Rempah (18 Pemasok)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.chipKelapa)?.setOnClickListener {
            Toast.makeText(this, "Filter: Kategori Kelapa & Olahan (14 Pemasok)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.chipKopiKakao)?.setOnClickListener {
            Toast.makeText(this, "Filter: Kategori Kopi & Kakao (16 Pemasok)", Toast.LENGTH_SHORT).show()
        }

        // ══════════════════════════════════════════════════════════════════
        // 4. Kartu Pemasok 1: Agrim PTE Ltd
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.btnChatWA1)?.setOnClickListener {
            openWhatsApp("+6281289901234", "Agrim PTE Ltd", "Hendrik Gunawan")
        }
        findViewById<View?>(R.id.btnUbah1)?.setOnClickListener {
            Toast.makeText(this, "Membuka form edit profil Agrim PTE Ltd", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnBookmark1)?.setOnClickListener {
            Toast.makeText(this, "Agrim PTE Ltd tersimpan di bookmark", Toast.LENGTH_SHORT).show()
        }

        // ══════════════════════════════════════════════════════════════════
        // 5. Kartu Pemasok 2: PT Nusantara Agro
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.btnChatWA2)?.setOnClickListener {
            openWhatsApp("+6281908876123", "PT Nusantara Agro", "Siti Rahmawati")
        }
        findViewById<View?>(R.id.btnUbah2)?.setOnClickListener {
            Toast.makeText(this, "Membuka form edit profil PT Nusantara Agro", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnBookmark2)?.setOnClickListener {
            Toast.makeText(this, "PT Nusantara Agro tersimpan di bookmark", Toast.LENGTH_SHORT).show()
        }

        // ══════════════════════════════════════════════════════════════════
        // 6. Kartu Pemasok 3: Ceylon Harvest
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.btnChatWA3)?.setOnClickListener {
            openWhatsApp("+6285210996877", "Ceylon Harvest", "Darmawan Wicaksono")
        }
        findViewById<View?>(R.id.btnUbah3)?.setOnClickListener {
            Toast.makeText(this, "Membuka form edit profil Ceylon Harvest", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnBookmark3)?.setOnClickListener {
            Toast.makeText(this, "Ceylon Harvest tersimpan di bookmark", Toast.LENGTH_SHORT).show()
        }

        // ══════════════════════════════════════════════════════════════════
        // 7. Top App Bar Icons
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.btnAdminSearchTop)?.setOnClickListener {
            findViewById<EditText?>(R.id.etSearchSupplier)?.requestFocus()
        }
        findViewById<View?>(R.id.btnAdminNotifTop)?.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnAdminProfileTop)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }

        // ══════════════════════════════════════════════════════════════════
        // 8. Bottom Navigation Bar
        // ══════════════════════════════════════════════════════════════════
        findViewById<View?>(R.id.navAdminBeranda)?.setOnClickListener {
            Toast.makeText(this, "Anda berada di tab ringkasan", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.navAdminJelajah)?.setOnClickListener {
            Toast.makeText(this, "Direktori Pemasok Aktif", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.navAdminBandingkan)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaMatriksBandingkan::class.java)
            startActivity(intent)
        }
        findViewById<View?>(R.id.navAdminAkun)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }
    }
}
