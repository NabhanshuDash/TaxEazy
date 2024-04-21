package com.example.taxeazy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.CAViewModel
import com.example.taxeazy.models.CaData
import com.example.taxeazy.models.UserData
import com.example.taxeazy.models.UserViewModel
import com.example.taxeazy.screens.ApplicationsScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyApplicationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
                if(!checkIfCA(FirebaseAuth.getInstance().currentUser?.uid.toString())){
                    var uid = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get().addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val userId = documentSnapshot.getString("uid")
                            if (userId != null) {
                                // Use userId here
                                println("User ID: $userId")
                                setContent {
                                    val viewModel: UserViewModel = viewModel()
                                    viewModel.generateFetchUser(userId, FirebaseFirestore.getInstance())
                                    val userData: UserData = viewModel.searchUser()
                                    ApplicationsScreen(userData)
                                }
                            } else {
                                println("User ID not found in the document.")
                            }
                        } else {
                            println("User document does not exist.")
                        } }
                }
                else {var uid = FirebaseFirestore.getInstance().collection("ca").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userId = documentSnapshot.getString("uid")
                        if (userId != null) {
                            // Use userId here
                            println("User ID: $userId")
                            setContent {
                                val viewModel: CAViewModel = viewModel()
                                viewModel.fetchCA(userId, FirebaseFirestore.getInstance())
                                val caData: CaData = viewModel.getcadata()
                                ApplicationsScreen(caData)
                            }
                        } else {
                            println("User ID not found in the document.")
                        }
                    } else {
                        println("User document does not exist.")
                    } }
                }
        }

    }

    private fun checkIfCA(uid: String): Boolean {
        var isCA = false
        val db = FirebaseFirestore.getInstance()

        // Assuming you have a collection named "CAUsers" where CA information is stored
        db.collection("ca").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // User exists in the CA database
                    isCA = true
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                println("Error checking if user is a CA: $exception")
            }

        return isCA
    }
}
