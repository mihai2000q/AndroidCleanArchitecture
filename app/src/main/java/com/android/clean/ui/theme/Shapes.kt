package com.android.clean.ui.theme

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

val TriangleShapePath: (Rect) -> Path = { rect ->
    Path().apply {
        with(rect) {
            moveTo(topCenter.x, 0f)
            lineTo(bottomRight.x, bottomRight.y)
            lineTo(bottomLeft.x, bottomLeft.y)
        }
        close()
    }
}

val ReverseTriangleShapePath: (Rect) -> Path = { rect ->
    Path().apply {
        with(rect) {
            lineTo(topRight.x, topRight.y)
            lineTo(bottomCenter.x, bottomCenter.y)
        }
        close()
    }
}