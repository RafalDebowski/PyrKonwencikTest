package com.example.pyrkonwenciknew.ui.compose.guestDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pyrkonwenciknew.domain.model.GuestDomain

@Composable
fun GuestDetailsView(
    item: GuestDomain,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserImage(
            imageUrl = item.imageUrl,
            size = 200.dp
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            text = item.name
        )
        Spacer(Modifier.height(12.dp))
        ZonesView(item.zones, Modifier)
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = item.summary
        )
    }

    IconButton(onClick = {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
    }

}
