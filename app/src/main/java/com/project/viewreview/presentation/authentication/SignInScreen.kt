package com.project.viewreview.presentation.authentication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.viewreview.R
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SemiLargePadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(MediumPadding)
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
                Spacer(modifier = Modifier.padding(25.dp))

                Column(Modifier.fillMaxWidth()) {

                    Text(
                        text = "Email",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.background
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        placeholder = { Text(text = "Enter your Email", fontSize = 14.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            placeholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Password",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.background
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                Icon(
                                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = "Toggle Password Visibility",
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        },
                        placeholder = { Text(text = "Enter your Password", fontSize = 14.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            placeholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (passwordVisibility) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }
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
                    onClick = {},
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
                    .width(50.dp)
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

            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { }, modifier = Modifier.size(60.dp)) {
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

            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {  }, modifier = Modifier.size(60.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Toggle Password Visibility",
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

    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    ViewReviewTheme {
        LoginScreen()
    }
}