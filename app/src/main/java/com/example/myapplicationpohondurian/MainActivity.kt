package com.example.myapplicationpohondurian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationpohondurian.ui.theme.MyApplicationPohonDurianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationPohonDurianTheme(){
                AlpukatApp()
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlpukatApp() {
    var step by remember { mutableStateOf(1) }
    var alpukatClicks by remember { mutableStateOf(0) }

    // MENAMBAHKAN ACAK KLIK: Nilai acak diawal antara 2 sampai 5
    var targetClicks by remember { mutableStateOf((2..5).random()) }

    val imageResource = when (step) {
        1 -> R.drawable.biji_durian
        2 -> R.drawable.pohon_durian
        3 -> R.drawable.buah_durian
        4 -> R.drawable.jus_durian
        else -> R.drawable.biji_durian
    }

    // Teks menyesuaikan dengan target acak ($targetClicks)
    val stepText = when (step) {
        1 -> "Tahap 1: Biji Pohon Durian\n(Ketik gambar untuk merawat dan menanam)"
        2 -> "Tahap 2: Pohon Durian\n(Ketik gambar untuk merawat: /$targetClicks)"
        3 -> "Tahap 3: Buah Durian\n(Ketik gambar untuk memetik dan mengolah)"
        4 -> "Tahap 4: Jus Durian\n(Ketik gambar untuk meminum jus)"
        else -> ""
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Jus Alpukat Interactive",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Proses membuat jus alpukat",
                modifier = Modifier
                    .size(280.dp)
                    .clickable {
                        when (step) {
                            1 -> step = 2
                            2 -> {
                                alpukatClicks++
                                // Mengecek apakah klik sudah mencapai target acak
                                if (alpukatClicks >= targetClicks) {
                                    step = 3
                                    alpukatClicks = 0
                                    // Membuat target acak baru untuk putaran selanjutnya
                                    targetClicks = (2..9).random()
                                }
                            }
                            3 -> step = 4
                            4 -> step = 1
                        }
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stepText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlpukatAppPreview() {
    MyApplicationPohonDurianTheme() {
        AlpukatApp()
    }
}