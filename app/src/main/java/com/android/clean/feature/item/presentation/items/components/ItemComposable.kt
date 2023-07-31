package com.android.clean.feature.item.presentation.items.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.android.clean.feature.item.domain.model.Item
import com.android.clean.ui.theme.ReverseTriangleShapePath
import com.android.clean.ui.theme.SomeBlue
import com.android.clean.ui.theme.SomeYellow
import com.android.clean.ui.theme.TriangleShapePath

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
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.87f)
        ) {
            Box {
                ColoredCard(
                    text = item.title,
                    modifier = Modifier.fillMaxWidth(),
                    textModifier = Modifier
                        .padding(16.dp)
                        .padding(end = 10.dp)
                )
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(21.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Item",
                        tint = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier.weight(0.13f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IncrementButton(
                reverse = false,
                onClick = increaseQuantity
            )
            ColoredCard(
                text = item.quantity.toString(),
                modifier = Modifier.fillMaxWidth(),
                textModifier = Modifier
                    .padding(0.dp, 16.dp),
                textAlign = TextAlign.Center,
            )
            IncrementButton(
                reverse = true,
                onClick = decreaseQuantity
            )
        }
    }
}

@Composable
fun ColoredCard(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text.trimStart(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = fontWeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = textModifier.fillMaxWidth(),
            textAlign = textAlign
        )
    }
}

@Composable
fun IncrementButton(
    reverse: Boolean,
    onClick: () -> Unit = {}
) {
    Canvas(modifier = Modifier
        .size(56.dp, 28.dp)
        .clickable { onClick() }
    ) {
        val rect = Rect(0f, 0f, size.width, size.height)
        val percentEffect = (20 / 100f) * rect.maxDimension
        val shapePath = if (reverse)
            ReverseTriangleShapePath(rect)
        else
            TriangleShapePath(rect)

        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(shapePath),
                paint = Paint().apply {
                    color = SomeYellow
                    pathEffect = PathEffect.cornerPathEffect(percentEffect)
                    style = PaintingStyle.Fill
                }
            )
            // border
            canvas.drawOutline(
                outline = Outline.Generic(shapePath),
                paint = Paint().apply {
                    color = SomeBlue
                    pathEffect = PathEffect.cornerPathEffect(percentEffect)
                    style = PaintingStyle.Stroke
                    strokeWidth = 3.dp.toPx()
                }
            )
        }

    }
}