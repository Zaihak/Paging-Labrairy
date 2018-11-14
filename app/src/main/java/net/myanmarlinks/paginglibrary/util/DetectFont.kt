package net.myanmarlinks.paginglibrary.util

import me.myatminsoe.mdetect.MDetect

object DetectFont {
    fun isUnicode(): Boolean {
        return MDetect.isUnicode()
    }
}