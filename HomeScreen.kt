package com.example.aprende.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aprende.R
import com.example.aprende.navigation.Screen
import com.example.aprende.ui.theme.*

// ── Modelo para la barra de navegación ───────────────────────
data class BottomNavItem(
    val label    : String,
    val icon     : ImageVector,
    val route    : String
)

val bottomNavItems = listOf(
    BottomNavItem("Inicio",   Icons.Filled.Home,             Screen.Home.route),
    BottomNavItem("Materias", Icons.Filled.MenuBook,         Screen.Materias.route),
    BottomNavItem("Tareas",   Icons.Filled.AssignmentTurnedIn, Screen.Tareas.route),
    BottomNavItem("Retos",    Icons.Filled.EmojiEvents,      Screen.Retos.route),
    BottomNavItem("Perfil",   Icons.Filled.Person,           Screen.Perfil.route),
)

@Composable
fun HomeScreen(navController: NavController) {
    // TODO: reemplaza con datos reales de tu ViewModel/DB
    val userName      = "Juan"
    val tareasEnCurso = 0
    val tareasVencidas = 0
    val realizadas    = 0
    val enCurso       = 0
    val hoy           = 0

    var selectedRoute by remember { mutableStateOf(Screen.Home.route) }

    Scaffold(
        containerColor = BackgroundDark,
        bottomBar = {
            BottomNavBar(
                items         = bottomNavItems,
                selectedRoute = selectedRoute,
                onItemClick   = { item ->
                    selectedRoute = item.route
                    // TODO: navegar a cada pantalla cuando estén listas
                    // navController.navigate(item.route)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            // ── Header ────────────────────────────────────────
            Row(
                verticalAlignment   = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier            = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text       = "Hola $userName",
                        fontSize   = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("Tienes ")
                            withStyle(SpanStyle(color = YellowHighlight, fontWeight = FontWeight.Bold)) {
                                append("$tareasEnCurso tareas")
                            }
                            append(" en curso y ")
                            withStyle(SpanStyle(color = RedDanger, fontWeight = FontWeight.Bold)) {
                                append("$tareasVencidas vencidas")
                            }
                        },
                        fontSize = 13.sp,
                        color    = TextSecondary
                    )
                }
                Image(
                    painter            = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    modifier           = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Tarjeta de estadísticas ───────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                colors   = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatBadge(label = "Realizadas", value = realizadas, color    = GreenSuccess)
                        StatBadge(label = "En curso",   value = enCurso,    color    = OrangeWarning)
                        StatBadge(label = "Hoy",        value = hoy,        color    = RedDanger)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Barra de progreso
                    LinearProgressIndicator(
                        progress          = { if (realizadas + enCurso == 0) 0f else realizadas.toFloat() / (realizadas + enCurso) },
                        modifier          = Modifier
                            .fillMaxWidth(0.5f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color             = AccentCyan,
                        trackColor        = SurfaceDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Botones de acceso rápido ──────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                colors   = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Column {
                    QuickAccessRow(
                        text    = "Ver mis materias",
                        onClick = { selectedRoute = Screen.Materias.route }
                    )
                    HorizontalDivider(color = SurfaceDark, thickness = 1.dp)
                    QuickAccessRow(
                        text    = "Ver mis tareas de hoy",
                        onClick = { selectedRoute = Screen.Tareas.route }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Inscribir Materia ─────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: navegar a inscribir materia */ },
                shape    = RoundedCornerShape(16.dp),
                colors   = CardDefaults.cardColors(containerColor = CardDark)
            ) {
                Row(
                    modifier              = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text       = "Inscribir Materia",
                        color      = TextPrimary,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector       = Icons.Default.ArrowCircleRight,
                        contentDescription = null,
                        tint              = TextPrimary,
                        modifier          = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

// ── Componente: badge de estadística ─────────────────────────
@Composable
private fun StatBadge(label: String, value: Int, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 12.sp, color = TextSecondary)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier        = Modifier
                .size(width = 72.dp, height = 44.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = value.toString(),
                color      = TextPrimary,
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ── Componente: fila de acceso rápido ────────────────────────
@Composable
private fun QuickAccessRow(text: String, onClick: () -> Unit) {
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(text = text, color = TextPrimary, fontSize = 15.sp)
        Icon(
            imageVector        = Icons.Default.ArrowCircleRight,
            contentDescription = null,
            tint               = TextPrimary,
            modifier           = Modifier.size(26.dp)
        )
    }
}

// ── Componente: barra de navegación inferior ──────────────────
@Composable
fun BottomNavBar(
    items         : List<BottomNavItem>,
    selectedRoute : String,
    onItemClick   : (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = CardDark,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val selected = item.route == selectedRoute
            NavigationBarItem(
                selected = selected,
                onClick  = { onItemClick(item) },
                icon     = {
                    Icon(
                        imageVector        = item.icon,
                        contentDescription = item.label,
                        modifier           = Modifier.size(22.dp)
                    )
                },
                label  = { Text(item.label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor   = AccentCyan,
                    selectedTextColor   = AccentCyan,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary,
                    indicatorColor      = SurfaceDark
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AprendeTheme { HomeScreen(rememberNavController()) }
}