package com.example.utsdika.cuaca

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CuacaFragment() {
    var selectedCity by remember { mutableStateOf("Bandung") }
    val cities = listOf("Bandung", "Jakarta", "Surabaya", "Yogyakarta", "Bali")

    val cuacaData = getCuacaData(selectedCity)
    val prakiraan = getPrakiraanMingguan()

    // Warna yang bersih dan modern
    val backgroundColor = Color(0xFFF8F9FA)
    val primaryColor = Color(0xFF2D3436)
    val accentBlue = Color(0xFF0984E3)
    val cardBackground = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar dengan lokasi
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = cardBackground,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Cuaca Hari Ini",
                        fontSize = 14.sp,
                        color = primaryColor.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = getCurrentDate(),
                        fontSize = 12.sp,
                        color = primaryColor.copy(alpha = 0.4f)
                    )
                }

                // Dropdown Kota
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Surface(
                        onClick = { expanded = true },
                        shape = RoundedCornerShape(12.dp),
                        color = backgroundColor,
                        modifier = Modifier.height(44.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = accentBlue
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                selectedCity,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = primaryColor
                            )
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null,
                                tint = primaryColor
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(
                            cardBackground,
                            RoundedCornerShape(12.dp)
                        )
                    ) {
                        cities.forEach { city ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        city,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor
                                    )
                                },
                                onClick = {
                                    selectedCity = city
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Main Weather Card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            color = accentBlue
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                // Icon Cuaca dengan animasi
                val infiniteTransition = rememberInfiniteTransition(label = "weather_animation")

                // Animasi rotasi untuk matahari
                val rotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(20000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "rotation"
                )

                // Animasi floating untuk awan
                val floatingOffset by infiniteTransition.animateFloat(
                    initialValue = -8f,
                    targetValue = 8f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "floating"
                )

                // Animasi pulse untuk background
                val pulseScale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "pulse"
                )

                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = cuacaData.icon,
                            contentDescription = "Cuaca",
                            modifier = Modifier
                                .size(64.dp)
                                .offset(y = if (cuacaData.kondisi.contains("Berawan", ignoreCase = true) ||
                                    cuacaData.kondisi.contains("Awan", ignoreCase = true))
                                    floatingOffset.dp else 0.dp)
                                .graphicsLayer {
                                    rotationZ = if (cuacaData.kondisi.contains("Cerah", ignoreCase = true) ||
                                        cuacaData.kondisi.contains("Panas", ignoreCase = true))
                                        rotation else 0f
                                    scaleX = pulseScale
                                    scaleY = pulseScale
                                },
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Suhu
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "${cuacaData.suhu}",
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = (-4).sp
                    )
                    Text(
                        text = "°C",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Kondisi
                Text(
                    text = cuacaData.kondisi,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.95f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Weather Details Grid
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Informasi Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CleanDetailCard(
                    icon = Icons.Outlined.WaterDrop,
                    label = "Kelembaban",
                    value = "${cuacaData.kelembaban}%",
                    iconColor = Color(0xFF00B894),
                    modifier = Modifier.weight(1f)
                )
                CleanDetailCard(
                    icon = Icons.Outlined.Air,
                    label = "Kec. Angin",
                    value = "${cuacaData.angin} km/h",
                    iconColor = Color(0xFF6C5CE7),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CleanDetailCard(
                    icon = Icons.Outlined.Visibility,
                    label = "Visibilitas",
                    value = "${cuacaData.jarakPandang} km",
                    iconColor = Color(0xFFFD79A8),
                    modifier = Modifier.weight(1f)
                )
                CleanDetailCard(
                    icon = Icons.Outlined.Speed,
                    label = "Tekanan",
                    value = "${cuacaData.tekanan} mb",
                    iconColor = Color(0xFFFDCB6E),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Forecast
            Text(
                text = "Prakiraan 7 Hari",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(prakiraan) { item ->
                    CleanForecastCard(item, accentBlue)
                }
            }
        }
    }
}

@Composable
fun CleanDetailCard(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    val primaryColor = Color(0xFF2D3436)

    Surface(
        modifier = modifier
            .height(120.dp)
            .shadow(2.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = CircleShape,
                color = iconColor.copy(alpha = 0.15f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(22.dp),
                        tint = iconColor
                    )
                }
            }

            Column {
                Text(
                    text = value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = primaryColor.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun CleanForecastCard(prakiraan: PrakiraanCuaca, accentColor: Color) {
    val primaryColor = Color(0xFF2D3436)

    Surface(
        modifier = Modifier
            .width(85.dp)
            .height(130.dp)
            .shadow(2.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = prakiraan.hari,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )

            Surface(
                shape = CircleShape,
                color = accentColor.copy(alpha = 0.1f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = prakiraan.icon,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = accentColor
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${prakiraan.suhuMax}°",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
                Text(
                    text = "${prakiraan.suhuMin}°",
                    fontSize = 13.sp,
                    color = primaryColor.copy(alpha = 0.4f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// Data Classes
data class CuacaData(
    val suhu: Int,
    val kondisi: String,
    val icon: ImageVector,
    val kelembaban: Int,
    val angin: Int,
    val jarakPandang: Int,
    val tekanan: Int
)

data class PrakiraanCuaca(
    val hari: String,
    val icon: ImageVector,
    val suhuMax: Int,
    val suhuMin: Int
)

// Helper Functions
fun getCuacaData(city: String): CuacaData {
    return when (city) {
        "Bandung" -> CuacaData(25, "Berawan", Icons.Filled.Cloud, 75, 12, 10, 1013)
        "Jakarta" -> CuacaData(32, "Cerah", Icons.Filled.WbSunny, 65, 15, 8, 1012)
        "Surabaya" -> CuacaData(31, "Panas", Icons.Filled.WbSunny, 70, 18, 9, 1011)
        "Yogyakarta" -> CuacaData(28, "Berawan", Icons.Filled.Cloud, 72, 10, 12, 1014)
        "Bali" -> CuacaData(30, "Cerah", Icons.Filled.WbSunny, 68, 20, 15, 1010)
        else -> CuacaData(25, "Berawan", Icons.Filled.Cloud, 75, 12, 10, 1013)
    }
}

fun getPrakiraanMingguan(): List<PrakiraanCuaca> {
    return listOf(
        PrakiraanCuaca("Sen", Icons.Filled.WbSunny, 30, 22),
        PrakiraanCuaca("Sel", Icons.Filled.Cloud, 28, 21),
        PrakiraanCuaca("Rab", Icons.Filled.CloudQueue, 27, 20),
        PrakiraanCuaca("Kam", Icons.Filled.WbSunny, 31, 23),
        PrakiraanCuaca("Jum", Icons.Filled.Cloud, 29, 22),
        PrakiraanCuaca("Sab", Icons.Filled.WbSunny, 32, 24),
        PrakiraanCuaca("Min", Icons.Filled.CloudQueue, 28, 21)
    )
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
    return sdf.format(Date())
}
