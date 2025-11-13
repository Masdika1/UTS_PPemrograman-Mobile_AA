# UTS Pemrograman Mobile - UTSdika

**Nama:** Riandika Fildani  
**NIM:** 152023062  
**Kelas:** IF - Ganjil 2025/2026  
**Mata Kuliah:** Pemrograman Mobile

---

## 📱 Deskripsi Aplikasi

**UTSdika** adalah aplikasi Android yang dikembangkan menggunakan **Kotlin** dan **Jetpack Compose** sebagai bagian dari Ujian Tengah Semester mata kuliah Pemrograman Mobile. Aplikasi ini menampilkan berbagai fitur interaktif dengan antarmuka yang modern dan responsif.

### Fitur Utama:
1. **Splash Screen** - Tampilan awal dengan animasi menarik (durasi 5 detik)
2. **Berita** - Menampilkan list berita komunitas dengan fitur pencarian dan detail
3. **Cuaca** - Informasi cuaca terkini dengan prakiraan mingguan
4. **Kalkulator** - Kalkulator fungsional untuk operasi matematika dasar
5. **Kontak** - Daftar kontak dengan fitur pencarian dan detail lengkap
6. **Biodata** - Profil pribadi dengan mode view dan edit

---

## 🎯 Ketentuan Pengerjaan

### 1. Splash Screen
✅ Splash screen dengan durasi **5 detik**  
✅ Menampilkan **nama aplikasi** dengan animasi typing effect  
✅ Menampilkan **foto profil** dengan animasi scale dan bounce  
✅ Desain modern dengan gradient background dan elemen dekoratif floating  
✅ Otomatis berpindah ke Dashboard Activity setelah timeout

### 2. Dashboard Activity
✅ Menggunakan **Bottom Navigation Bar** dengan 5 menu utama  
✅ **Top App Bar** yang menampilkan judul sesuai fragment aktif  
✅ **Responsive design** - Top App Bar disembunyikan saat mode landscape  
✅ Fragment-based navigation untuk setiap menu  
✅ Implementasi Material Design 3 (Material You)

### 3. Fragment Implementation

#### Fragment Berita
✅ List berita menggunakan **LazyColumn**  
✅ Setiap item menampilkan:
- Gambar/thumbnail berita
- Judul berita
- Preview isi berita
- Jumlah members
- Kategori/tags
- Tanggal publikasi  
✅ **Fitur pencarian** berita dengan SearchBar  
✅ **Detail berita** dengan tampilan full content saat item diklik  
✅ Minimal **5 data berita** dengan konten lengkap

#### Fragment Cuaca
✅ Tampilan informasi cuaca terkini meliputi:
- Nama kota
- Tanggal saat ini
- Suhu dengan ikon cuaca
- Deskripsi kondisi cuaca
- Humidity dan Wind Speed  
✅ **Prakiraan cuaca mingguan** (7 hari ke depan)  
✅ Design modern dengan gradient dan card elevation  
✅ Data dummy untuk simulasi API cuaca

#### Fragment Kalkulator
✅ Kalkulator fungsional dengan operasi:
- Penjumlahan (+)
- Pengurangan (-)
- Perkalian (×)
- Pembagian (÷)  
✅ Display untuk input dan hasil  
✅ Tombol Clear (C) dan Delete  
✅ Tombol angka 0-9 dan decimal point  
✅ Layout yang user-friendly dan responsive

#### Fragment Kontak
✅ Daftar kontak menggunakan **LazyColumn**  
✅ Setiap kontak menampilkan:
- Foto profil
- Nama lengkap
- Nomor telepon
- Status/tagline  
✅ **Fitur pencarian** kontak  
✅ **Detail kontak** saat diklik dengan informasi:
- Email
- WhatsApp
- Alamat
- Social media links  
✅ Minimal **10 data kontak**

#### Fragment Biodata
✅ **Mode View** - Menampilkan biodata dalam card yang rapi:
- Foto profil
- Data pribadi (Nama, NIM, Tanggal Lahir, dll)
- Data kontak (Email, Telepon, WhatsApp)
- Data akademik (Universitas, Program Studi, Tahun Masuk)  
✅ **Mode Edit** - Form untuk mengubah data:
- TextField untuk setiap data
- RadioButton untuk Jenis Kelamin
- Dropdown untuk Golongan Darah
- Button Save dan Cancel  
✅ **Skill section** dengan progress bar untuk setiap skill  
✅ Tombol toggle antara mode View dan Edit  
✅ Validasi dan state management yang baik

---

## � Screenshots

