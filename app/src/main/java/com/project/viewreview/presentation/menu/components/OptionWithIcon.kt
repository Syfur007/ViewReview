package com.project.viewreview.presentation.menu.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SmallPadding

@Composable
fun OptionWithIcon(
    titleText: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = MediumPadding, vertical = SmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row {
            Icon(
                imageVector = icon,
                contentDescription = titleText,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.padding(SmallPadding))

            Text(
                text = titleText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = titleText,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(30.dp)
        )

    }
}