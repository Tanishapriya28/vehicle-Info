package com.example.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val vehicleNumber=binding.deletePhone.text.toString()
            if(vehicleNumber.isNotEmpty())
            {
                deleteData(vehicleNumber)
            }else
            {
                Toast.makeText(this,"please enter vehicle number",Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun deleteData(vehicleNumber:String)
    {
        databaseReference=FirebaseDatabase.getInstance().getReference("vehicleInformation")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deletePhone.text.clear()
            Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"unable to delete",Toast.LENGTH_SHORT).show()
        }
    }

}