package com.example.deleteroq.util

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributeView

class Deleter {
    companion object {
        fun run(path: String, day: Int) {
            val dir = File(path)
            if (dir.exists() && dir.isDirectory) {
                val currentTime = System.currentTimeMillis()/1000
                val n = 60*60*24*day

                dir.listFiles()?.forEach {
                    if (!it.isDirectory) {
                        val attr = Files.getFileAttributeView(Paths.get(it.path), BasicFileAttributeView::class.java).readAttributes()

                        val createTime = attr.creationTime().toMillis()/1000

                        if (currentTime - createTime > n) {
                            it.delete()
                        }
                    }
                }
            }
        }
    }
}