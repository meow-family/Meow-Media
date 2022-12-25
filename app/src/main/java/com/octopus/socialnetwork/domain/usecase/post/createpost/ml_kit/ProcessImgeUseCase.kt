package com.octopus.socialnetwork.domain.usecase.post.createpost.ml_kit

import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.octopus.socialnetwork.domain.utils.ImageConverter
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ProcessImageUseCase @Inject constructor(
    private val imageConverter: ImageConverter,
) {
    suspend operator fun invoke(
        imageUri: Uri,
    ): List<ImageLabel>? {

        return suspendCancellableCoroutine { continuation ->
            val image = imageConverter.convertToInputImage(imageUri)
            val imageLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

            imageLabeler.process(image).addOnSuccessListener { detectedLabels ->
                Log.i(DetectCatUseCase.ML_KIT_LOG_TAG, "the detected labels $detectedLabels")
                continuation.resume(detectedLabels.filter { it.confidence >= 0.7f })
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }

        }
    }
}