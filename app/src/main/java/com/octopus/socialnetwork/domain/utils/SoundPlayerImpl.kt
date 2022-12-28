package com.octopus.socialnetwork.domain.utils

import android.media.MediaPlayer
import javax.inject.Inject

class SoundPlayerImpl @Inject constructor(
    private val mediaPlayer: MediaPlayer,
) : SoundPlayer{
    override fun playSound() {
        mediaPlayer.start()
        if (mediaPlayer.isPlaying.not()) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}