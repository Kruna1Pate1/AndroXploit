package com.krunal.androxploit.domain.utils

import java.io.*
import java.util.zip.ZipFile

fun File.unzip(to: File? = null) {
    val destinationDir = to ?: File(parentFile, nameWithoutExtension)
    destinationDir.mkdirs()

    ZipFile(this).use { zipFile ->
        zipFile
            .entries()
            .asSequence()
            .filter { !it.isDirectory }
            .forEach { zipEntry ->
                val currFile = File(destinationDir, zipEntry.name)
                currFile.parentFile?.mkdirs()
                zipFile.getInputStream(zipEntry).use { input ->
                    currFile.outputStream().use { output -> input.copyTo(output) }
                }
            }
    }
}