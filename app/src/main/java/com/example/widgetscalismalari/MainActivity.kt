package com.example.widgetscalismalari

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import java.util.ArrayList
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var galata: RadioButton = findViewById(R.id.radioButtonGalata)
        var konya: RadioButton = findViewById(R.id.radioButtonKonya)
        var fener: RadioButton = findViewById(R.id.radioButtonFener)
        var kotlinCheckBox: CheckBox = findViewById(R.id.checkBoxKotlin)
        var javaCheckBox: CheckBox = findViewById(R.id.checkBoxJava)
        var button: Button = findViewById(R.id.button)
        var switch: Switch = findViewById(R.id.switch1)
        var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var seekBar: SeekBar = findViewById(R.id.seekBar)
        var textViewSeekBar: TextView = findViewById(R.id.textView)
        textViewSeekBar.text = "Puan : ${seekBar.progress.toString()}"
        var ratingBar: RatingBar = findViewById(R.id.ratingBar)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textViewSeekBar.text = "Puan : ${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        konya.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    applicationContext,
                    "Tebrikler :( bişeyide becerdin",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }
        //************Webview**********
        var webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://github.com")

        //************ImageView************
        var imageView: ImageView = findViewById(R.id.imageView)
/*        imageView.setImageResource(R.drawable.kotlincode)*/

        //*********VideoView****************
        var videoView: VideoView = findViewById(R.id.videoView)
        var adres = Uri.parse("android.resource://" + packageName + "/" + R.raw.bruh)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(adres)
        videoView.start()

        //Url ile video gösterme
        /*val videoUrl = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
        val uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()*/


        //********TimePicker********
        var editTextSaat: EditText = findViewById(R.id.editTextSaat)
        editTextSaat.setOnClickListener {
            val calendar = Calendar.getInstance()
            val saat = calendar.get(Calendar.HOUR_OF_DAY)
            val dakika = calendar.get(Calendar.MINUTE)
            val timePicker: TimePickerDialog
            timePicker = TimePickerDialog(
                this@MainActivity,
                TimePickerDialog.OnTimeSetListener { timePicker, i, i1 ->

                    editTextSaat.setText("$i : $i1")

                },
                saat,
                dakika,
                true
            )

            timePicker.setTitle("Saat Seciniz")
            timePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarla", timePicker)
            timePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Iptal", timePicker)

            timePicker.show()
        }
        //*********Date Picker***************
        var editTextDatePicker:EditText = findViewById(R.id.editTextDatePicker)

        editTextDatePicker.setOnClickListener {
            var calendar = Calendar.getInstance()
            var yil = calendar.get(Calendar.YEAR)
            var ay = calendar.get(Calendar.MONTH)
            var gun = calendar.get(Calendar.DAY_OF_MONTH)

            var datePicker : DatePickerDialog

            datePicker = DatePickerDialog(this@MainActivity, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                editTextDatePicker.setText("${yil}/${ay+1}/${gun}")
            }, yil, ay, gun)

            datePicker.setTitle("Lütfen Tarih Saçiniz")
            datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"iptal",datePicker)
            datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Onayla",datePicker)
            datePicker.show()
        }
        //************spinner************
        var spinner:Spinner = findViewById(R.id.spinner)
        var arrayspinner = ArrayList<String>()
        arrayspinner.add("Türkiye")
        arrayspinner.add("Yunanistan")
        arrayspinner.add("İngiltere")
        arrayspinner.add("Japonya")
        arrayspinner.add("Italy Mamamia")
        var veriAdaptoru = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1, arrayspinner)
        spinner.adapter = veriAdaptoru
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext,"Seçilen Ulke : "+arrayspinner[position],Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        button.setOnClickListener {
            Log.e("Debug", "Galatasaray seçildimi = ${galata.isChecked}")
            Log.e("Debug", "Fenerbahçe seçildimi = ${fener.isChecked}")
            Log.e("Debug", "Konyaspor seçildimi = ${konya.isChecked}")
            Log.e("Debug", "Kotlin seçildimi = ${kotlinCheckBox.isChecked}")
            Log.e("Debug", "Java seçildimi = ${javaCheckBox.isChecked}")
            Log.e("Debug", "Star Count = ${ratingBar.rating}")
            Log.e("Debug", "Spinner = ${arrayspinner[spinner.selectedItemPosition]}")
        }
    }
}