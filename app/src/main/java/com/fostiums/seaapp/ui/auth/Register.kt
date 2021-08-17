package com.fostiums.seaapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.fostiums.seaapp.R
import com.jama.carouselview.CarouselView
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType

class Register : AppCompatActivity() {

    private val image_item = arrayListOf(
        R.drawable.cart_women,
        R.drawable.delivery_man,
        R.drawable.fish_monger
    )

    private val text_item= arrayListOf(
        R.string.reg_header_cart_women,
        R.string.reg_header_delivery_man,
        R.string.reg_header_fish_monger,
        R.string.reg_text_cart_women,
        R.string.reg_text_delivery_man,
        R.string.reg_text_fish_monger
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val carouselView = findViewById<CarouselView>(R.id.image_slide)

        carouselView.apply {
            size = image_item.size
            resource = R.layout.register_carousel_item
            autoPlay = true
            indicatorAnimationType = IndicatorAnimationType.SCALE
            carouselOffset = OffsetType.START
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView2)
                val textViewHeader = view.findViewById<TextView>(R.id.textViewHeader)
                val textViewParagraph = view.findViewById<TextView>(R.id.textViewParagraph)

                imageView.setImageDrawable(resources.getDrawable(image_item[position]))
                textViewHeader.text = resources.getText(text_item[position])
                textViewParagraph.text = resources.getText(text_item[position+3])
            }
            show()
        }
    }
}