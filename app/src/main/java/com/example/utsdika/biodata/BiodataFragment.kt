package com.example.utsdika.biodata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utsdika.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataFragment() {
    // State untuk mode (View atau Edit)
    var isEditMode by remember { mutableStateOf(false) }

    // State untuk form input
    var nama by remember { mutableStateOf("Riandika Fildani") }
    var nim by remember { mutableStateOf("152023062") }
    var tanggalLahir by remember { mutableStateOf("19 Januari 2005") }
    var jenisKelamin by remember { mutableStateOf("Laki-laki") }
    var golonganDarah by remember { mutableStateOf("O") }
    var noTelepon by remember { mutableStateOf("+62 812-3456-7890") }
    var email by remember { mutableStateOf("riandika.fildani@mhs.itenas.ac.id") }
    var whatsapp by remember { mutableStateOf("+62 812-3456-7890") }
    var alamat by remember { mutableStateOf("Gg.Anggrek 4 No.35, Cimahi, Jawa Barat") }
    var universitas by remember { mutableStateOf("Institut Teknologi Nasional Bandung") }
    var programStudi by remember { mutableStateOf("Teknik Informatika") }
    var tahunMasuk by remember { mutableStateOf("2023") }

    // State untuk dropdown dan date picker
    var expandedBloodType by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header dengan gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar dengan foto profil
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nama
                Text(
                    text = nama,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Status/Jabatan
                Text(
                    text = "Mahasiswa • $programStudi",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.95f),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Badge
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "⭐ Semester 5",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Edit/Simpan
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { isEditMode = !isEditMode },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isEditMode) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = if (isEditMode) Icons.Filled.Check else Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isEditMode) "Simpan" else "Edit Biodata")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Informasi Pribadi
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SectionTitle("Informasi Pribadi")

            if (isEditMode) {
                // Mode Edit - Form Input
                InputField(
                    label = "Nama Lengkap",
                    value = nama,
                    onValueChange = { nama = it },
                    icon = Icons.Filled.Person
                )

                InputField(
                    label = "NIM",
                    value = nim,
                    onValueChange = { nim = it },
                    icon = Icons.Filled.Badge
                )

                // Date Picker untuk Tanggal Lahir
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    onClick = { showDatePicker = true }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Cake,
                            contentDescription = "Tanggal Lahir",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Tanggal Lahir",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = tanggalLahir,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ RADIO BUTTON untuk Jenis Kelamin
                Text(
                    text = "Jenis Kelamin",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .selectableGroup(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Wc,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Radio Button: Laki-laki
                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = (jenisKelamin == "Laki-laki"),
                                    onClick = { jenisKelamin = "Laki-laki" },
                                    role = Role.RadioButton
                                )
                                .padding(end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (jenisKelamin == "Laki-laki"),
                                onClick = null
                            )
                            Text(
                                text = "Laki-laki",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        // Radio Button: Perempuan
                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = (jenisKelamin == "Perempuan"),
                                    onClick = { jenisKelamin = "Perempuan" },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (jenisKelamin == "Perempuan"),
                                onClick = null
                            )
                            Text(
                                text = "Perempuan",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown Golongan Darah
                ExposedDropdownMenuBox(
                    expanded = expandedBloodType,
                    onExpandedChange = { expandedBloodType = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    OutlinedTextField(
                        value = golonganDarah,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Golongan Darah") },
                        leadingIcon = {
                            Icon(Icons.Filled.LocalHospital, contentDescription = null)
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBloodType)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = OutlinedTextFieldDefaults.colors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedBloodType,
                        onDismissRequest = { expandedBloodType = false }
                    ) {
                        listOf("A", "B", "AB", "O").forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    golonganDarah = option
                                    expandedBloodType = false
                                }
                            )
                        }
                    }
                }

            } else {
                // Mode View - Card Display
                BiodataCard(
                    icon = Icons.Filled.Badge,
                    label = "NIM",
                    value = nim,
                    color = MaterialTheme.colorScheme.primary
                )

                BiodataCard(
                    icon = Icons.Filled.Cake,
                    label = "Tanggal Lahir",
                    value = tanggalLahir,
                    color = MaterialTheme.colorScheme.secondary
                )

                BiodataCard(
                    icon = Icons.Filled.Wc,
                    label = "Jenis Kelamin",
                    value = jenisKelamin,
                    color = MaterialTheme.colorScheme.tertiary
                )

                BiodataCard(
                    icon = Icons.Filled.LocalHospital,
                    label = "Golongan Darah",
                    value = golonganDarah,
                    color = Color(0xFFE53935)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Kontak
            SectionTitle("Kontak")

            if (isEditMode) {
                InputField(
                    label = "Nomor Telepon",
                    value = noTelepon,
                    onValueChange = { noTelepon = it },
                    icon = Icons.Filled.Phone
                )

                InputField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    icon = Icons.Filled.Email
                )

                InputField(
                    label = "WhatsApp",
                    value = whatsapp,
                    onValueChange = { whatsapp = it },
                    icon = Icons.Filled.Chat
                )
            } else {
                BiodataCard(
                    icon = Icons.Filled.Phone,
                    label = "Nomor Telepon",
                    value = noTelepon,
                    color = Color(0xFF4CAF50)
                )

                BiodataCard(
                    icon = Icons.Filled.Email,
                    label = "Email",
                    value = email,
                    color = Color(0xFFFF9800)
                )

                BiodataCard(
                    icon = Icons.Filled.Chat,
                    label = "WhatsApp",
                    value = whatsapp,
                    color = Color(0xFF25D366)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Alamat & Pendidikan
            SectionTitle("Alamat & Pendidikan")

            if (isEditMode) {
                InputField(
                    label = "Alamat Rumah",
                    value = alamat,
                    onValueChange = { alamat = it },
                    icon = Icons.Filled.Home,
                    maxLines = 3
                )

                InputField(
                    label = "Universitas",
                    value = universitas,
                    onValueChange = { universitas = it },
                    icon = Icons.Filled.School
                )

                InputField(
                    label = "Program Studi",
                    value = programStudi,
                    onValueChange = { programStudi = it },
                    icon = Icons.Filled.MenuBook
                )

                InputField(
                    label = "Tahun Masuk",
                    value = tahunMasuk,
                    onValueChange = { tahunMasuk = it },
                    icon = Icons.Filled.CalendarMonth
                )
            } else {
                BiodataCard(
                    icon = Icons.Filled.Home,
                    label = "Alamat Rumah",
                    value = alamat,
                    color = MaterialTheme.colorScheme.primary
                )

                BiodataCard(
                    icon = Icons.Filled.School,
                    label = "Universitas",
                    value = universitas,
                    color = MaterialTheme.colorScheme.secondary
                )

                BiodataCard(
                    icon = Icons.Filled.MenuBook,
                    label = "Program Studi",
                    value = programStudi,
                    color = MaterialTheme.colorScheme.tertiary
                )

                BiodataCard(
                    icon = Icons.Filled.CalendarMonth,
                    label = "Tahun Masuk",
                    value = tahunMasuk,
                    color = Color(0xFF9C27B0)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Keterampilan
            SectionTitle("Keterampilan")

            SkillSection(
                skills = listOf(
                    Skill("Python", 70),
                    Skill("JavaScript", 70),
                    Skill("Kotlin", 15),
                    Skill("UI/UX Design", 70),
                    Skill("Git & GitHub", 60)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    // Ambil tanggal yang dipilih dan konversi ke format Indonesia
                    datePickerState.selectedDateMillis?.let { millis ->
                        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
                        tanggalLahir = dateFormat.format(Date(millis))
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = {
            Icon(icon, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors()
    )
}

@Composable
fun SectionTitle(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = null,
            modifier = Modifier.size(8.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BiodataCard(
    icon: ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = color.copy(alpha = 0.15f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp),
                        tint = color
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun SkillSection(skills: List<Skill>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        skills.forEach { skill ->
            SkillBar(skill)
        }
    }
}

@Composable
fun SkillBar(skill: Skill) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = skill.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${skill.level}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = skill.level / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(MaterialTheme.shapes.small),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

data class Skill(
    val name: String,
    val level: Int
)