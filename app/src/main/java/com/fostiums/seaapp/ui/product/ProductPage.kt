package com.fostiums.seaapp.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner

import com.fostiums.seaapp.R
import com.fostiums.seaapp.repository.Product
import com.fostiums.seaapp.ui.home.widgets.ExpandableGridView
import com.fostiums.seaapp.ui.product.adapter.ProductGridAdapter
import com.fostiums.seaapp.ui.product.adapter.SpinnerProductCategory
import com.fostiums.seaapp.ui.product.models.SpinnerProductModel

class ProductPage : AppCompatActivity() {
    val TAG = "PRODUCT PAGE"
    lateinit var loadingProduct: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        loadingProduct = findViewById(R.id.progressBarProductPage)
        val productIntent = intent

        initSpinnerKategori()
        initGridProduct()

    }


    fun initSpinnerKategori(){
        var modelList: List<SpinnerProductModel> = arrayListOf(
            SpinnerProductModel("Ikan Laut"),
            SpinnerProductModel("Ikan Air Tawar"),
            SpinnerProductModel("Ikan Air Tawar"),
            SpinnerProductModel("Ikan Air Tawar"),
            SpinnerProductModel("Ikan Air Tawar"),
            SpinnerProductModel("Ikan Air Tawar"),
        )


        val catSpinner: Spinner = findViewById(R.id.spinnerCategoryProduct)
        val customDropDownAdapter = SpinnerProductCategory(this, modelList)
        catSpinner.adapter = customDropDownAdapter
        catSpinner.dropDownVerticalOffset = 60
    }

    fun initGridProduct() {
        val gridView = findViewById<ExpandableGridView>(R.id.productgridview)
        loadingProduct.visibility = View.VISIBLE
        Product(this).getAllProduct(0,
            { data ->

                runOnUiThread {
                    if (data != null) {
                        val mainAdapter = ProductGridAdapter(this,data.data)
                        gridView.adapter = mainAdapter
                    }
                    loadingProduct.visibility = View.GONE
                }

            }
        ) { error ->
            Log.e(TAG, "initGridProduct: ", error)
            loadingProduct.visibility = View.GONE

        }

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->


        }
    }
}