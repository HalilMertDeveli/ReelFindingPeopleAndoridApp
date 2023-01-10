package com.example.findingpeoplemyvalue

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.findingpeoplemyvalue.databinding.ActivityGoogleMapBinding
import com.google.firebase.auth.FirebaseAuth

class GoogleMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityGoogleMapBinding
    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener
    private  lateinit var authInstance :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        authInstance = FirebaseAuth.getInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.there_point_options,menu)




        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.findingFriendsLocation){
            val intent = Intent(applicationContext,FindFriends::class.java)
            startActivity(intent)

        }
        if (item.itemId == R.id.logOut){
            authInstance.signOut()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        return super.onOptionsItemSelected(item)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object  : LocationListener{
            override fun onLocationChanged(location: Location) {
                var rotation  =LatLng(location.latitude,location.longitude)
                map.addMarker(MarkerOptions().position(rotation).title("Your Location"))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(rotation,15f))
            }

        }

        if (ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,
               arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) ,1)
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty()){
            if (requestCode ==1){
                if (ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)

                }
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}