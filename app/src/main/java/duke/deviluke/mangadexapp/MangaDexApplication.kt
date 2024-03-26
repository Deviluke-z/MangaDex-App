package duke.deviluke.mangadexapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MangaDexApplication : Application() {

    companion object {
        val DEBUG_TAG = "MangaDexApplication"
    }
}