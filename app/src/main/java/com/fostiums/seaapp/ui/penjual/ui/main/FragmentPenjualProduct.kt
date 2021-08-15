package com.fostiums.seaapp.ui.penjual.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fostiums.seaapp.R
import com.fostiums.seaapp.databinding.FragmentPenjualProductBinding
import com.fostiums.seaapp.models.ProductData
import com.fostiums.seaapp.repository.Penjual
import com.fostiums.seaapp.ui.penjual.adapter.ProductPenjualAdapter


/**
 * A placeholder fragment containing a simple view.
 */
class FragmentPenjualProduct : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPenjualProductBinding? = null
    lateinit var adapter: ProductPenjualAdapter
    var pageNow:Int = 1
    var isLoadingMore:Boolean = false
    lateinit var recyclerView: RecyclerView
    var searchKeyword: String = ""

    var productdata: List<ProductData>? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        _binding = FragmentPenjualProductBinding.inflate(inflater, container, false)
        val root = binding.root


        recyclerView = root.findViewById(R.id.penjual_producr_rv)

        loadList(0,searchKeyword)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    loadMore(searchKeyword)
                }
            }
        })


        return root
    }



    fun loadList(page:Int,search:String) {
//        emptydatapenerimabantuan.visibility = View.GONE
//        progresbarresponden.visibility = View.VISIBLE
        Penjual(context).getAllProduct(page,
            {
                    data ->
                        activity?.runOnUiThread {


                            //progresbarresponden.visibility = View.GONE
                            productdata = data?.data
                            if (productdata?.size == 0) {
                                //emptydatapenerimabantuan.visibility = View.VISIBLE
                            }
                            adapter = ProductPenjualAdapter(context, productdata)
                            recyclerView.layoutManager = LinearLayoutManager(context)
                            recyclerView.adapter = adapter
                        }
            },{error ->
                //dialog errror
                Toast.makeText(
                    context,
                    "Gagal Memuat Responden",
                    Toast.LENGTH_LONG
                ).show()
            }
        )


    }



    fun loadMore(search: String) {
        if (!isLoadingMore) {
            isLoadingMore = true
            //progresbarresponden.visibility = View.VISIBLE
            Penjual(context).getAllProduct(pageNow,
                { data ->
                    activity?.runOnUiThread {
                        if (data != null) {



                            data.data.forEach { data ->
                                productdata?.toMutableList()?.add(data)
                                var panjang = productdata?.size
                                if (panjang != null) {
                                    adapter.notifyItemInserted( panjang - 1)
                                }
                            }
                            adapter.notifyDataSetChanged()
                            pageNow++
                            //progresbarresponden.visibility = View.GONE
                            isLoadingMore = false
                        }
                    }
                }, { error ->
                    //progresbarresponden.visibility = View.GONE
                    Toast.makeText(
                        context,
                        "Load More Error" + error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        }

    }



    fun refreshList(){
        pageNow = 0
        searchKeyword = ""
        loadList(0,searchKeyword)
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }



    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): FragmentPenjualProduct {
            return FragmentPenjualProduct().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}