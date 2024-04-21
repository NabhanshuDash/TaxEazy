package com.example.taxeazy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.R
import com.example.taxeazy.models.UserData
import com.example.taxeazy.models.UserViewModel
import com.example.taxeazy.screens.ApplicationsScreen
import com.example.taxeazy.screens.CaSelectionScreen
import com.example.taxeazy.screens.ViewClientsScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyClientsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            FirebaseFirestore.getInstance().collection("ca").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val clintList : List<String> = documentSnapshot.get("clients") as List<String>
                    if (clintList != null) {
                        // Use userId here

                    } else {
                        println("Clients not found in the document.")
                    }
                } else {
                    println("User document does not exist.")
                } }
            setContent {
                ViewClientsScreen() // Set the caSelectionScreen Composable as the content
            }
        }
    }
}