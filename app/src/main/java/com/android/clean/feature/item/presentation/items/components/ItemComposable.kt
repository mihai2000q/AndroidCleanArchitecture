package com.android.clean.feature.item.presentation.items.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.clean.feature.item.domain.model.Item

@Composable
fun ItemComposable(
    item: Item,
    onDelete: () -> Unit,
    increaseQuantity: () -> Unit,
    decreaseQuantity: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.9f)
        ) {
            Box {
                ColoredCard(
                    text = item.title,
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(0.dp, 7.dp, 7.dp, 0.dp)
                        .size(21.dp)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Item")
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            Modifier.weight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IncrementButton(
                icon = Icons.Default.Add,
                contextDescription = "Increase Quantity",
                onClick = increaseQuantity
            )
            ColoredCard(
                text = item.quantity.toString(),
                modifier = Modifier,
            )
            IncrementButton(
                icon = Icons.Default.Remove,
                contextDescription = "Decrease Quantity",
                onClick = decreaseQuantity
            )
        }
    }
}

@Composable
fun ColoredCard(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = fontWeight,
            modifier = Modifier.padding(16.dp)
        )
        content()
    }
}

@Composable
fun IncrementButton(
    icon: ImageVector,
    contextDescription: String = "",
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = Modifier
            .padding(0.dp, 5.dp)
            .size(14.dp),
        onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = contextDescription)
    }
}