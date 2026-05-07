package com.example.aprende.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // ── Logo ──────────────────────────────────────────
            Image(
                painter            = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo Aprende",
                modifier           = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── Título ────────────────────────────────────────
            Text(
                text       = "Aprende a\nAprender",
                fontSize   = 32.sp,
                fontWeight = FontWeight.Bold,
                color      = AccentCyan,
                textAlign  = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(80.dp))

            // ── Botón Iniciar Sesión ──────────────────────────
            Button(
                onClick  = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentCyan,
                    contentColor   = BackgroundDark
                )
            ) {
                Text(
                    text       = "Inicie Sesión",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Botón Regístrese ──────────────────────────────
            Button(
                onClick  = { navController.navigate(Screen.Register.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentCyanDark,
                    contentColor   = TextPrimary
                )
            ) {
                Text(
                    text       = "Regístrese",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AprendeTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}