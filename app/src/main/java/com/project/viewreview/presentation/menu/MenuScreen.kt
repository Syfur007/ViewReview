package com.project.viewreview.presentation.menu

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.project.viewreview.presentation.common.MovieButton
import com.project.viewreview.presentation.menu.components.OptionWithIcon
import com.project.viewreview.presentation.menu.components.SignedInMenuItems
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme

@Composable
fun MenuScreen(
    onSignInClick: () -> Unit,
    onMyReviewsClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onAboutAppClick: () -> Unit,
    onTermsAndConditionsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
) {

    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser by remember{ mutableStateOf(firebaseAuth.currentUser) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = MediumPadding)
            .fillMaxSize()
            .statusBarsPadding()
    ) {


        if (currentUser != null) {
            SignedInMenuItems(
                currentUser = currentUser!!,
                onMyReviewsClick = { onMyReviewsClick() },
                onChangePasswordClick = { onChangePasswordClick() },
                onLogoutClick = {
                    firebaseAuth.signOut().also {
                        Toast.makeText(context, "Logged out!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        } else {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(MediumPadding), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "You are not logged in")
                
                Spacer(modifier = Modifier.padding(SmallPadding))
                
                MovieButton(
                    text = "Sign In",
                    onClick = { onSignInClick() }
                )
            }
        }

        Column {
            Spacer(modifier = Modifier.padding(SmallPadding))

            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(horizontal = MediumPadding, vertical = SmallPadding)
            ) {
                Text(
                    text = "Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

            }
            Spacer(modifier = Modifier.padding(VerySmallPadding))
            OptionWithIcon(
                titleText = "About App",
                icon = Icons.Default.PhoneAndroid,
                onClick = { onAboutAppClick() })
            OptionWithIcon(
                titleText = "Terms & Conditions",
                icon = Icons.Default.Article,
                onClick = { onTermsAndConditionsClick() })
            OptionWithIcon(
                titleText = "Privacy Policy",
                icon = Icons.Default.PrivacyTip,
                onClick = { onPrivacyPolicyClick() })
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MenuScreenPreview() {
    ViewReviewTheme {
        MenuScreen(
            onSignInClick = {},
            onMyReviewsClick = {},
            onChangePasswordClick = {},
            onAboutAppClick = {},
            onTermsAndConditionsClick = {},
            onPrivacyPolicyClick = {}
        )
    }
}