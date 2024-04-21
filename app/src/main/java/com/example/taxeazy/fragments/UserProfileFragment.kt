package com.example.taxeazy.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.taxeazy.R
import com.example.taxeazy.app.LoginActivity
import com.example.taxeazy.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfileFragment : Fragment() {

    private lateinit var nameEditText: TextView
    private lateinit var languageEditText: TextView
    private lateinit var logoutButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Find views
        nameEditText = view.findViewById(R.id.nametv)
        languageEditText = view.findViewById(R.id.languagetv)
        logoutButton = view.findViewById(R.id.logoutButton)

        // Set click listener for logout button
        logoutButton.setOnClickListener {
            // Perform logout action
            logoutUser()
        }

        // Load user data
        loadUserData()

        return view
    }

    private fun logoutUser() {
        // Sign out the user from Firebase Authentication
        auth.signOut()

        // Navigate back to the login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        // Finish the current activity (optional)
        requireActivity().finish()
    }

    private fun loadUserData() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val uid = user.uid
            val userRef = firestore.collection("users").document(uid)

            userRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val userData = document.data
                        userData?.let {
                            if (userData.containsKey("username")) {
                                val username = userData["username"] as String
                                nameEditText.setText(username)
                            }
                            if (userData.containsKey("language")) {
                                val language = userData["language"] as String
                                languageEditText.setText(language)
                            }
                        }
                    } else {
                        // Document does not exist or is empty
                        // Handle accordingly
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle errors
                }
        }
    }

}
