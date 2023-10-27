package com.project.viewreview.presentation.authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.viewreview.presentation.authentication.components.EmailField
import com.project.viewreview.presentation.authentication.components.PasswordField
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SemiLargePadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
    exitAuthentication: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordConfirmVisibility by remember { mutableStateOf(false) }


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.authState.collectAsState(initial = null)

    if (state.value?.isLoading == true) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(Modifier.fillMaxWidth().padding(horizontal = MediumPadding, vertical = SmallPadding), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navigateToSignIn() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Exit Button",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
                )
            }
            IconButton(onClick = { exitAuthentication() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Exit Button",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.background(
                        MaterialTheme.colorScheme.surface,
                        CircleShape
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(horizontal = MediumPadding)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )

        ) {
            Spacer(modifier = Modifier.padding(5.dp))

            Column(
                modifier = Modifier
                    .padding(MediumPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = "Sign Up",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.background,
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Create an account to continue",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.padding(20.dp))

                Column(Modifier.fillMaxWidth()) {

                    EmailField(
                        email = email,
                        onEmailChange = { email = it }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    PasswordField(
                        password = password,
                        passwordVisibility = passwordVisibility,
                        onPasswordChange = { password = it },
                        onPasswordVisibilityChange = { passwordVisibility = !passwordVisibility }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    PasswordField(
                        password = passwordConfirm,
                        passwordVisibility = passwordConfirmVisibility,
                        onPasswordChange = { passwordConfirm = it },
                        onPasswordVisibilityChange = {
                            passwordConfirmVisibility = !passwordConfirmVisibility
                        },
                        title = "Confirm Password",
                        placeholderText = "Re-enter your Password"
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        if (password == passwordConfirm) {
                            scope.launch {
                                viewModel.signUpUserWithEmailAndPassword(email, password)
                            }
                        } else {
                            Toast.makeText(context, "Password does not match", Toast.LENGTH_LONG)
                                .show()
                            return@Button
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }

        Spacer(modifier = Modifier.padding(SmallPadding))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Text(
                text = "Or",
                Modifier.padding(horizontal = SemiLargePadding),
                color = MaterialTheme.colorScheme.surface
            )
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
        }

        Spacer(modifier = Modifier.padding(MediumPadding))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Text(
                text = "Already have an account?",
                color = MaterialTheme.colorScheme.surface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.padding(VerySmallPadding))

            Text(
                text = "Sign In",
                color = MaterialTheme.colorScheme.surface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navigateToSignIn()
                }
            )

        }

        Spacer(modifier = Modifier.padding(SmallPadding))

        LaunchedEffect(key1 = state.value?.isSuccessful) {
            scope.launch {
                if (state.value?.isSuccessful?.isNotEmpty() == true) {
                    val success = state.value?.isSuccessful
                    Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}