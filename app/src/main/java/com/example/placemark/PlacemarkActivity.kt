package com.example.placemark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        info("Placemark Activity started..")

        btnAdd.setOnClickListener() {
            val placemarkTitle = placemarkTitle.text.toString()
            if (placemarkTitle.isNotEmpty()) {
                info("Add button pressed: $placemarkTitle")
            } else {
                toast("Please enter a title..")
            }
        }
    }
}
