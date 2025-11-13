package com.example.utsdika

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.utsdika.berita.BeritaFragment
import com.example.utsdika.biodata.BiodataFragment
import com.example.utsdika.cuaca.CuacaFragment
import com.example.utsdika.kalkulator.CalculatorFragment
import com.example.utsdika.kontak.KontakFragment
import com.example.utsdika.ui.theme.UTSdikaTheme

class DashboardActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTSdikaTheme {
                DashboardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    // Deteksi orientasi layar
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val items = listOf(
        BottomNavItem("Berita", Icons.Filled.Newspaper),
        BottomNavItem("Cuaca", Icons.Filled.Cloud),
        BottomNavItem("Kalkulator", Icons.Filled.Calculate),
        BottomNavItem("Kontak", Icons.Filled.Contacts),
        BottomNavItem("Biodata", Icons.Filled.Person)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Hanya tampilkan TopAppBar jika BUKAN landscape
            if (!isLandscape) {
                TopAppBar(
                    title = { Text(items[selectedItem].title) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                0 -> BeritaFragment()
                1 -> CuacaFragment()
                2 -> FragmentContainer(fragment = CalculatorFragment())
                3 -> KontakFragment()
                4 -> BiodataFragment()
            }
        }
    }
}

@Composable
fun FragmentContainer(fragment: Fragment) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val activity = context as? FragmentActivity

    AndroidView(
        factory = { ctx ->
            FragmentContainerView(ctx).apply {
                id = android.view.View.generateViewId()
            }
        },
        update = { view ->
            activity?.supportFragmentManager?.commit {
                replace(view.id, fragment)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

// Icon fallback jika Newspaper tidak tersedia
val Icons.Filled.Newspaper: ImageVector
    get() = Icons.Filled.Article