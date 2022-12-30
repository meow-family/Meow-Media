package com.octopus.socialnetwork.domain.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.annotation.RequiresApi
import com.google.mlkit.vision.common.InputImage
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class FileServiceImpl @Inject constructor(
    private val context: Context,
) : FileService {
    override fun openInputImage(uri: Uri): InputImage {
        return InputImage.fromFilePath(context, uri)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun openFile(fileUri: Uri): File {
        var fileName = ""

        fileUri.let { returnUri ->
            context.contentResolver.query(returnUri, null, null, null)
        }?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            fileName = cursor.getString(nameIndex)
        }

        val iStream = context.contentResolver.openInputStream(fileUri)!!
        val outputDir = context.cacheDir!!

        val outputFile = File(outputDir, fileName)
        copyStreamToFile(iStream, outputFile)
        iStream.close()
        return outputFile

    }

    private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024)
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }
}