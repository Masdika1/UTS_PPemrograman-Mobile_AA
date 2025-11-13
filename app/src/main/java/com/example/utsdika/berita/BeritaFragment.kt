package com.example.utsdika.berita

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.utsdika.R

@Composable
fun BeritaFragment() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedBerita by remember { mutableStateOf<Berita?>(null) }

    val beritaList = listOf(
        Berita(
            judul = "Cycling Community",
            isi = "Join cycling community for engaging and bonding with new friends with same hobby.",
            members = 24,
            categories = listOf("Benefits", "Health", "Sports"),
            about = "Komunitas kecil namun solid yang terdiri dari 24 anggota aktif yang rutin bersepeda bersama setiap akhir pekan. Kami memiliki grup WhatsApp yang sangat responsif untuk koordinasi jadwal, sharing rute menarik, dan tips perawatan sepeda. Setiap bulan ada ride bareng ke lokasi berbeda, dari rute santai di taman kota hingga jalur menantang di pegunungan. Yang bikin seru, setiap member saling support tanpa memandang level - dari pemula sampai yang udah ikut race. Bonus: sering ada ngopi bareng setelah gowes!",
            imageRes = R.drawable.img,
            tanggal = "10 November 2025"
        ),
        Berita(
            judul = "Gerakan Peduli Lingkungan",
            isi = "Komunitas yang aktif dalam pelestarian alam dan kampanye go green.",
            members = 342,
            categories = listOf("Environment", "Nature", "Conservation"),
            about = "Dengan 342 anggota dari berbagai latar belakang, komunitas ini super aktif mengadakan aksi lingkungan setiap 2 minggu sekali. Dari mahasiswa, pekerja kantoran, hingga ibu rumah tangga - semua bersatu dalam misi yang sama. Kami punya divisi-divisi seperti Beach Cleanup Team, Urban Farming Squad, dan Education Outreach yang bisa kamu pilih sesuai minat. Ada grup Telegram untuk update kegiatan dan sharing tips eco-living. Yang paling seru, setiap aksi selalu diakhiri dengan gathering santai sambil diskusi dan kenalan dengan member baru. Join dan jadilah bagian dari perubahan nyata!",
            imageRes = R.drawable.img_1,
            tanggal = "8 November 2025"
        ),
        Berita(
            judul = "Zero Waste Indonesia",
            isi = "Mari bersama mengurangi sampah dan menerapkan gaya hidup zero waste.",
            members = 278,
            categories = listOf("Sustainability", "Lifestyle", "Green"),
            about = "Komunitas 278 changemakers yang saling mendukung dalam perjalanan zero waste. Kami punya grup online yang aktif 24/7 - bisa tanya-tanya produk ramah lingkungan, minta rekomendasi toko bulk store terdekat, atau sharing pengalaman gagal dan sukses. Setiap minggu ada Challenge Zero Waste dengan hadiah menarik, plus workshop gratis bulanan tentang DIY produk seperti sabun, shampoo bar, hingga beeswax wrap. Member bisa join sesuai pace masing-masing, nggak ada target yang memaksa. Vibe-nya supportive banget, cocok buat pemula yang baru mau mulai atau yang udah advanced mau berbagi pengalaman.",
            imageRes = R.drawable.img_2,
            tanggal = "5 November 2025"
        ),
        Berita(
            judul = "Tanam Pohon Bersama",
            isi = "Aksi nyata reboisasi dan penghijauan untuk masa depan lebih baik.",
            members = 567,
            categories = listOf("Reforestation", "Climate", "Action"),
            about = "Komunitas terbesar kami dengan 567 anggota yang tersebar di berbagai kota! Struktur organisasinya rapi dengan koordinator regional di setiap daerah, jadi kamu bisa ikut kegiatan yang dekat dari lokasi kamu. Setiap event penanaman pohon, biasanya datang 50-100 orang - seru banget buat networking dan kenal orang baru yang punya passion sama. Kami sediakan semua perlengkapan (bibit, cangkul, sarung tangan), kamu tinggal datang dan bawa semangat. Ada aplikasi khusus untuk tracking pohon yang kamu tanam - bisa lihat perkembangannya dari foto yang diupdate volunteer. Member juga dapat sertifikat dan badge digital setiap kali ikut aksi. Cocok banget buat yang mau berkontribusi nyata sambil bikin circle pertemanan baru!",
            imageRes = R.drawable.img_3,
            tanggal = "3 November 2025"
        ),
        Berita(
            judul = "Podcast Community",
            isi = "Tips and tricks for podcast enthusiasts and creators.",
            members = 10,
            categories = listOf("Education", "Media", "Tech"),
            about = "Komunitas intimate dengan 10 member dedicated yang serius di dunia podcasting. Karena jumlahnya kecil, interaksinya jadi sangat personal dan deep - kita saling kenal satu sama lain dengan baik. Setiap minggu ada virtual meetup untuk review podcast masing-masing, sharing equipment, dan troubleshooting masalah teknis. Member terdiri dari mix podcaster pemula dan yang sudah punya puluhan episode, jadi bisa belajar langsung dari pengalaman mereka. Kami juga sering kolaborasi bikin episode bareng atau jadi guest di podcast satu sama lain. Perfect untuk yang serius mau develop podcast skills dalam lingkungan supportive dan nggak judgmental!",
            imageRes = R.drawable.img_4,
            tanggal = "1 November 2025"
        )
    )

    val filteredBeritaList = if (searchQuery.isEmpty()) {
        beritaList
    } else {
        beritaList.filter {
            it.judul.contains(searchQuery, ignoreCase = true) ||
                    it.isi.contains(searchQuery, ignoreCase = true)
        }
    }

    if (selectedBerita != null) {
        BeritaDetailScreen(
            berita = selectedBerita!!,
            onBack = { selectedBerita = null }
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            // List Berita
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredBeritaList) { berita ->
                    BeritaCard(
                        berita = berita,
                        onClick = { selectedBerita = berita }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun BeritaDetailScreen(
    berita: Berita,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom Header dengan Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Detail Berita",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                // Gambar Header - langsung tanpa padding
                Image(
                    painter = painterResource(id = berita.imageRes),
                    contentDescription = berita.judul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Judul
                    Text(
                        text = berita.judul,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Tanggal
                    Text(
                        text = berita.tanggal,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Deskripsi
                    Text(
                        text = berita.isi,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Categories
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        berita.categories.forEach { category ->
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            ) {
                                Text(
                                    text = category,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // About - Detail Penjelasan Berita
                    Text(
                        text = "Detail",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = berita.about,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                    )
                }
            }
        }
    }
}

@Composable
fun BeritaCard(
    berita: Berita,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Gambar Header
            Image(
                painter = painterResource(id = berita.imageRes),
                contentDescription = berita.judul,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                // Judul
                Text(
                    text = berita.judul,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Tanggal
                Text(
                    text = berita.tanggal,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Deskripsi
                Text(
                    text = berita.isi,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Categories
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    berita.categories.forEach { category ->
                        Surface(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = category,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class Berita(
    val judul: String,
    val isi: String,
    val members: Int,
    val categories: List<String>,
    val about: String,
    val imageRes: Int,
    val tanggal: String
)