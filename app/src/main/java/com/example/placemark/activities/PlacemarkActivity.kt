package com.example.placemark.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.placemark.main.MainApp
import com.example.placemark.models.PlacemarkModel
import com.example.placemark.R
import com.example.placemark.helpers.readImage
import com.example.placemark.helpers.readImageFromPath
import com.example.placemark.helpers.showImagePicker
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    var placemark = PlacemarkModel()
    lateinit var app : MainApp

    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        var updating = false
        if (intent.hasExtra("placemark_edit")) {
            placemark = intent.extras?.getParcelable<PlacemarkModel>("placemark_edit")!!
            placemarkTitle.setText(placemark.title)
            placemarkDescription.setText(placemark.description)
            placemarkImage.setImageBitmap((readImageFromPath(this, placemark.image)))

            updating = true
            btnAdd.text = getString(R.string.button_savePlacemark)
            chooseImage.setText(R.string.change_placemark_image)
        }

        btnAdd.setOnClickListener() {
            placemark.title = placemarkTitle.text.toString()
            placemark.description = placemarkDescription.text.toString()

            if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
                if (updating) {
                    app.placemarks.update(placemark.copy())
                } else {
                    app.placemarks.create(placemark.copy())
                }

                info(getString(R.string.success_addPlacemark))
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast(getString(R.string.error_requireTitleDescription))
            }
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> startActivityForResult<PlacemarkListActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    placemark.image = data.getData().toString()
                    placemarkImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_placemark_image)
                }
            }
        }
    }
}
