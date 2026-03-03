package io.mastercoding.serviceapp

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings


class myService : Service() {
    lateinit var mediaPlayer: MediaPlayer


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(
            this,
            Settings.System.DEFAULT_RINGTONE_URI
        )

        mediaPlayer.start()


        //stop hone ke dab recreate kar
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer.stop()
    }

}