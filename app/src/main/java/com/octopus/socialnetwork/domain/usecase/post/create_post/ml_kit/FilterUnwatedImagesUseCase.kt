package com.octopus.socialnetwork.domain.usecase.post.create_post.ml_kit

import android.net.Uri
import javax.inject.Inject

class FilterUnwantedImagesUseCase @Inject constructor(
    private val processImageUseCase: ProcessImageUseCase,
) {
    suspend operator fun invoke(
        imageUri: Uri,
        unwantedLabels: List<Int> = listOf(1,2,3,4)
    ): Boolean {

        val detectedLabels = processImageUseCase.invoke(imageUri)

        return detectedLabels?.filterNot { it.index in unwantedLabels }?.size == detectedLabels?.size
    }


}