package com.bankapp.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects
import javax.inject.Inject

class UriProvider @Inject constructor(
    private val context: Context,
) {
    fun newUri(): Uri {
        val file = context.createImageFile()
        return try {
            FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                "com.bankapp.provider", file
            )
        } catch (npe: NullPointerException) {
            Uri.EMPTY //TODO: improve error handling
        }
    }

    private fun Context.createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return File.createTempFile(
            "BANKAPP_$timeStamp",
            ".jpg",
            cacheDir
        )
    }
}
