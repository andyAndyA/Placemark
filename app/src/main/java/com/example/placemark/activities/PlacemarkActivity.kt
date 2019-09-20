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

    val placemarks = ArrayList<PlacemarkModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        info("Placemark Activity started..")

        btnAdd.setOnClickListener() {
            val placemark = PlacemarkModel()
            placemark.title = placemarkTitle.text.toString()
            placemark.description = placemarkDescription.text.toString()

            if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
                placemarks.add(placemark)

                info("Add button pressed: $placemarks")
            } else {
                toast("Please enter a title and description..")
            }
        }
    }
}
