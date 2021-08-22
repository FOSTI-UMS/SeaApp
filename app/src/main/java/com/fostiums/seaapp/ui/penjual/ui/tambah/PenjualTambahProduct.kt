package com.fostiums.seaapp.ui.penjual.ui.tambah

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.fostiums.seaapp.R
import com.fostiums.seaapp.repository.Penjual
import com.fostiums.seaapp.repository.Product
import com.fostiums.seaapp.ui.penjual.adapter.SpinnerProductCategoryForm
import com.fostiums.seaapp.ui.product.models.SpinnerProductModel
import com.github.dhaval2404.imagepicker.ImagePicker
import java.util.*
import kotlin.collections.ArrayList
import cn.pedant.SweetAlert.SweetAlertDialog





class PenjualTambahProduct : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    lateinit var product_foto1: ImageView
    lateinit var product_foto2: ImageView
    lateinit var product_foto3: ImageView
    lateinit var buttonaddproduct: Button
    lateinit var product_satuan_harga: EditText
    var fotoKe: Int = 1
    var foto1: String = ""
    var foto2: String = ""
    var foto3: String = ""

    lateinit var product_nama: EditText
    lateinit var product_nutrisi: EditText
    lateinit var product_harga: EditText
    var kadaluwarsaForm: String = ""
    var kategoriForm: String = ""
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)


        setContentView(R.layout.activity_penjual_tambah_product)
        product_satuan_harga = findViewById(R.id.product_satuan_harga)
        product_nama = findViewById(R.id.product_nama)
        product_nutrisi = findViewById(R.id.product_nutrisi)
        product_harga = findViewById(R.id.product_harga)

        var catSpinner: Spinner = findViewById(R.id.spinner_kategori)
        buttonaddproduct = findViewById(R.id.buttonaddproduct)
        val categories: MutableList<String> = ArrayList()
        categories.add("Ikan laut")
        categories.add("Ikan Tawar")
        categories.add("Olahan")
        categories.add("Kepiting")
        categories.add("Udang")
        categories.add("Lainya")

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        catSpinner.setAdapter(dataAdapter)

        catSpinner.onItemSelectedListener = this



        val datePicker = findViewById<DatePicker>(R.id.date_Picker)
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            //2021-08-08 00:37:54
            kadaluwarsaForm = "$year-$month-$day 00:00:00"

        }


        product_foto1 = findViewById(R.id.product_foto1)
        product_foto1.setOnClickListener {
            fotoKe = 1
            ImagePicker.with(this)
                .crop()
                .compress(512)
                .maxResultSize(1080, 1080)
                .start()
        }

        product_foto2 = findViewById(R.id.product_foto2)
        product_foto2.setOnClickListener {
            fotoKe = 2
            ImagePicker.with(this)
                .crop()
                .compress(512)
                .maxResultSize(1080, 1080)
                .start()
        }

        product_foto3 = findViewById(R.id.product_foto3)
        product_foto3.setOnClickListener {
            fotoKe = 3
            ImagePicker.with(this)
                .crop()
                .compress(512)
                .maxResultSize(1080, 1080)
                .start()
        }

        buttonaddproduct.setOnClickListener {
            pDialog.show()
            addNew()
        }

    }



    override fun onNothingSelected(parent: AdapterView<*>?) {
        kategoriForm = "Ikan"
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        kategoriForm = text
    }

    fun addNew(){
        Product(this).addNewProduct(
            foto1,
            foto2,
            foto3,
            kategoriForm,
            product_nama.text.toString(),
            product_nutrisi.text.toString(),
            product_harga.text.toString(),
            kadaluwarsaForm,
            product_satuan_harga.text.toString(),
            {
                success ->
                if(success?.error == false) {
                    pDialog.dismiss()
                    SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Berhasil")
                        .setContentText(success?.message)
                        .show()
                }else{
                    pDialog.dismiss()
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Berhasil")
                        .setContentText(success?.message)
                        .show()
                }
            },
            {
                error ->
                    pDialog.dismiss()
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText(error.message)
                        .show()
            }
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == ImagePicker.REQUEST_CODE) {
                val uri: Uri = data?.data!!

                if (fotoKe == 1) {
                    foto1 = uri.path.toString()
                    product_foto1.setImageURI(uri)
                } else if (fotoKe == 2) {
                    foto2 = uri.path.toString()
                    product_foto2.setImageURI(uri)
                } else if (fotoKe == 3) {
                    foto3 = uri.path.toString()
                    product_foto3.setImageURI(uri)
                }
            }


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }


    }

}