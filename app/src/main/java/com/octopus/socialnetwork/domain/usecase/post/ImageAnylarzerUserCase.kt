package com.octopus.socialnetwork.domain.usecase.post


import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ImageAnalyzerUserCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(imageUri: Uri): Boolean? {

        return suspendCancellableCoroutine { continuation ->

            val image = InputImage.fromFilePath(context, imageUri)
            val labelerImage = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

            labelerImage.process(image).addOnSuccessListener { resultAnalyzer ->
                val isImageIncludeCat = resultAnalyzer.map { it.index == CAT_LABEL_INDEX }.first()
                continuation.resume(isImageIncludeCat)
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }

        }

    }

    companion object {
        const val CAT_LABEL_INDEX = 118
    }
}