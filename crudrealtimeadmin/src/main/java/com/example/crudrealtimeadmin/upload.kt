
package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class upload : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val ownername=binding.uploadName.text.toString()
            val vehiclebrand=binding.uploadvehiclebrand.text.toString()
            val vehiclerto=binding.uploadvhicelerto.text.toString()
            val vehiclenumber=binding.uploadvehiclenumber.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("vehicleInformation")
            val vehicleData=vehicleData(ownername, vehiclebrand, vehiclerto, vehiclenumber)
            databaseReference.child(vehiclenumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadvehiclebrand.text.clear()
                binding.uploadvehiclenumber.text.clear()
                binding.uploadvhicelerto.text.clear()

                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "unable to upload", Toast.LENGTH_SHORT).show()
            }
        }

    }
}