package com.example.taxeazy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.taxeazy.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.Calendar
import java.util.UUID


class MyBillsFragment : Fragment() {

    private lateinit var selectedDate: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize MaterialDatePicker
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDate = Calendar.getInstance().apply {
                timeInMillis = selection
            }

            // Handle image upload for the selected date
            uploadImageForDate()
        }

        val selectDateButton : Button
        selectDateButton = view.findViewById(R.id.selectDateButton)

        // Show DatePicker when button is clicked
        selectDateButton.setOnClickListener {
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }
    }

    private fun uploadImageForDate() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("uid/${selectedDate.get(Calendar.YEAR)}/${selectedDate.get(Calendar.MONTH) + 1}/${selectedDate.get(Calendar.DAY_OF_MONTH)}/${UUID.randomUUID()}.jpg")

        // TODO: Replace imageUri with the Uri of the selected image
        // val uploadTask = imageRef.putFile(imageUri)

        // TODO: Handle image upload success and failure
    }
}