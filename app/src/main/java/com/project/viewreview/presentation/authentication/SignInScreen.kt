package com.project.viewreview.presentation.authentication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.project.viewreview.R
import com.project.viewreview.presentation.authentication.components.EmailField
import com.project.viewreview.presentation.authentication.components.PasswordField
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SemiLargePadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    exitAuthentication: () -> Unit,
    navigateToSignUp: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.authState.collectAsState(initial = null)


    val googleSignInState = viewModel.googleState.value
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    if (state.value?.isLoading == true || googleSignInState.loading) {
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

        Row(Modifier.fillMaxWidth().padding(horizontal = MediumPadding, vertical = SmallPadding), horizontalArrangement = Arrangement.End) {
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

            ) {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(MediumPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = "Sign In",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.background,
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Before continue, please sign in",
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
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = !rememberMe },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.secondary,
                            uncheckedColor = MaterialTheme.colorScheme.secondary,
                            checkmarkColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )

                    Text(
                        text = "Remember me",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        scope.launch {
                            viewModel.signInUserWithEmailAndPassword(email, password)
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
                        text = "Sign In",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )


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

        Spacer(modifier = Modifier.padding(SmallPadding))

        Text(
            text = "Sign up with",
            modifier = Modifier.padding(horizontal = SemiLargePadding),
            color = MaterialTheme.colorScheme.surface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.padding(SmallPadding))

        Row {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(context.getString(R.string.default_web_client_id))

                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)

                    googleSignInClient.signOut().addOnCompleteListener {
                        launcher.launch(googleSignInClient.signInIntent)
                    }


                }, modifier = Modifier.size(55.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.shapes.small
                            )
                            .padding(VerySmallPadding)
                            .fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.padding(VerySmallPadding))

                Text(text = "Google", color = MaterialTheme.colorScheme.surface, fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { navigateToSignUp() }, modifier = Modifier.size(55.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Sign Up with Email",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.onSecondary,
                                MaterialTheme.shapes.small
                            )
                            .padding(VerySmallPadding)
                            .fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.padding(VerySmallPadding))

                Text(text = "Email", color = MaterialTheme.colorScheme.surface, fontSize = 15.sp)
            }

        }


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

        LaunchedEffect(key1 = googleSignInState.success) {
            scope.launch {
                if (googleSignInState.success != null) {
                    Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    ViewReviewTheme {

        SignInScreen(exitAuthentication = { /*TODO*/ }) {

        }
    }
}