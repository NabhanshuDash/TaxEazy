package com.example.taxeazy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxeazy.models.UserData
import com.example.taxeazy.models.UserViewModel
import com.example.taxeazy.screens.ApplicationsScreen
import com.google.firebase.firestore.FirebaseFirestore

class MyApplicationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid = requireArguments().getString("UID")
        return ComposeView(requireContext()).apply {
            setContent {
                val viewModel : UserViewModel = viewModel()
                if (uid != null) {
                    viewModel.generateFetchUser(uid, FirebaseFirestore.getInstance())
                }
                val userData: UserData = viewModel.searchUser()
                ApplicationsScreen(userData) // Set the caSelectionScreen Composable as the content
            }
        }
    }
}
