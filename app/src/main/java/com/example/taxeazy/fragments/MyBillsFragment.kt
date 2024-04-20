package com.example.taxeazy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxeazy.databinding.FragmentMyBillsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MyBillsFragment : Fragment() {

    private lateinit var binding: FragmentMyBillsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyBillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the CalendarView listener
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            uploadImageForDate(selectedDate)
        }
    }

    private fun uploadImageForDate(selectedDate: Calendar) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("$uid/${selectedDate.get(Calendar.YEAR)}/${selectedDate.get(Calendar.MONTH) + 1}/${selectedDate.get(Calendar.DAY_OF_MONTH)}/${UUID.randomUUID()}.jpg")

        // TODO: Replace imageUri with the Uri of the selected image
        // val uploadTask = imageRef.putFile(imageUri)

        // TODO: Handle image upload success and failure
    }
}
