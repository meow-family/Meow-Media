package com.octopus.socialnetwork.domain.utils

import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import java.io.File

interface FileService {
    fun openInputImage(uri: Uri): InputImage
    fun openFile(uri: Uri) : File
}