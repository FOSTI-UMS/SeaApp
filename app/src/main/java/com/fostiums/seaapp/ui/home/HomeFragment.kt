package com.fostiums.seaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fostiums.seaapp.R
import com.fostiums.seaapp.databinding.FragmentHomeBinding

import com.fostiums.seaapp.ui.home.adapter.RekomendasiGridViewAdapter

import com.fostiums.seaapp.ui.home.models.KategoriCarouselModel
import com.fostiums.seaapp.ui.home.widgets.ExpandableGridView
import com.fostiums.seaapp.ui.product.ProductPage

import com.jama.carouselview.CarouselView
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)




        val fakeDataPromotion = arrayListOf(
            R.drawable.promosi_contoh,
            R.drawable.promosi_contoh,
            R.drawable.promosi_contoh,
            R.drawable.promosi_contoh,
            R.drawable.promosi_contoh,
        )

        val kategoriProduct = arrayListOf(
            KategoriCarouselModel(R.drawable.kategori_ikan,"Ikan"),
            KategoriCarouselModel(R.drawable.kategori_ikan,"Kerang"),
            KategoriCarouselModel(R.drawable.kategori_ikan,"Rumput Laut"),
            KategoriCarouselModel(R.drawable.kategori_ikan,"Udang"),
            KategoriCarouselModel(R.drawable.kategori_ikan,"Garam")
        )

        initViewPagerCarouselPromotion(fakeDataPromotion)
        initViewPagerCarouselCategory(kategoriProduct)
        initRekomendasiProductGridView()
        return binding.root
    }

    private fun initViewPagerCarouselPromotion(data: ArrayList<Int>){


        val carouselView = binding.root.findViewById<CarouselView>(R.id.carouselViewPromotion)
        carouselView.setPadding(20,0,0,0)
        carouselView.apply {
            size = data.size
            autoPlay = false
            resource = R.layout.center_carousel_item_promotion
            indicatorAnimationType = IndicatorAnimationType.SLIDE

            carouselOffset = OffsetType.START
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView)
                imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, data[position], null)
                )
            }
            show()
        }

    }

    private fun initViewPagerCarouselCategory(data: ArrayList<KategoriCarouselModel>){

        val carouselView = binding.root.findViewById<CarouselView>(R.id.carouselViewCategory)
        carouselView.setPadding(20,0,0,0)
        carouselView.apply {
            size = data.size
            autoPlay = false
            resource = R.layout.center_carousel_item_category
            indicatorAnimationType = IndicatorAnimationType.NONE
            hideIndicator(true)
            carouselOffset = OffsetType.START
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView)
                val textView = view.findViewById<TextView>(R.id.textViewKategoriItemCarousel)
                imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, data[position].image, null))
                textView.text = data[position].kategori

                imageView.setOnClickListener {
                    val gotoProductPage = Intent(context, ProductPage::class.java)
                    gotoProductPage.putExtra("KATEGORI", data[position].kategori)
                    startActivity(gotoProductPage)
                }
            }
            show()
        }

    }

    private fun initRekomendasiProductGridView() {
        val playerNames = arrayOf(
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
            "Gurame",
        )
        val playerImages = intArrayOf(
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
            R.drawable.contohikan,
        )
        val gridView = binding.root.findViewById<ExpandableGridView>(R.id.rekomendasigridview)

        val mainAdapter = context?.let { RekomendasiGridViewAdapter(it, playerNames, playerImages) }


        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                context, "You CLicked " + playerNames[+position],
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}