package com.project.viewreview.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.project.viewreview.R


val MavenPro = FontFamily(
    Font(R.font.mavenpro_regular, FontWeight.Normal),
    Font(R.font.mavenpro_medium, FontWeight.Medium),
    Font(R.font.mavenpro_semibold, FontWeight.SemiBold),
    Font(R.font.mavenpro_bold, FontWeight.Bold),
    Font(R.font.mavenpro_extrabold, FontWeight.ExtraBold),
    Font(R.font.mavenpro_black, FontWeight.Black),
)



// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)