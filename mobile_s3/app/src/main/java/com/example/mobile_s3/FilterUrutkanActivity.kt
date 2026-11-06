package com.example.mobile_s3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FilterUrutkanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_urutkan)

        // ═══════════════════════════════════════════════════
        // 1. Header & Bookmark
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.btnBookmarkFilter)?.setOnClickListener {
            Toast.makeText(this, "Daftar Komoditas Tersimpan di Bookmark", Toast.LENGTH_SHORT).show()
        }

        // ═══════════════════════════════════════════════════
        // 2. Filter Chips Interaksi
        // ═══════════════════════════════════════════════════
        val chipSemua = findViewById<TextView?>(R.id.chipFilterSemua)
        val chipLampung = findViewById<TextView?>(R.id.chipFilterLampung)
        val chipDirect = findViewById<TextView?>(R.id.chipFilterDirectFarm)
        val chipKopi = findViewById<TextView?>(R.id.chipFilterKopi)

        fun selectChip(selected: TextView?) {
            val chips = listOf(chipSemua, chipLampung, chipDirect, chipKopi)
            for (chip in chips) {
                if (chip == selected) {
                    chip?.setBackgroundResource(R.drawable.bg_chip_pill_dark)
                    chip?.setTextColor(getColor(R.color.white))
                } else {
                    chip?.setBackgroundResource(R.drawable.bg_chip_pill_white)
                    chip?.setTextColor(getColor(R.color.agrim_text_black))
                }
            }
        }

        chipSemua?.setOnClickListener {
            selectChip(chipSemua)
            Toast.makeText(this, "Menampilkan Semua 48 Komoditas Rempah", Toast.LENGTH_SHORT).show()
        }
        chipLampung?.setOnClickListener {
            selectChip(chipLampung)
            Toast.makeText(this, "Filter: Asal Lampung", Toast.LENGTH_SHORT).show()
        }
        chipDirect?.setOnClickListener {
            selectChip(chipDirect)
            Toast.makeText(this, "Filter: Kategori Direct Farm", Toast.LENGTH_SHORT).show()
        }
        chipKopi?.setOnClickListener {
            selectChip(chipKopi)
            Toast.makeText(this, "Filter: Kopi & Kakao", Toast.LENGTH_SHORT).show()
        }

        // ═══════════════════════════════════════════════════
        // 3. Klik Kartu Komoditas
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.cardKomoditas1)?.setOnClickListener {
            Toast.makeText(this, "Lampung Black Pepper Grade ASTA (FOB $6,450/MT)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardKomoditas2)?.setOnClickListener {
            Toast.makeText(this, "Arabica Coffee Screen 12 Clean (FOB $4,200/MT)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardKomoditas3)?.setOnClickListener {
            Toast.makeText(this, "Robusta Coffee Screen 18 Clean (FOB $3,820/MT)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardKomoditas4)?.setOnClickListener {
            Toast.makeText(this, "Arabica Coffee 8 Raw (FOB $3,670/MT)", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.cardKomoditas5)?.setOnClickListener {
            Toast.makeText(this, "Arabica Coffe 12 Clean (FOB $3,820/MT)", Toast.LENGTH_SHORT).show()
        }

        // ═══════════════════════════════════════════════════
        // 4. Bottom Navigation Bar (5 Tabs)
        // ═══════════════════════════════════════════════════
        findViewById<View?>(R.id.navFilterBeranda)?.setOnClickListener {
            val intent = Intent(this, BerandaDirektoriAgrim::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navFilterJelajah)?.setOnClickListener {
            Toast.makeText(this, "Anda berada di katalog komoditas & filter.", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navFilterRfq)?.setOnClickListener {
            Toast.makeText(this, "Membuka Menu RFQ (Permintaan Penawaran)...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View?>(R.id.navFilterBandingkan)?.setOnClickListener {
            val intent = Intent(this, BandingkanBuyerActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.navFilterAkun)?.setOnClickListener {
            val intent = Intent(this, AkunBuyerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
