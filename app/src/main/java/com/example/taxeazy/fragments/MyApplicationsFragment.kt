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
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        return ComposeView(requireContext()).apply {
            setContent {
                if(!checkIfCA(uid)){
                    val viewModel: UserViewModel = viewModel()
                    viewModel.generateFetchUser(uid, FirebaseFirestore.getInstance())
                    val userData: UserData = viewModel.searchUser()
                    ApplicationsScreen(userData)
                }
                else {
                    val viewModel: CAViewModel = viewModel()
                    viewModel.fetchCA(uid, FirebaseFirestore.getInstance())
                    val caData: CaData = viewModel.getcadata()
                    ApplicationsScreen(caData)
                }
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
