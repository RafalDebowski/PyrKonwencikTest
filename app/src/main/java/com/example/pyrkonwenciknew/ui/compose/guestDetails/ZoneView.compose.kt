package com.example.pyrkonwenciknew.ui.compose.guestDetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ZonesView(list: List<String>, modifier: Modifier) {
    Row {
        list.forEach {
            ZoneView(it, modifier.padding(2.dp))
        }
    }
}

@Composable
private fun ZoneView(name: String, modifier: Modifier) {
    Surface(
        modifier = modifier
            .border(2.dp, color = Color.Gray, shape = RectangleShape)
    ) {
        Text(
            modifier = modifier.padding(8.dp),
            text = name
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ZonesViewPreview() {
    val list = listOf("Literacka", "Muzyka", "Teatr")
    ZonesView(list, Modifier)
}