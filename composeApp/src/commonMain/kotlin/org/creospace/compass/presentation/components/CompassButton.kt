package org.creospace.compass.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CompassButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall
        )
    }
}