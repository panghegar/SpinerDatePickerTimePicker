package com.example.ticketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myp6.R

class konfirmasii : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.berhasil)

        // Ambil TextView dari activity_confirmation.xml
        val confirmNama = findViewById<TextView>(R.id.confirmNama)
        val confirmJam = findViewById<TextView>(R.id.confirmJam)
        val confirmTanggal = findViewById<TextView>(R.id.confirmTanggal)
        val confirmTujuan = findViewById<TextView>(R.id.confirmTujuan)

        // Ambil data dari intent
        val nama = intent.getStringExtra("nama")
        val jam = intent.getStringExtra("jam")
        val tanggal = intent.getStringExtra("tanggal")
        val tujuan = intent.getStringExtra("tujuan")

        // Set data ke TextView
        confirmNama.text = "Nama: $nama"
        confirmJam.text = "Jam: $jam"
        confirmTanggal.text = "Tanggal: $tanggal"
        confirmTujuan.text = "Tujuan: $tujuan"
    }
}
