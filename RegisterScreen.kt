package com.example.aprende.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aprende.R
import com.example.aprende.navigation.Screen
import com.example.aprende.ui.theme.*

@Composable
fun RegisterScreen(navController: NavController) {
    var nombre        by remember { mutableStateOf("") }
    var email         by remember { mutableStateOf("") }
    var password      by remember { mutableStateOf("") }
    var confirmPass   by remember { mutableStateOf("") }
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter            = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier           = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Crear Cuenta", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("Regístrate para comenzar", fontSize = 14.sp, color = TextSecondary)

            Spacer(modifier = Modifier.height(28.dp))

            // ── Nombre ────────────────────────────────────────
            OutlinedTextField(
                value         = nombre,
                onValueChange = { nombre = it; errorMessage = "" },
                label         = { Text("Nombre completo", color = TextSecondary) },
                leadingIcon   = { Icon(Icons.Default.Person, contentDescription = null, tint = AccentCyan) },
                modifier      = Modifier.fillMaxWidth(),
                shape         = RoundedCornerShape(12.dp),
                singleLine    = true,
                colors        = outlinedTextFieldColors()
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ── Email ─────────────────────────────────────────
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

            Spacer(modifier = Modifier.height(14.dp))

            // ── Contraseña ────────────────────────────────────
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

            Spacer(modifier = Modifier.height(14.dp))

            // ── Confirmar Contraseña ──────────────────────────
            OutlinedTextField(
                value         = confirmPass,
                onValueChange = { confirmPass = it; errorMessage = "" },
                label         = { Text("Confirmar contraseña", color = TextSecondary) },
                leadingIcon   = { Icon(Icons.Default.LockOpen, contentDescription = null, tint = AccentCyan) },
                visualTransformation = PasswordVisualTransformation(),
                modifier      = Modifier.fillMaxWidth(),
                shape         = RoundedCornerShape(12.dp),
                singleLine    = true,
                colors        = outlinedTextFieldColors()
            )

            // ── Error ─────────────────────────────────────────
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorMessage, color = RedDanger, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Botón Registrarse ─────────────────────────────
            Button(
                onClick = {
                    when {
                        nombre.isBlank()              -> errorMessage = "Ingresa tu nombre"
                        email.isBlank()               -> errorMessage = "Ingresa tu correo"
                        password.isBlank()            -> errorMessage = "Ingresa una contraseña"
                        password.length < 6           -> errorMessage = "Mínimo 6 caracteres"
                        password != confirmPass       -> errorMessage = "Las contraseñas no coinciden"
                        else -> {
                            // TODO: conectar con tu base de datos aquí
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
                Text("Registrarse", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("¿Ya tienes cuenta? ", color = TextSecondary, fontSize = 14.sp)
                TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                    Text("Inicia sesión", color = AccentCyan, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    AprendeTheme { RegisterScreen(rememberNavController()) }
}