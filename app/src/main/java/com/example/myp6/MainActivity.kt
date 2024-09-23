package com.example.myp6

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketapp.konfirmasii
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var tanggalEditText: EditText
    private lateinit var jamEditText: EditText
    private lateinit var tujuanSpinner: Spinner

    // Map tujuan dan harga tiket
    private val hargaTiket = mapOf(
        "Stasiun Gambir" to 150000,
        "Stasiun Pasar Senen" to 120000,
        "Stasiun Bandung" to 200000,
        "Stasiun Yogyakarta (Tugu)" to 250000,
        "Stasiun Surabaya Gubeng" to 300000,
        "Stasiun Solo Balapan" to 220000,
        "Stasiun Semarang Tawang" to 180000,
        "Stasiun Malang" to 280000,
        "Stasiun Cirebon" to 140000,
        "stasiun Purwokerto" to 160000
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the EditText for "Pilih Tanggal Keberangkatan"
        tanggalEditText = findViewById(R.id.tanggal)

        // Disable keyboard for the date field
        tanggalEditText.isFocusable = false
        tanggalEditText.isClickable = true

        // Set an OnClickListener for the date picker
        tanggalEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Find the EditText for "Jam Keberangkatan"
        jamEditText = findViewById(R.id.jam)

        // Disable keyboard for the time field
        jamEditText.isFocusable = false
        jamEditText.isClickable = true

        // Set an OnClickListener for the time picker
        jamEditText.setOnClickListener {
            showTimePickerDialog()
        }

        // Tombol pesan tiket
        val btnRegister: Button = findViewById(R.id.Ptiket)

        // Set listener untuk tombol pesan tiket
        btnRegister.setOnClickListener {
            showConfirmationDialog()
        }

        // Find the Spinner for "Tujuan"
        tujuanSpinner = findViewById(R.id.tujuan)

        // Create a list of destinations for the Spinner
        val tujuanList = listOf(
            "Stasiun Gambir", "Stasiun Pasar Senen", "Stasiun Bandung",
            "Stasiun Yogyakarta (Tugu)", "Stasiun Surabaya Gubeng", "Stasiun Solo Balapan",
            "Stasiun Semarang Tawang", "Stasiun Malang", "Stasiun Cirebon", "stasiun Purwokerto"
        )

        // Create an ArrayAdapter using the destination list and a default spinner layout
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            tujuanList
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        tujuanSpinner.adapter = adapter
    }

    // Function to show the DatePickerDialog
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tanggalEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun showConfirmationDialog() {
        val nama = findViewById<EditText>(R.id.nama)
        val jam = findViewById<EditText>(R.id.jam)
        val tanggal = findViewById<EditText>(R.id.tanggal)
        val tujuan = findViewById<Spinner>(R.id.tujuan)

        // Ambil harga tiket berdasarkan tujuan yang dipilih
        val selectedTujuan = tujuan.selectedItem.toString()
        val harga = hargaTiket[selectedTujuan] ?: 0 // Default to 0 if not found

        // Inflate layout konfirmasi.xml
        val dialogView = LayoutInflater.from(this).inflate(R.layout.konfirmasi, null)

        // Buat AlertDialog
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        // Tampilkan dialog
        val alertDialog = builder.create()
        alertDialog.show()

        // Update tampilan harga di dalam dialog
        val hargaTextView = dialogView.findViewById<TextView>(R.id.price_value)
        hargaTextView.text = "Rp$harga"

        // Tombol batal di dalam dialog
        val btnCancel: Button = dialogView.findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            alertDialog.dismiss() // Tutup dialog jika batal
        }

        // Tombol beli di dalam dialog
        val btnBuy: Button = dialogView.findViewById(R.id.beli)
        btnBuy.setOnClickListener {
            // Buat intent untuk berpindah ke KonfirmasiActivity
            val intent = Intent(this, konfirmasii::class.java)

            // Tambahkan data yang dimasukkan pengguna ke intent
            intent.putExtra("nama", nama.text.toString())
            intent.putExtra("jam", jam.text.toString())
            intent.putExtra("tanggal", tanggal.text.toString())
            intent.putExtra("tujuan", tujuan.selectedItem.toString())
            intent.putExtra("harga", harga)

            // Mulai activity konfirmasi
            startActivity(intent)

            // Tutup dialog setelah menekan tombol beli
            alertDialog.dismiss()
        }

    }

    // Function to show the TimePickerDialog
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Format the time into a human-readable string
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                jamEditText.setText(selectedTime)
            },
            hour,
            minute,
            DateFormat.is24HourFormat(this)  // Set whether the time is in 24-hour format
        )

        timePickerDialog.show()
    }
}

