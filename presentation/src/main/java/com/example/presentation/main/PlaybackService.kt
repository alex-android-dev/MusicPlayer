package com.example.presentation.main

/*
class PlaybackService : Service() {

    private val notificationManager by lazy { getSystemService(NOTIFICATION_SERVICE) as NotificationManager }
    private val builder by lazy { createNotificationBuilder() }

    override fun onBind(p0: Intent?): IBinder {
        log("onBind")
        return LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        AppMusicPlayer.initPLayer(this)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, builder.build())
    }


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        log("onStartCommand")
        AppMusicPlayer.playTestTrack()
        return START_STICKY
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotificationBuilder(): NotificationCompat.Builder {
        val isPlayingIcon =
            if (AppMusicPlayer.getPlayer().isPlaying) R.drawable.ic_media_pause // todo установить другие иконки
            else R.drawable.ic_media_play

        val playPauseAction = NotificationCompat.Action(
            isPlayingIcon,
            "play / pause",
            getPendingIntentForPlayPause()
        )


        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("Listening...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(playPauseAction)
            .setOnlyAlertOnce(false)
    }

    private fun getPendingIntentForPlayPause(): PendingIntent {
        val intent = Intent(this, PlaybackService::class.java).apply {
            Intent.setAction = "com.yourapp.REWIND"
        }
        return PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun log(str: String) {
        Log.d("PlaybackService", str)
    }

    inner class LocalBinder : Binder() {
        fun getService() = this@PlaybackService
    }

    companion object {
        private const val CHANNEL_ID = "music_player_channel"
        private const val CHANNEL_NAME = "music_player"
        private const val NOTIFICATION_ID = 101
    }

}

 */