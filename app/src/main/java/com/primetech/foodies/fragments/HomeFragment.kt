package com.primetech.foodies.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.primetech.foodies.activities.ListActivity
import com.primetech.foodies.databinding.FragmentHomeBinding
import com.primetech.foodies.utils.Constants.Companion.BARS
import com.primetech.foodies.utils.Constants.Companion.CAFE
import com.primetech.foodies.utils.Constants.Companion.LIST_TYPE
import com.primetech.foodies.utils.Constants.Companion.RESTAURENT


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setClickListeners()

        return binding.root
    }

    private fun setClickListeners() {
        binding.apply {
            cvResturent.setOnClickListener {
               getDataList(RESTAURENT)
            }
            cvCafay.setOnClickListener {
                getDataList(CAFE)
            }
            cvBars.setOnClickListener {
                getDataList(BARS)
            }
        }
    }

    private fun getDataList(type: String){
        var intent = Intent(requireActivity(),ListActivity::class.java)
        intent.putExtra(LIST_TYPE, type)
        startActivity(intent)
    }

}