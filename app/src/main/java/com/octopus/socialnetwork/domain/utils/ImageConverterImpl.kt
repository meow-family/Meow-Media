package com.octopus.socialnetwork.domain.utils

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import javax.inject.Inject

class ImageConverterImpl @Inject constructor(
    private val context: Context,
) : ImageConverter {
    override fun convertToInputImage(uri: Uri): InputImage {
        return InputImage.fromFilePath(context, uri)
    }
}