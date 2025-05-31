package org.creospace.compass

import compass.composeapp.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL

actual class AudioPlayer {

    val mediaItem = soundResList.map { path ->
        val uri = Res.getUri(path)
        NSURL.URLWithString(URLString = uri)
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun playSound(id: Int) {
        val avAudioPlayer = AVAudioPlayer(mediaItem[id]!!, error = null)
        avAudioPlayer.prepareToPlay()
        avAudioPlayer.play()
    }

    actual fun pause() {

    }

    actual fun stop() {

    }

    actual fun release() {

    }
}