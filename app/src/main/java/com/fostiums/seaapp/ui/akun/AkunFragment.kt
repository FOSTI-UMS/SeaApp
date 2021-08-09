package com.fostiums.seaapp.ui.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fostiums.seaapp.R
import com.fostiums.seaapp.databinding.FragmentAkunBinding
import com.fostiums.seaapp.helper.TinyDB
import com.fostiums.seaapp.models.UserData
import com.fostiums.seaapp.ui.auth.Login
import com.fostiums.seaapp.ui.penjual.PenjualActivity

class AkunFragment : Fragment() {
    lateinit var tinyDB : TinyDB
    private lateinit var notificationsViewModel: AkunViewModel
    private var _binding: FragmentAkunBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notificationsViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        tinyDB = TinyDB(context)

        //button action
        var btnPusatPenjual: TextView = root.findViewById(R.id.akun_pusat_penjual)
        btnPusatPenjual.setOnClickListener {
            val penjualIntent = Intent(context, PenjualActivity::class.java)
            startActivity(penjualIntent)
        }

        var btnLogout: TextView = root.findViewById(R.id.btn_akun_logout)
        btnLogout.setOnClickListener {
            tinyDB.clear()
            val toLoginPage = Intent(context, Login::class.java)
            startActivity(toLoginPage)
        }

        setUserData()

        return root
    }

    fun setUserData(){
        val username: TextView = binding.root.findViewById(R.id.textViewAkunUsername)
        if (tinyDB.getObject("USER_DATA", UserData::class.java) != null) {
            var user = tinyDB.getObject("USER_DATA", UserData::class.java)
            username.text = user.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setUserData()
    }
}