package com.example.crudrealtimeclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val searchVehicleNumber:String=binding.searchPhone.text.toString()
            if(searchVehicleNumber.isNotEmpty())
            {
                readData(searchVehicleNumber)
            }else
            {
                Toast.makeText(this,"please enter the vehicle Number",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(vehicleNumber:String)
    {
        databaseReference=FirebaseDatabase.getInstance().getReference("vehicleInformation")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists())
            {
                val ownerName=it.child("ownername").value
                val vehicleBrand=it.child("vehiclebrand").value
                val vehicleRTO=it.child("vehiclerto").value
                Toast.makeText(this,"Result found",Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text=ownerName.toString()
                binding.readVehicleBrand.text=vehicleBrand.toString()
                binding.readVehicleRTO.text=vehicleRTO.toString()
            }else
            {
                Toast.makeText(this,"vehicle number not found",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
        }
    }

}