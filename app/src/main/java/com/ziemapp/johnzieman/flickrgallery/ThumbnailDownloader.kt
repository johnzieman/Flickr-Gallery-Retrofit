package com.ziemapp.johnzieman.flickrgallery

import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

private const val TAG = "ThumbnailDownloader"
class ThumbnailDownloader<in T>: HandlerThread(TAG), LifecycleObserver {
    private var hasQuit = false

    override fun quit(): Boolean {
        hasQuit = true
        return super.quit()
    }

    fun queueThumbnail(target: T, url: String){
        Log.d(TAG, "Got a URl: $url")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun setup() {
        start()
        looper
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun tearDown() {
        quit()
    }
}