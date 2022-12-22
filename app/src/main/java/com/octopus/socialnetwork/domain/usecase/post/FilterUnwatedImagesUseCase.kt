package com.octopus.socialnetwork.domain.usecase.post

import android.net.Uri
import com.octopus.socialnetwork.domain.usecase.ProcessImageUseCase
import javax.inject.Inject

class FilterUnwantedImagesUseCase @Inject constructor(
    private val processImageUseCase: ProcessImageUseCase,
) {
    suspend operator fun invoke(
        imageUri: Uri,
        unwantedLabels: List<Int> = listOf(1,2,3,4)
    ): Boolean {

        val detectedLabels = processImageUseCase.invoke(imageUri)

        return detectedLabels?.filterNot {
            it.index in unwantedLabels
        }?.size == detectedLabels?.size
    }


}