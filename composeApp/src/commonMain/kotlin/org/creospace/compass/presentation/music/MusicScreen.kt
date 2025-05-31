package org.creospace.compass.presentation.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compass.composeapp.generated.resources.Res
import compass.composeapp.generated.resources.img_meditation
import org.creospace.compass.AudioPlayer
import org.creospace.compass.getAudioPlayer
import org.creospace.compass.presentation.components.CompassAppBar
import org.creospace.compass.presentation.components.CompassButton
import org.creospace.compass.presentation.components.Spacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun MusicScreen(navController: NavController) {

    val audioPlayer = remember { getAudioPlayer() }

    Scaffold(
        topBar = {
            CompassAppBar(title = "Meditation") {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->

        MeditationContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            audioPlayer = audioPlayer
        )

    }
}

@Composable
private fun MeditationContent(modifier: Modifier, audioPlayer: AudioPlayer) {
    var isMusicPlay by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.img_meditation),
            contentDescription = null
        )
        CompassButton(
            title = if (isMusicPlay) "Stop" else "Play"
        ) {
            if (isMusicPlay) {
                audioPlayer.stop()
            } else {
                audioPlayer.playSound(0)
            }
            isMusicPlay = !isMusicPlay
        }
    }
}