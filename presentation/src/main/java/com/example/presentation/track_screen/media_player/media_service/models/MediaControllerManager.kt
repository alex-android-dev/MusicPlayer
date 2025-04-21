package com.example.presentation.track_screen.media_player.media_service.models

import android.content.ComponentName
import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.presentation.track_screen.media_player.MusicService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors

@Stable
internal class MediaControllerManager private constructor(context: Context) : RememberObserver {
    private val appContext = context.applicationContext
    private var factory: ListenableFuture<MediaController>? = null
    var controller = mutableStateOf<MediaController?>(null)
        private set

    init {
        initialize()
    }

    /** Создание контроллера **/
    @OptIn(UnstableApi::class)
    fun initialize() {
        if (factory == null || factory?.isDone == true) {
            factory = MediaController.Builder(
                appContext,
                SessionToken(appContext, ComponentName(appContext, MusicService::class.java))
            ).buildAsync()
        }
        factory?.addListener(
            {
                // MediaController is available here with controllerFuture.get()
                controller.value = factory?.let {
                    if (it.isDone)
                        it.get()
                    else
                        null
                }
            },
            MoreExecutors.directExecutor()
        )
    }

    fun release() {
        factory?.let {
            MediaController.releaseFuture(it)
            controller.value = null
        }
        factory = null
    }

    // Lifecycle methods for the RememberObserver interface.
    override fun onAbandoned() {
        release()
    }

    override fun onForgotten() {
        release()
    }

    override fun onRemembered() {}

    companion object {
        @Volatile
        private var instance: MediaControllerManager? = null

        fun getInstance(context: Context): MediaControllerManager {
            return instance ?: synchronized(this) {
                instance ?: MediaControllerManager(context).also { instance = it }
            }
        }
    }
}