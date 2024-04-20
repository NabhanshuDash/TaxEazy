package com.example.taxeazy.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(navController: NavHostController){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = { /*TODO*/ }) {

            Text(text="Register as CA")

        }

        Button(onClick = { /*TODO*/ }) {

            Text(text="Register as Business")

        }

        Button(onClick = { /*TODO*/ }) {

            Text(text="Login as CA")

        }

        Button(onClick = { /*TODO*/ }) {

            Text(text="Login as Business")

        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}