### Splash Screen
<p align="center">
  <img src="screenshoot/splashscreen.jpg" width="250" alt="Splash Screen">
</p>

Tampilan splash screen dengan animasi typing effect untuk nama aplikasi "DIKΛ", foto profil dengan animasi bounce, gradient background dengan elemen dekoratif floating, dan durasi 5 detik sebelum masuk ke Dashboard.

---

### Fragment Berita
<p align="center">
  <img src="screenshoot/berita.jpg" width="250" alt="Fragment Berita">
  <img src="screenshoot/detailberita.jpg" width="250" alt="Detail Berita">
</p>

**Kiri:** List berita dengan thumbnail, judul, preview, tags, dan informasi members.  
**Kanan:** Detail berita dengan konten lengkap saat item diklik.

---

### Fragment Cuaca
<p align="center">
  <img src="screenshoot/cuaca.jpg" width="250" alt="Fragment Cuaca 1">
  <img src="screenshoot/cuaca2.jpg" width="250" alt="Fragment Cuaca 2">
</p>

Informasi cuaca terkini dengan ikon, suhu, deskripsi kondisi cuaca, humidity, wind speed, dan prakiraan cuaca 7 hari ke depan dengan design gradient yang menarik.

---

### Fragment Kalkulator
<p align="center">
  <img src="screenshoot/kalkulator.jpg" width="250" alt="Kalkulator Portrait">
  <img src="screenshoot/kalkulatorlandscape.jpg" width="400" alt="Kalkulator Landscape">
</p>

**Kiri:** Kalkulator dalam mode portrait dengan layout yang intuitif.  
**Kanan:** Kalkulator dalam mode landscape dengan responsive design.

---

### Fragment Kontak
<p align="center">
  <img src="screenshoot/kontak.jpg" width="250" alt="Fragment Kontak">
  <img src="screenshoot/detailkontak.jpg" width="250" alt="Detail Kontak">
</p>

**Kiri:** List kontak dengan foto profil, nama, nomor telepon, dan status.  
**Kanan:** Detail kontak dengan informasi lengkap termasuk email, WhatsApp, alamat, dan social media links.

---

### Fragment Biodata
<p align="center">
  <img src="screenshoot/biodata.jpg" width="250" alt="Biodata View Mode">
  <img src="screenshoot/formeditbiodata.jpg" width="250" alt="Biodata Edit Mode">
</p>

**Kiri:** Mode View dengan card informasi biodata yang rapi, termasuk foto profil, data pribadi, kontak, akademik, dan skill section.  
**Kanan:** Mode Edit dengan form input lengkap untuk mengubah data biodata.

---

## �🛠️ Teknologi yang Digunakan

### Framework & Library
- **Kotlin** - Bahasa pemrograman utama
- **Jetpack Compose** - Modern UI toolkit untuk Android
- **Material Design 3** - Design system terbaru dari Google
- **Compose BOM** - Bill of Materials untuk sinkronisasi versi Compose
- **Fragment KTX** - AndroidX Fragment library

### Build Configuration
- **Android Gradle Plugin** - Build automation
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 36 (Android 15)
- **Compile SDK:** 36
- **Java Version:** 11

### UI Components
- LazyColumn untuk efficient scrolling
- Material3 components (Card, Button, TextField, etc.)
- Custom animations (typing effect, floating, scale, fade)
- Responsive layouts with Modifier
- Vector icons dari Material Icons

---

## 📂 Struktur Folder

```
app/src/main/
├── java/com/example/utsdika/
│   ├── MainActivity.kt                 # Splash Screen Activity
│   ├── DashboardActivity.kt            # Main Dashboard dengan Bottom Nav
│   ├── berita/
│   │   └── BeritaFragment.kt           # Fragment untuk berita
│   ├── cuaca/
│   │   └── CuacaFragment.kt            # Fragment untuk cuaca
│   ├── kalkulator/
│   │   └── CalculatorFragment.kt       # Fragment untuk kalkulator
│   ├── kontak/
│   │   └── KontakFragment.kt           # Fragment untuk kontak
│   ├── biodata/
│   │   └── BiodataFragment.kt          # Fragment untuk biodata
│   └── ui/theme/
│       ├── Color.kt                    # Definisi warna tema
│       ├── Theme.kt                    # Konfigurasi tema Material3
│       └── Type.kt                     # Typography settings
├── res/
│   ├── drawable/                       # Asset gambar (img.png, img_1.png, dll)
│   ├── mipmap/                         # App icons
│   ├── values/
│   │   ├── strings.xml                 # String resources
│   │   └── themes.xml                  # XML theme definitions
│   └── xml/
│       └── data_extraction_rules.xml
└── AndroidManifest.xml                 # App manifest
```

