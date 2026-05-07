package com.example.aprende.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aprende.R
import com.example.aprende.navigation.Screen
import com.example.aprende.ui.theme.*

@Composable
fun LoginScreen(navController: NavController) {
    var email         by remember { mutableStateOf("") }
    var password      by remember { mutableStateOf("") }
    var showPassword  by remember { mutableStateOf(false) }
    var errorMessage  by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // ── Logo ──────────────────────────────────────────
            Image(
                painter            = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier           = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text       = "Iniciar Sesión",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextPrimary
            )

            Text(
                text     = "Bienvenido de nuevo",
                fontSize = 14.sp,
                color    = TextSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── Campo Email ───────────────────────────────────
            OutlinedTextField(
                value         = email,
                onValueChange = { email = it; errorMessage = "" },
                label         = { Text("Correo electrónico", color = TextSecondary) },
                leadingIcon   = { Icon(Icons.Default.Email, contentDescription = null, tint = AccentCyan) },
                modifier      = Modifier.fillMaxWidth(),
                shape         = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine    = true,
                colors        = outlinedTextFieldColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Campo Contraseña ──────────────────────────────
            OutlinedTextField(
                value         = password,
                onValueChange = { password = it; errorMessage = "" },
                label         = { Text("Contraseña", color = TextSecondary) },
                leadingIcon   = { Icon(Icons.Default.Lock, contentDescription = null, tint = AccentCyan) },
                trailingIcon  = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier      = Modifier.fillMaxWidth(),
                shape         = RoundedCornerShape(12.dp),
                singleLine    = true,
                colors        = outlinedTextFieldColors()
            )

            // ── Error ─────────────────────────────────────────
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text     = errorMessage,
                    color    = RedDanger,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Botón Ingresar ────────────────────────────────
            Button(
                onClick = {
                    when {
                        email.isBlank()    -> errorMessage = "Ingresa tu correo"
                        password.isBlank() -> errorMessage = "Ingresa tu contraseña"
                        else -> {
                            // TODO: conectar con tu base de datos aquí
                            // Por ahora navega directo al Home
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Welcome.route) { inclusive = true }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape  = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentCyan,
                    contentColor   = BackgroundDark
                )
            ) {
                Text("Ingresar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Link Registro ─────────────────────────────────
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("¿No tienes cuenta? ", color = TextSecondary, fontSize = 14.sp)
                TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
                    Text("Regístrate", color = AccentCyan, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AprendeTheme { LoginScreen(rememberNavController()) }
}