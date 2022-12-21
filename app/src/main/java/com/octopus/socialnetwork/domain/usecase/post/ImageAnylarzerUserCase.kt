package com.octopus.socialnetwork.domain.usecase.post


import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageAnalyzerUserCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(imageUri: Uri): Boolean? {

        var isImageHaveCat: Boolean? = false

        val image = InputImage.fromFilePath(context, imageUri)
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        labeler.process(image).addOnFailureListener {
            isImageHaveCat = null
        }.addOnSuccessListener { it ->
            isImageHaveCat = it.map { it.index == CAT_LABEL_INDEX }.first()
        }.await()

        return isImageHaveCat
    }

    companion object {
        const val CAT_LABEL_INDEX = 118
    }
}