package com.example.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placemark.main.MainApp
import com.example.placemark.models.PlacemarkModel
import com.example.placemark.R
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    var placemark = PlacemarkModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            placemark.title = placemarkTitle.text.toString()
            placemark.description = placemarkDescription.text.toString()

            if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
                app.placemarks.add(placemark.copy())

                info("Add button pressed")
                for (i in app.placemarks.indices) {
                    info("Placemark[$i]: ${app.placemarks[i]}")
                }

                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please enter a title and description..")
            }
        }
    }
}
