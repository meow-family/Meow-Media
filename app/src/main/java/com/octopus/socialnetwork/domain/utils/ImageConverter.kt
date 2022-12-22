package com.octopus.socialnetwork.domain.utils

import android.net.Uri
import com.google.mlkit.vision.common.InputImage

interface ImageConverter {
    fun convertToInputImage(uri: Uri): InputImage
}