package com.example.mobile_s3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var isAdminMode: Boolean = true
    private var selectedScope: String = "Operasional"

    // URL API Backend Laravel namabiz
    // Cek port 8000 dan 8001 otomatis jika salah satu port sedang dipakai
    private val apiUrls = listOf(
        "http://10.0.2.2:8000/api/login",
        "http://10.0.2.2:8001/api/login"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLoginScreen()
        setupBuyerNavigation()
        setupAdminNavigation()
    }

    /**
     * Mengatur interaksi layar Login: toggle mode, input field, dan pengiriman request ke backend.
     */
    private fun setupLoginScreen() {
        val btnModeAdmin = findViewById<TextView>(R.id.btnModeAdmin) ?: return
        val btnModeBuyer = findViewById<TextView>(R.id.btnModeBuyer) ?: return
        val chipScope1 = findViewById<TextView>(R.id.chipScope1)
        val chipScope2 = findViewById<TextView>(R.id.chipScope2)
        val chipScope3 = findViewById<TextView>(R.id.chipScope3)
        val tvEmailLabel = findViewById<TextView>(R.id.tvEmailLabel)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLoginSubmit = findViewById<Button>(R.id.btnLoginSubmit)
        val btnLoginSSO = findViewById<Button>(R.id.btnLoginSSO)

        // Mode Admin
        fun applyAdminMode() {
            isAdminMode = true
            selectedScope = "Operasional"

            btnModeAdmin.setBackgroundResource(R.drawable.bg_pill_dark)
            btnModeAdmin.setTextColor(getColor(R.color.white))

            btnModeBuyer.setBackgroundResource(0)
            btnModeBuyer.setTextColor(getColor(R.color.agrim_text_body))

            tvEmailLabel?.text = "Email Staf Operasional"
            etEmail?.hint = "admin@agrim.com"

            chipScope1?.text = "Operasional"
            chipScope1?.visibility = View.VISIBLE
            chipScope1?.setBackgroundResource(R.drawable.bg_pill_dark)
            chipScope1?.setTextColor(getColor(R.color.white))

            chipScope2?.text = "Auditor Mutu"
            chipScope2?.visibility = View.VISIBLE
            chipScope2?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope2?.setTextColor(getColor(R.color.agrim_text_body))

            chipScope3?.text = "Super Admin"
            chipScope3?.visibility = View.VISIBLE
            chipScope3?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope3?.setTextColor(getColor(R.color.agrim_text_body))

            btnLoginSubmit?.text = "Masuk ke Sesi Admin (Operasional)"
        }

        // Mode Buyer
        fun applyBuyerMode() {
            isAdminMode = false
            selectedScope = "Pembeli B2B"

            btnModeBuyer.setBackgroundResource(R.drawable.bg_pill_dark)
            btnModeBuyer.setTextColor(getColor(R.color.white))

            btnModeAdmin.setBackgroundResource(0)
            btnModeAdmin.setTextColor(getColor(R.color.agrim_text_body))

            tvEmailLabel?.text = "Email Akun Pembeli B2B"
            etEmail?.hint = "free@example.com / premium@example.com"

            chipScope1?.text = "Pembeli B2B"
            chipScope1?.visibility = View.VISIBLE
            chipScope1?.setBackgroundResource(R.drawable.bg_pill_dark)
            chipScope1?.setTextColor(getColor(R.color.white))

            chipScope2?.text = "Importir Global"
            chipScope2?.visibility = View.VISIBLE
            chipScope2?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope2?.setTextColor(getColor(R.color.agrim_text_body))

            chipScope3?.visibility = View.GONE

            btnLoginSubmit?.text = "Masuk ke Direktori Buyer"
        }

        btnModeAdmin.setOnClickListener { applyAdminMode() }
        btnModeBuyer.setOnClickListener { applyBuyerMode() }

        // Chip selection
        chipScope1?.setOnClickListener {
            selectedScope = chipScope1.text.toString()
            chipScope1.setBackgroundResource(R.drawable.bg_pill_dark)
            chipScope1.setTextColor(getColor(R.color.white))
            chipScope2?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope2?.setTextColor(getColor(R.color.agrim_text_body))
            chipScope3?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope3?.setTextColor(getColor(R.color.agrim_text_body))
        }

        chipScope2?.setOnClickListener {
            selectedScope = chipScope2.text.toString()
            chipScope2.setBackgroundResource(R.drawable.bg_pill_dark)
            chipScope2.setTextColor(getColor(R.color.white))
            chipScope1?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope1?.setTextColor(getColor(R.color.agrim_text_body))
            chipScope3?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope3?.setTextColor(getColor(R.color.agrim_text_body))
        }

        chipScope3?.setOnClickListener {
            selectedScope = chipScope3.text.toString()
            chipScope3.setBackgroundResource(R.drawable.bg_pill_dark)
            chipScope3.setTextColor(getColor(R.color.white))
            chipScope1?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope1?.setTextColor(getColor(R.color.agrim_text_body))
            chipScope2?.setBackgroundResource(R.drawable.bg_pill_white)
            chipScope2?.setTextColor(getColor(R.color.agrim_text_body))
        }

        // Tombol Submit Login
        btnLoginSubmit?.setOnClickListener {
            val email = etEmail?.text?.toString()?.trim() ?: ""
            val password = etPassword?.text?.toString()?.trim() ?: ""

            if (email.isEmpty()) {
                etEmail?.error = "Email wajib diisi"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                etPassword?.error = "Kata sandi wajib diisi"
                return@setOnClickListener
            }

            // Nonaktifkan tombol sementara selama loading
            btnLoginSubmit.isEnabled = false
            btnLoginSubmit.text = "Memverifikasi sesi..."

            val mode = if (isAdminMode) "admin" else "buyer"

            // Eksekusi panggilan API ke Backend Laravel namabiz secara asinkron
            lifecycleScope.launch(Dispatchers.IO) {
                val result = performLoginRequest(email, password, mode)

                withContext(Dispatchers.Main) {
                    btnLoginSubmit.isEnabled = true
                    btnLoginSubmit.text = if (isAdminMode) "Masuk ke Sesi Admin (Operasional)" else "Masuk ke Direktori Buyer"

                    if (result.isSuccess) {
                        val role = result.role
                        val userName = result.userName
                        val message = result.message

                        Toast.makeText(this@MainActivity, "$message (Halo, $userName!)", Toast.LENGTH_SHORT).show()

                        if (role == "admin") {
                            val intent = Intent(this@MainActivity, AdminKelolaPemasokWhatsApp::class.java)
                            startActivity(intent)
                        } else {
                            // Masuk ke Layar Beranda Pengguna (Activity terpisah dengan desain Figma)
                            val intent = Intent(this@MainActivity, BerandaDirektoriAgrim::class.java)
                            startActivity(intent)
                        }
                    } else {
                        // Tampilkan pesan kesalahan dari backend Laravel (misal: Akses ditolak atau sandi salah)
                        Toast.makeText(this@MainActivity, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        btnLoginSSO?.setOnClickListener {
            Toast.makeText(this, "Menghubungkan ke SSO Korporat Agrim...", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Mengirimkan HTTP POST ke endpoint Laravel namabiz (/api/login).
     * Otomatis mencoba port 8000 dan port 8001.
     */
    private fun performLoginRequest(email: String, pass: String, mode: String): LoginResponse {
        for (targetUrl in apiUrls) {
            try {
                val url = URL(targetUrl)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.connectTimeout = 3000
                conn.readTimeout = 3000
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                conn.setRequestProperty("Accept", "application/json")
                conn.doOutput = true
                conn.doInput = true

                val requestBody = JSONObject().apply {
                    put("email", email)
                    put("password", pass)
                    put("mode", mode)
                }

                OutputStreamWriter(conn.outputStream).use { writer ->
                    writer.write(requestBody.toString())
                    writer.flush()
                }

                val statusCode = conn.responseCode
                val inputStream = if (statusCode in 200..299) conn.inputStream else conn.errorStream
                val responseText = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }

                val json = try {
                    JSONObject(responseText)
                } catch (e: Exception) {
                    JSONObject()
                }

                val success = json.optBoolean("success", false)
                val message = json.optString("message", "")

                // Jika endpoint ditemukan dan berhasil
                if (success && statusCode in 200..299) {
                    val user = json.optJSONObject("user")
                    val role = user?.optString("role", mode) ?: mode
                    val name = user?.optString("name", email) ?: email
                    return LoginResponse(isSuccess = true, role = role, userName = name, message = message)
                }

                // Jika server merespons validasi bisnis (misal sandi salah atau role ditolak 403)
                if (statusCode in 401..403) {
                    return LoginResponse(isSuccess = false, role = "", userName = "", message = message.ifEmpty { "Autentikasi gagal" })
                }

                // Jika 404 (route belum ada di port ini), coba port berikutnya
                if (statusCode == 404 || message.contains("could not be found", ignoreCase = true)) {
                    continue
                }

                if (message.isNotEmpty()) {
                    return LoginResponse(isSuccess = false, role = "", userName = "", message = message)
                }
            } catch (e: Exception) {
                // Koneksi gagal pada port ini, coba port berikutnya di loop
                continue
            }
        }

        // Jika semua port gagal tersambung, gunakan fallback offline terverifikasi
        return handleOfflineFallback(email, mode)
    }

    /**
     * Fallback simulasi jika backend Laravel sedang tidak aktif (offline mode).
     */
    private fun handleOfflineFallback(email: String, mode: String): LoginResponse {
        if (mode == "admin") {
            if (email.contains("buyer") || email.contains("free") || email.contains("premium")) {
                return LoginResponse(
                    isSuccess = false,
                    role = "",
                    userName = "",
                    message = "Akses ditolak: Akun Buyer tidak memiliki otorisasi Admin Ops."
                )
            }
            return LoginResponse(
                isSuccess = true,
                role = "admin",
                userName = "Admin Ops (Offline)",
                message = "Login Offline Berhasil (Admin Node JKT-01)"
            )
        } else {
            return LoginResponse(
                isSuccess = true,
                role = "buyer",
                userName = "Pembeli B2B (Offline)",
                message = "Login Offline Berhasil"
            )
        }
    }

    /**
     * Data class pembungkus hasil login.
     */
    data class LoginResponse(
        val isSuccess: Boolean,
        val role: String,
        val userName: String,
        val message: String
    )

    /**
     * Navigasi untuk layar Buyer (Beranda, Jelajah, Bandingkan, Akun, Logout).
     */
    private fun setupBuyerNavigation() {
        // Navigasi Tab: Beranda
        val navBerandaViews = listOf(
            findViewById<View?>(R.id.navBeranda1),
            findViewById<View?>(R.id.navBeranda2),
            findViewById<View?>(R.id.navBeranda3),
            findViewById<View?>(R.id.navBeranda4)
        )
        navBerandaViews.forEach { it?.setOnClickListener { showScreen(R.id.berandaScreen) } }

        // Navigasi Tab: Jelajah Direktori
        val navJelajahViews = listOf(
            findViewById<View?>(R.id.navJelajah1),
            findViewById<View?>(R.id.navJelajah2),
            findViewById<View?>(R.id.navJelajah3),
            findViewById<View?>(R.id.navJelajah4)
        )
        navJelajahViews.forEach { it?.setOnClickListener { showScreen(R.id.jelajahScreen) } }

        // Navigasi Tab: Bandingkan
        val navBandingkanViews = listOf(
            findViewById<View?>(R.id.navBandingkan1),
            findViewById<View?>(R.id.navBandingkan2),
            findViewById<View?>(R.id.navBandingkan3),
            findViewById<View?>(R.id.navBandingkan4)
        )
        navBandingkanViews.forEach { it?.setOnClickListener { showScreen(R.id.bandingkanScreen) } }

        // Navigasi Tab: Akun
        val navAkunViews = listOf(
            findViewById<View?>(R.id.navAkun1),
            findViewById<View?>(R.id.navAkun2),
            findViewById<View?>(R.id.navAkun3),
            findViewById<View?>(R.id.navAkun4)
        )
        navAkunViews.forEach { it?.setOnClickListener { showScreen(R.id.akunScreen) } }

        // Navigasi Tab: RFQ
        findViewById<View?>(R.id.navRfq1)?.setOnClickListener {
            Toast.makeText(this, "Fitur RFQ (Permintaan Penawaran) Baru", Toast.LENGTH_SHORT).show()
        }

        // Interaksi di screen_beranda (Figma Buyer Terbaru)
        findViewById<View?>(R.id.btnBuyerNotifBeranda)?.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnBuyerQrScannerBeranda)?.setOnClickListener {
            Toast.makeText(this, "Membuka Pemindai QR...", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnKelolaMembershipBeranda)?.setOnClickListener {
            Toast.makeText(this, "Pengaturan Keanggotaan Business Pro", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnRfqBaruBeranda)?.setOnClickListener {
            Toast.makeText(this, "Formulir RFQ Baru dibuka", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnBuyerMetricsStripBeranda)?.setOnClickListener {
            showScreen(R.id.jelajahScreen)
        }
        findViewById<View?>(R.id.btnLihatKatalogBeranda)?.setOnClickListener {
            showScreen(R.id.jelajahScreen)
        }
        findViewById<View?>(R.id.cardBuyerSupplier1Beranda)?.setOnClickListener {
            showScreen(R.id.detailScreen)
        }
        findViewById<View?>(R.id.cardBuyerSupplier2Beranda)?.setOnClickListener {
            showScreen(R.id.detailScreen)
        }
        findViewById<View?>(R.id.cardBuyerSupplier3Beranda)?.setOnClickListener {
            showScreen(R.id.detailScreen)
        }

        // Navigasi Tab: RFQ 2 (di screen_jelajah)
        findViewById<View?>(R.id.navRfq2)?.setOnClickListener {
            Toast.makeText(this, "Fitur RFQ (Permintaan Penawaran) Baru", Toast.LENGTH_SHORT).show()
        }

        // Bookmark di screen_jelajah
        findViewById<View?>(R.id.btnBookmarkFilterJelajah)?.setOnClickListener {
            Toast.makeText(this, "Daftar Komoditas Tersimpan di Bookmark", Toast.LENGTH_SHORT).show()
        }

        // Tombol logout di screen_akun
        findViewById<Button>(R.id.btnKeluarSesi)?.setOnClickListener {
            Toast.makeText(this, "Anda telah keluar dari sesi.", Toast.LENGTH_SHORT).show()
            showScreen(R.id.loginScreen)
        }

        // Tombol kembali di screen_detail_pemasok
        findViewById<TextView>(R.id.btnBackDetail)?.setOnClickListener {
            showScreen(R.id.jelajahScreen)
        }
    }

    /**
     * Navigasi untuk layar Admin (Beranda Admin Ops & Kelola Pemasok via WhatsApp).
     */
    private fun setupAdminNavigation() {
        fun openWhatsApp(phoneNumber: String, message: String) {
            val cleanPhone = phoneNumber.replace("+", "").replace("-", "").replace(" ", "").trim()
            val url = "https://api.whatsapp.com/send?phone=$cleanPhone&text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Membuka WhatsApp ke $phoneNumber...", Toast.LENGTH_SHORT).show()
            }
        }

        // 1. WhatsApp Chat Buttons untuk PIC Pemasok
        findViewById<View?>(R.id.btnChatWA1)?.setOnClickListener {
            openWhatsApp(
                "+6281289901234",
                "Halo Pak Hendrik Gunawan (Agrim PTE Ltd), saya Admin Agrim Ops ingin mengonfirmasi kapasitas ekspor dan audit sertifikasi."
            )
        }

        findViewById<View?>(R.id.btnChatWA2)?.setOnClickListener {
            openWhatsApp(
                "+6281908876123",
                "Halo Bu Siti Rahmawati (PT Nusantara Agro), saya Admin Agrim Ops ingin verifikasi kelengkapan dokumen audit komoditas."
            )
        }

        findViewById<View?>(R.id.btnChatWA3)?.setOnClickListener {
            openWhatsApp(
                "+6285210996877",
                "Halo Pak Darmawan Wicaksono (Ceylon Harvest), saya Admin Agrim Ops ingin verifikasi status audit komoditas."
            )
        }

        // 2. Tombol Aksi Admin Tambah & Ubah Pemasok
        findViewById<View?>(R.id.btnTambahPemasok)?.setOnClickListener {
            Toast.makeText(this, "Formulir Pendaftaran Pemasok Baru dibuka", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnUbahPemasok1)?.setOnClickListener {
            Toast.makeText(this, "Edit data profil Agrim PTE Ltd", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnUbahPemasok2)?.setOnClickListener {
            Toast.makeText(this, "Edit data profil PT Nusantara Agro", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnUbahPemasok3)?.setOnClickListener {
            Toast.makeText(this, "Edit data profil Ceylon Harvest", Toast.LENGTH_SHORT).show()
        }

        // 3. Header Icons
        findViewById<View?>(R.id.btnAdminNotifTop)?.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi operasional baru", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnAdminProfileTop)?.setOnClickListener {
            Toast.makeText(this, "Admin Operasional Agrim Aktif", Toast.LENGTH_SHORT).show()
        }

        // 4. Bottom Navigation di screen_admin_kelola_pemasok (Beranda Admin)
        findViewById<View?>(R.id.navAdminKelola)?.setOnClickListener {
            showScreen(R.id.adminKelolaScreen)
        }
        findViewById<View?>(R.id.navAdminMatriks)?.setOnClickListener {
            showScreen(R.id.adminMatriksScreen)
        }
        findViewById<View?>(R.id.navAdminBuyerMode)?.setOnClickListener {
            showScreen(R.id.berandaScreen)
            Toast.makeText(this, "Beralih ke Pratinjau Tampilan Buyer", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.navAdminAkun)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }

        // 5. Tombol Aksi & Tab di screen_admin_matriks_bandingkan
        findViewById<View?>(R.id.btnSimpanMatriks)?.setOnClickListener {
            Toast.makeText(this, "Perubahan parameter matriks perbandingan berhasil disimpan!", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.btnTambahParameterBaru)?.setOnClickListener {
            Toast.makeText(this, "Formulir Parameter Baru dibuka", Toast.LENGTH_SHORT).show()
        }
        val tabKelola = findViewById<TextView?>(R.id.tabKelolaParam)
        val tabBenchmark = findViewById<TextView?>(R.id.tabUjiBenchmark)
        tabKelola?.setOnClickListener {
            tabKelola.setBackgroundResource(R.drawable.bg_pill_white)
            tabKelola.setTextColor(getColor(R.color.agrim_text_dark))
            tabBenchmark?.setBackgroundResource(0)
            tabBenchmark?.setTextColor(getColor(R.color.agrim_text_body))
            Toast.makeText(this, "Mode: Kelola Parameter", Toast.LENGTH_SHORT).show()
        }
        tabBenchmark?.setOnClickListener {
            tabBenchmark.setBackgroundResource(R.drawable.bg_pill_white)
            tabBenchmark.setTextColor(getColor(R.color.agrim_text_dark))
            tabKelola?.setBackgroundResource(0)
            tabKelola?.setTextColor(getColor(R.color.agrim_text_body))
            Toast.makeText(this, "Mode: Uji Tolok Ukur (Benchmark)", Toast.LENGTH_SHORT).show()
        }

        // 6. Bottom Navigation di screen_admin_matriks_bandingkan
        findViewById<View?>(R.id.navAdminMatriksHome)?.setOnClickListener {
            showScreen(R.id.adminKelolaScreen)
        }
        findViewById<View?>(R.id.navAdminMatriksAudit)?.setOnClickListener {
            showScreen(R.id.adminMatriksScreen)
        }
        findViewById<View?>(R.id.navAdminMatriksBuyerMode)?.setOnClickListener {
            showScreen(R.id.berandaScreen)
            Toast.makeText(this, "Beralih ke Pratinjau Tampilan Buyer", Toast.LENGTH_SHORT).show()
        }
        findViewById<View?>(R.id.navAdminMatriksAkun)?.setOnClickListener {
            val intent = Intent(this, AdminKelolaStafPengguna::class.java)
            startActivity(intent)
        }
    }

    /**
     * Utilitas untuk beralih tampilan antar layar.
     */
    private fun showScreen(screenId: Int) {
        val screens = listOf(
            R.id.loginScreen,
            R.id.berandaScreen,
            R.id.jelajahScreen,
            R.id.detailScreen,
            R.id.bandingkanScreen,
            R.id.akunScreen,
            R.id.adminKelolaScreen,
            R.id.adminMatriksScreen
        )

        for (id in screens) {
            findViewById<View?>(id)?.visibility = if (id == screenId) View.VISIBLE else View.GONE
        }
    }
}
