package com.saputroekosulistiyo.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saputroekosulistiyo.artspace.ui.theme.ArtSpaceTheme

// Aktivitas utama aplikasi
class MainActivity : ComponentActivity() {
    // Metode ini dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur konten UI dengan fungsi ArtSpaceScreen
        setContent {
            ArtSpaceScreen()
        }
    }
}

// Data class untuk menyimpan informasi karya seni
data class Art(
    val imageResourceId: Int, // ID sumber daya gambar
    val title: String, // Judul karya seni
    val description: String // Deskripsi karya seni
)

// Daftar karya seni yang ditampilkan
val artList = listOf(
    Art(R.drawable.starry_night, "Starry Night", "by Vincent van Gogh, 1889"),
    Art(R.drawable.mona_lisa, "Mona Lisa", "by Leonardo da Vinci, 1503"),
    Art(R.drawable.tpm, "The Persistence of Memory", "by Salvador Dali, 1931")
)

// Fungsi komposabel untuk menampilkan layar ArtSpace
@Composable
fun ArtSpaceScreen() {
    // State untuk indeks karya seni yang sedang ditampilkan
    var currentArtIndex by remember { mutableIntStateOf(0) }

    // Struktur layout kolom untuk menampilkan elemen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding untuk kolom
        horizontalAlignment = Alignment.CenterHorizontally, // Mengatur penyejajaran horizontal
        verticalArrangement = Arrangement.Center // Mengatur penyejajaran vertikal
    ) {
        // Card untuk menampilkan gambar karya seni
        Card(
            modifier = Modifier
                .padding(16.dp) // Padding di luar Card
                .align(Alignment.CenterHorizontally), // Memperluas lebar sesuai dengan lebar kolom
            elevation = CardDefaults.elevatedCardElevation(8.dp), // Efek bayangan
            shape = RoundedCornerShape(8.dp) // Sudut melengkung pada Card
        ) {
            // Menampilkan gambar karya seni
            Image(
                painter = painterResource(id = artList[currentArtIndex].imageResourceId),
                contentDescription = null, // Deskripsi konten untuk aksesibilitas
                modifier = Modifier
                    .size(300.dp) // Ukuran gambar
                    .padding(16.dp) // Padding di dalam Card
                    .align(Alignment.CenterHorizontally) // Mengatur gambar di tengah
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Ruang antara gambar dan teks

        // Menampilkan judul dan deskripsi
        Text(
            text = artList[currentArtIndex].title, // Judul karya seni
            fontSize = 24.sp, // Ukuran font judul
            modifier = Modifier.align(Alignment.CenterHorizontally) // Mengatur teks di tengah
        )
        Text(
            text = artList[currentArtIndex].description, // Deskripsi karya seni
            fontSize = 16.sp, // Ukuran font deskripsi
            modifier = Modifier.align(Alignment.CenterHorizontally) // Mengatur teks di tengah
        )

        Spacer(modifier = Modifier.height(32.dp)) // Ruang sebelum tombol navigasi

        // Tombol navigasi untuk karya seni berikut dan sebelumnya
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, // Mengatur jarak antar tombol
            modifier = Modifier.fillMaxWidth() // Mengisi lebar kolom
        ) {
            Button(onClick = {
                // Mengurangi indeks jika tidak di awal daftar
                if (currentArtIndex > 0) {
                    currentArtIndex--
                }
            }) {
                Text("Previous") // Tombol untuk karya seni sebelumnya
            }
            Button(onClick = {
                // Meningkatkan indeks jika tidak di akhir daftar
                if (currentArtIndex < artList.size - 1) {
                    currentArtIndex++
                }
            }) {
                Text("Next") // Tombol untuk karya seni berikutnya
            }
        }
    }
}

// Fungsi untuk pratinjau layar ArtSpace
@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme { // Menggunakan tema aplikasi
        ArtSpaceScreen() // Menampilkan pratinjau layar
    }
}
