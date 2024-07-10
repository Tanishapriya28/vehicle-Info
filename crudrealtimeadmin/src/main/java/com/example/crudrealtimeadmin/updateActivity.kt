package com.example.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class updateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateButton.setOnClickListener {
            val referencevehicleNumber=binding.referencevehiclenumber.text.toString()
            val ownerName=binding.updateName.text.toString()
            val vehicleBrand=binding.updateVehicleBrand.text.toString()
            val vehicleRTO=binding.updateVehicleRTO.text.toString()

            updatedata(referencevehicleNumber,ownerName,vehicleBrand,vehicleRTO)
        }
    }
    private fun updatedata(vehicleNumber: String,ownerName:String,vehicleBrand:String,vehicleRTO:String)
    {
        databaseReference=FirebaseDatabase.getInstance().getReference("vehicleInformation")
        val vehicleData= mapOf<String,String>("ownername" to ownerName,"vehiclebrand" to vehicleBrand,"vehiclerto" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.referencevehiclenumber.text.clear()
            binding.updateName.text.clear()
            binding.updateVehicleBrand.text.clear()
            binding.updateVehicleRTO.text.clear()
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"unable to update",Toast.LENGTH_SHORT).show()
        }
    }
}