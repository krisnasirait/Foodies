package com.primetech.foodies.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.primetech.foodies.R
import com.primetech.foodies.databinding.ActivityDetailBinding
import com.primetech.foodies.utils.Constants.Companion.DATA
import com.primetech.foodies.utils.Data

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: Data
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivFull.setOnClickListener {
            var intent = Intent(this, MapActivity::class.java)
            intent.putExtra(DATA,data)
            startActivity(intent)
        }
    }

    private fun initViews() {
        data = intent.getSerializableExtra(DATA) as Data
        setData()
        setMap()
    }

    private fun setData() {
        binding.apply {
            Glide.with(this@DetailActivity).load(data.url)
                .placeholder(R.drawable.img_profile_cover_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(ivEvent)
            tvEventName.text = data.name
            tvDescription.text = data.description
            tvDescriptionLocation.text = data.location
            hours.text = data.hour
        }
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