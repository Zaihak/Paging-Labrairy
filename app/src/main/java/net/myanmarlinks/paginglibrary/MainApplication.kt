package net.myanmarlinks.paginglibrary

import android.app.Application
import me.myatminsoe.mdetect.MDetect

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MDetect.init(this)
    }

}