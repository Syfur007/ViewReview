package com.project.viewreview.presentation.menu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseUser
import com.project.viewreview.R
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding

@Composable
fun SignedInMenuItems(
    currentUser: FirebaseUser,
    onMyReviewsClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val context = LocalContext.current
    val userName = currentUser.displayName
    val userPhoto = currentUser.photoUrl
    val userEmail = currentUser.email!!

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (userPhoto != null) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(userPhoto).build(),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(SmallPadding)
                    .clip(shape = CircleShape),
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(SmallPadding)
                    .clip(shape = CircleShape),
            )
        }

        if (userName != null) {
            Text(
                text = userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }

        Text(
            text = userEmail,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.padding(MediumPadding))

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(horizontal = MediumPadding, vertical = SmallPadding)
        ) {
            Text(
                text = "General Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.padding(VerySmallPadding))

        OptionWithIcon(
            titleText = "My Reviews",
            icon = Icons.Default.Article,
            onClick = { onMyReviewsClick() })
        OptionWithIcon(
            titleText = "Change Password",
            icon = Icons.Default.Key,
            onClick = { onChangePasswordClick() })
        OptionWithIcon(
            titleText = "Logout",
            icon = Icons.Default.Logout,
            onClick = { onLogoutClick() })

    }

}