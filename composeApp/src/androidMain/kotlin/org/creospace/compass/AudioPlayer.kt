package org.creospace.compass

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import compass.composeapp.generated.resources.Res

actual class AudioPlayer(private val context: Context) {
    private val mediaPlayer = ExoPlayer.Builder(context).build()
    private val mediaItem = soundResList.map {
        MediaItem.fromUri(Res.getUri(it))
    }

    private var isPrepared = false

    actual fun playSound(id: Int) {
        mediaPlayer.setMediaItem(mediaItem[id])
        mediaPlayer.prepare() // Ensure it's prepared before playing
        mediaPlayer.play()
        isPrepared = true
    }

    actual fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    actual fun stop() {
        if (isPrepared) {
            mediaPlayer.stop()
            isPrepared = false
        }
    }

    actual fun release() {
        mediaPlayer.release()
        isPrepared = false
    }
}