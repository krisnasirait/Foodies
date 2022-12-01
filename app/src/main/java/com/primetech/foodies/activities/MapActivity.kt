package com.primetech.foodies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.primetech.foodies.R
import com.primetech.foodies.databinding.ActivityMapBinding
import com.primetech.foodies.utils.Constants
import com.primetech.foodies.utils.Data

class MapActivity : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var binding: ActivityMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setMap()
        setOnClickListener()


    }

    private fun setOnClickListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        data = intent.getSerializableExtra(Constants.DATA) as Data
        binding.tvName.text = data.name
    }

    private fun setMap() {
        mapFragment = supportFragmentManager.
        findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setLocationOnMap()
    }

    private fun setLocationOnMap() {
        val originLocation = LatLng(data.lat!!.toDouble(),data.long!!.toDouble())
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(originLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))
    }


}