---

## 🚀 Cara Menjalankan Aplikasi

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 atau lebih baru
- JDK 11 atau lebih baru
- Android SDK dengan API Level 24 atau lebih tinggi
- Emulator atau perangkat Android fisik

### Langkah Instalasi

1. **Clone atau Download Repository**
   ```bash
   git clone https://github.com/Masdika1/UTS_PPemrograman-Mobile_AA.git
   cd UTSdika
   ```

2. **Buka Project di Android Studio**
   - Buka Android Studio
   - Pilih `File > Open`
   - Navigate ke folder project `UTSdika`
   - Klik `OK`

3. **Sync Gradle**
   - Android Studio akan otomatis melakukan Gradle sync
   - Tunggu hingga proses selesai
   - Pastikan tidak ada error

4. **Run Aplikasi**
   - Pilih emulator atau connect perangkat Android
   - Klik tombol `Run` (▶️) atau tekan `Shift + F10`
   - Tunggu build process selesai
   - Aplikasi akan terbuka di emulator/device

### Troubleshooting
- Jika terjadi error Gradle sync, coba `File > Invalidate Caches / Restart`
- Pastikan internet tersedia untuk download dependencies
- Update Android Studio ke versi terbaru jika ada masalah kompatibilitas

---

## 🎨 Design Highlights

### Color Scheme
- Primary: Gradient dari purple ke pink
- Secondary: Blue accent
- Background: White dengan subtle gradient
- Card: Elevated dengan shadow

### Animations
- **Typing Effect** pada splash screen
- **Cursor Blinking** animation
- **Scale & Bounce** untuk foto profil
- **Floating** animation untuk elemen dekoratif
- **Fade In/Out** untuk transisi
- **Smooth scroll** pada semua list

### Typography
- Font: Default system font (Roboto)
- Title: Bold, 24-32 sp
- Body: Regular, 14-16 sp
- Caption: Light, 12 sp

---

## 📋 Fitur Tambahan

### Responsive Design
✅ Support orientasi portrait dan landscape  
✅ Adaptive layout berdasarkan ukuran layar  
✅ Top App Bar disembunyikan di mode landscape untuk maksimalkan ruang

### User Experience
✅ Smooth animations dan transitions  
✅ Loading states untuk simulasi proses  
✅ Error handling dengan snackbar/toast  
✅ Form validation di Fragment Biodata  
✅ Search functionality di Fragment Berita dan Kontak

### Code Quality
✅ Clean code dengan proper naming convention  
✅ Separation of concerns (UI, Data, Logic)  
✅ Reusable composable functions  
✅ State management dengan remember dan mutableStateOf  
✅ Comments untuk penjelasan code kompleks

---

## 👨‍💻 Developer

**Riandika Fildani**  
- NIM: 152023062
- Program Studi: Teknik Informatika
- Universitas: Institut Teknologi Nasional Bandung
- Email: riandika.fildani@mhs.itenas.ac.id
- GitHub: [@Masdika1](https://github.com/Masdika1)

---

## 📄 Lisensi

Project ini dibuat untuk keperluan akademik (UTS Pemrograman Mobile) di Institut Teknologi Nasional Bandung.

---

## 🙏 Acknowledgments

- Dosen Pengampu: Mata Kuliah Pemrograman Mobile
- Google Developers untuk dokumentasi Jetpack Compose
- Material Design team untuk design system
- Stack Overflow community untuk troubleshooting

---

## 📝 Catatan Pengembangan

### Version 1.0 (November 2025)
- ✅ Implementasi Splash Screen dengan animasi
- ✅ Dashboard dengan Bottom Navigation
- ✅ Fragment Berita dengan search dan detail
- ✅ Fragment Cuaca dengan prakiraan mingguan
- ✅ Fragment Kalkulator fungsional
- ✅ Fragment Kontak dengan search dan detail
- ✅ Fragment Biodata dengan mode View dan Edit
- ✅ Responsive design untuk portrait dan landscape
- ✅ Material Design 3 implementation

### Future Improvements
- [ ] Integrasi dengan API cuaca real-time
- [ ] Database local dengan Room untuk menyimpan data
- [ ] Share functionality untuk berita
- [ ] Dark mode support
- [ ] Localization (multi-bahasa)
- [ ] Unit testing dan UI testing

---

**Dibuat dengan ❤️ menggunakan Kotlin & Jetpack Compose**
