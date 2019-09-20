package com.example.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.models.PlacemarkModel
import com.example.placemark.R
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    var placemark = PlacemarkModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        info("Placemark Activity started..")

        btnAdd.setOnClickListener() {
            placemark.title = placemarkTitle.text.toString()
            if (placemark.title.isNotEmpty()) {
                info("Add button pressed: $placemark")
            } else {
                toast("Please enter a title..")
            }
        }
    }
}
