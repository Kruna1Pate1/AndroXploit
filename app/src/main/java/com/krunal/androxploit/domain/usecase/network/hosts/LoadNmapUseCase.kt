package com.krunal.androxploit.domain.usecase.network.hosts

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import com.jaredrummler.ktsh.Shell
import com.krunal.androxploit.domain.utils.unzip
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class LoadNmapUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val TAG = "LoadNmapUseCase"
    private lateinit var NMAP_DIR: String
    private lateinit var NMAP_ZIP: String
    private lateinit var NMAP: String

    suspend operator fun invoke(): String {

        NMAP_DIR = context.filesDir.absolutePath + "/nmap"
        NMAP_ZIP = "${NMAP_DIR}/nmap.zip"
        NMAP = "$NMAP_DIR/nmap"

        if (!File(NMAP).exists() || !testNmap()) {
            val dir = File(NMAP_DIR)

            if (!dir.exists()) {
                Log.d(TAG, "invoke: Directory does not exist.")
                dir.mkdirs()
            } else {
                Log.d(TAG, "invoke: Cleaning directory")
                cleanDir()
            }

            downloadNmap()

        } else {
            Log.d(TAG, "invoke: Nmap is already installed and working fine")
        }

        return NMAP
    }

    private fun downloadNmap() {
        val arch = System.getProperty("os.arch")
        Log.d(TAG, "downloadNmap: Architecture $arch")


//        Toast.makeText(context, "Downloading nmap for $arch, Do not close app", Toast.LENGTH_LONG)
//            .show()

        val url: URL = when (arch) {
            "i686" -> {
                URL("https://github.com/kost/nmap-android/releases/download/v7.31/nmap-7.31-binaries-x86.zip")
            }
//            "arm64" -> {
//                URL("https://github.com/kost/nmap-android/releases/download/v7.31/nmap-7.31-binaries-arm64-v8a.zip")
//            }
//            "aarch64" -> {
//                URL("https://github.com/kost/nmap-android/releases/download/v7.31/nmap-7.31-binaries-arm64-v8a.zip")
//            }
            else -> {
                URL("https://github.com/kost/nmap-android/releases/download/v7.31/nmap-7.31-binaries-arm64-v8a.zip")
            }
        }

        try {
            FileOutputStream(File(NMAP_ZIP)).use { fos ->
                Log.d(TAG, "downloadNmap: $url")
                url.openConnection().getInputStream().use { fis ->
                    fis.copyTo(fos)
                }
            }

            extract()

        } catch (e: IOException) {
            Log.d(TAG, "invoke: Can not download nmap")
            Log.d(TAG, "invoke: ${e.message}")
//            Toast.makeText(context, "Error in download ${e.message}", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.d(TAG, "invoke: ${e.message}")
//            Toast.makeText(context, "Error in download ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun extract() {
        Log.d(TAG, "downloadNmap: Extracting nmap")
//        val result = Shell.SH.run("unzip /$NMAP_ZIP -d /$NMAP_DIR")

        val file: File = File(NMAP_ZIP)

        try {
            file.unzip(File(NMAP_DIR))
            File(NMAP_ZIP).delete()
            Log.d(TAG, "extract: Nmap files extracted successfully")
        } catch (e: Exception) {
            Log.d(TAG, "extract: Error ${e.message}")
        }
    }

    private fun cleanDir() {
        val dir = File(NMAP_DIR)
        dir.listFiles()?.forEach { file ->
            file.delete()
        }
    }

    private fun testNmap(): Boolean {
        var flag = false
        val arg: String = "--version"
        val shell: Shell = Shell.SH

        shell.run("chmod +x $NMAP_DIR/*")
        shell.run("$NMAP $arg") {
            onStdOut = { line ->
                Log.d(TAG, "testNmap: $line")
                if (line.startsWith("Nmap version")) {
                    flag = true
                }
            }
        }
        return flag
    }
}