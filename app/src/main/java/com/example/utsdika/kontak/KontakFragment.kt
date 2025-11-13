package com.example.utsdika.kontak

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KontakFragment() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedKontak by remember { mutableStateOf<Kontak?>(null) }

    val kontakList = remember {
        listOf(
            Kontak(
                nama = "Andi Wijaya",
                telepon = "081234567890",
                email = "andi@email.com",
                kategori = "Teman",
                inisial = "AW",
                warna = Color(0xFF2196F3)
            ),
            Kontak(
                nama = "Budi Santoso",
                telepon = "081234567891",
                email = "budi@email.com",
                kategori = "Keluarga",
                inisial = "BS",
                warna = Color(0xFF4CAF50)
            ),
            Kontak(
                nama = "Citra Dewi",
                telepon = "081234567892",
                email = "citra@email.com",
                kategori = "Kerja",
                inisial = "CD",
                warna = Color(0xFFFF9800)
            ),
            Kontak(
                nama = "Dika Pratama",
                telepon = "081234567893",
                email = "dika@email.com",
                kategori = "Teman",
                inisial = "DP",
                warna = Color(0xFF9C27B0)
            ),
            Kontak(
                nama = "Eka Putri",
                telepon = "081234567894",
                email = "eka@email.com",
                kategori = "Teman",
                inisial = "EP",
                warna = Color(0xFFE91E63)
            ),
            Kontak(
                nama = "Fajar Ramadhan",
                telepon = "081234567895",
                email = "fajar@email.com",
                kategori = "Kerja",
                inisial = "FR",
                warna = Color(0xFF00BCD4)
            ),
            Kontak(
                nama = "Gita Sari",
                telepon = "081234567896",
                email = "gita@email.com",
                kategori = "Keluarga",
                inisial = "GS",
                warna = Color(0xFF8BC34A)
            ),
            Kontak(
                nama = "Hendra Kusuma",
                telepon = "081234567897",
                email = "hendra@email.com",
                kategori = "Teman",
                inisial = "HK",
                warna = Color(0xFFFF5722)
            ),
            Kontak(
                nama = "Indah Permata",
                telepon = "081234567898",
                email = "indah@email.com",
                kategori = "Keluarga",
                inisial = "IP",
                warna = Color(0xFF3F51B5)
            ),
            Kontak(
                nama = "Joko Widodo",
                telepon = "081234567899",
                email = "joko@email.com",
                kategori = "Kerja",
                inisial = "JW",
                warna = Color(0xFFFFEB3B)
            ),
            Kontak(
                nama = "Kartika Sari",
                telepon = "081234567800",
                email = "kartika@email.com",
                kategori = "Teman",
                inisial = "KS",
                warna = Color(0xFF009688)
            ),
            Kontak(
                nama = "Luna Maya",
                telepon = "081234567801",
                email = "luna@email.com",
                kategori = "Teman",
                inisial = "LM",
                warna = Color(0xFFFF4081)
            ),
            Kontak(
                nama = "Maya Anggraini",
                telepon = "081234567802",
                email = "maya@email.com",
                kategori = "Kerja",
                inisial = "MA",
                warna = Color(0xFF795548)
            ),
            Kontak(
                nama = "Nanda Wijaya",
                telepon = "081234567803",
                email = "nanda@email.com",
                kategori = "Keluarga",
                inisial = "NW",
                warna = Color(0xFF607D8B)
            ),
            Kontak(
                nama = "Oscar Pratama",
                telepon = "081234567804",
                email = "oscar@email.com",
                kategori = "Teman",
                inisial = "OP",
                warna = Color(0xFFCDDC39)
            ),
            Kontak(
                nama = "Putri Ayu",
                telepon = "081234567805",
                email = "putri@email.com",
                kategori = "Teman",
                inisial = "PA",
                warna = Color(0xFFF44336)
            ),
            Kontak(
                nama = "Rudi Hermawan",
                telepon = "081234567806",
                email = "rudi@email.com",
                kategori = "Kerja",
                inisial = "RH",
                warna = Color(0xFF9E9E9E)
            ),
            Kontak(
                nama = "Siti Nurhaliza",
                telepon = "081234567807",
                email = "siti@email.com",
                kategori = "Keluarga",
                inisial = "SN",
                warna = Color(0xFF673AB7)
            ),
            Kontak(
                nama = "Tono Sugiarto",
                telepon = "081234567808",
                email = "tono@email.com",
                kategori = "Teman",
                inisial = "TS",
                warna = Color(0xFF03A9F4)
            ),
            Kontak(
                nama = "Vina Kusuma",
                telepon = "081234567809",
                email = "vina@email.com",
                kategori = "Kerja",
                inisial = "VK",
                warna = Color(0xFFFF6F00)
            )
        )
    }

    val filteredKontak = kontakList.filter {
        it.nama.contains(searchQuery, ignoreCase = true) ||
                it.telepon.contains(searchQuery) ||
                it.email.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Cari kontak...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = MaterialTheme.shapes.medium
                )

                // Info jumlah kontak
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${filteredKontak.size} kontak ditemukan",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Kontak List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredKontak) { kontak ->
                KontakCard(
                    kontak = kontak,
                    onClick = { selectedKontak = kontak }
                )
            }

            if (filteredKontak.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.SearchOff,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Kontak tidak ditemukan",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }

    // Detail Modal
    if (selectedKontak != null) {
        KontakDetailModal(
            kontak = selectedKontak!!,
            onDismiss = { selectedKontak = null }
        )
    }
}

@Composable
fun KontakCard(
    kontak: Kontak,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar dengan inisial
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(kontak.warna),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = kontak.inisial,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Info
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = kontak.nama,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    // Badge kategori
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = kontak.warna.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = kontak.kategori,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = kontak.warna,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = kontak.telepon,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = kontak.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun KontakDetailModal(
    kontak: Kontak,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = null,  // Kosongkan title
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,  // Tambahkan ini untuk center
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Avatar di tengah
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(kontak.warna),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = kontak.inisial,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Nama di tengah
                Text(
                    text = kontak.nama,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Detail rows
                DetailRow(
                    icon = Icons.Filled.Phone,
                    label = "Telepon",
                    value = kontak.telepon
                )
                DetailRow(
                    icon = Icons.Filled.Email,
                    label = "Email",
                    value = kontak.email
                )
                DetailRow(
                    icon = Icons.Filled.Category,
                    label = "Kategori",
                    value = kontak.kategori
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Tutup")
            }
        }
    )
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Data class
data class Kontak(
    val nama: String,
    val telepon: String,
    val email: String,
    val kategori: String,
    val inisial: String,
    val warna: Color
)