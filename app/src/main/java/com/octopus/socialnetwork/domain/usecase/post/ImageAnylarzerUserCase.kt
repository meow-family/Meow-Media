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

        var imageIndexCat: Boolean? = false

        val image = InputImage.fromFilePath(context, imageUri)
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        labeler.process(image).addOnFailureListener {
            imageIndexCat = null
        }.addOnSuccessListener {
            imageIndexCat = it.map { it.index == labelIndexCat }.first()
        }.await()

        return imageIndexCat
    }

    companion object {
        const val labelIndexCat = 118
    }
}