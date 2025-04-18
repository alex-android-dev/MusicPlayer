/*
package com.example.musicplayer.presentation.main

import android.media.MediaSession2Service

class AudioPlayerService : Service() {

    private lateinit var player: ExoPlayer
    private lateinit var notificationManager: NotificationManagerCompat
    private val channelId = "audio_player_channel"

    override fun onCreate() {
        super.onCreate()

        // Создаем канал уведомлений (только для Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Audio Player",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Audio Player Notification"
            }
            val manager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        // Инициализируем ExoPlayer
        player = ExoPlayer.Builder(this).build()

        // URL первого трека
        val firstTrackUri = "https://path_to_your_audio_file.mp3"
        val mediaItem = MediaItem.fromUri(firstTrackUri)

        // Устанавливаем трек
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        // Создаем уведомление с контролами
        notificationManager = NotificationManagerCompat.from(this)

        // Настроим уведомление с контролами
        val notification = createPlayerNotification()

        // Запускаем службу как Foreground Service
        startForeground(1, notification)
    }

    private fun createPlayerNotification(): Notification {
        // Создаем контролы для уведомления
        val playPauseAction = NotificationCompat.Action(
            if (player.isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play,
            "Play/Pause",
            getPendingIntentForPlayPause()
        )

        val rewindAction = NotificationCompat.Action(
            android.R.drawable.ic_media_rew,
            "Rewind",
            getPendingIntentForRewind()
        )

        // Создаем уведомление с этими контролами
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Music is Playing")
            .setContentText("Track title or artist")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .addAction(playPauseAction)
            .addAction(rewindAction)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun getPendingIntentForPlayPause(): PendingIntent {
        val playPauseIntent = Intent(this, AudioPlayerService::class.java).apply {
            action = "com.yourapp.PLAY_PAUSE"
        }
        return PendingIntent.getService(this, 0, playPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getPendingIntentForRewind(): PendingIntent {
        val rewindIntent = Intent(this, AudioPlayerService::class.java).apply {
            action = "com.yourapp.REWIND"
        }
        return PendingIntent.getService(this, 1, rewindIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Обработать действия для управления плеером
        when (intent?.action) {
            "com.yourapp.PLAY_PAUSE" -> togglePlayPause()
            "com.yourapp.REWIND" -> rewindTrack()
        }
        return START_NOT_STICKY
    }

    private fun togglePlayPause() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
        // Обновить уведомление после изменения состояния
        notificationManager.notify(1, createPlayerNotification())
    }

    private fun rewindTrack() {
        // Перематываем трек на начало
        player.seekTo(0)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}

*/