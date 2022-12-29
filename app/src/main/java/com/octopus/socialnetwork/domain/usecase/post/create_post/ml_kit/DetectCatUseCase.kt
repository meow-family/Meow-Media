package com.octopus.socialnetwork.domain.usecase.post.create_post.ml_kit


import android.net.Uri
import javax.inject.Inject

class DetectCatUseCase @Inject constructor(
    private val processImageUseCase: ProcessImageUseCase,
) {
    suspend operator fun invoke(imageUri: Uri, labels: List<Int> = listOf(CAT_LABEL_INDEX),
    ): Boolean {

        val detectedLabels = processImageUseCase.invoke(imageUri)

        return detectedLabels?.filter {
            it.index in labels
        }?.size == labels.size
    }

    companion object {
        const val CAT_LABEL_INDEX = 118
        const val ML_KIT_LOG_TAG = "ML_KIT_TESTING"
    }
}