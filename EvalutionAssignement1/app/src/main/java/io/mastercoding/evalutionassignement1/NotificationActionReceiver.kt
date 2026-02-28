package io.mastercoding.evalutionassignement1

import android.Manifest
import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {
    private fun safeNotify(ctx: android.content.Context, id: Int, notification: androidx.core.app.NotificationCompat.Builder) {
        val nm = androidx.core.app.NotificationManagerCompat.from(ctx)
        val hasPermission =
            android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU ||
                    androidx.core.content.ContextCompat.checkSelfPermission(
                        ctx,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            try {
                nm.notify(id, notification.build())
            } catch (se: SecurityException) {
                android.util.Log.w("Notify", "POST_NOTIFICATIONS denied at runtime", se)
            }
        } else {
            android.util.Log.w("Notify", "Skipping notify() in receiver: permission not granted")
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        val note = intent.getStringExtra(MainActivity.EXTRA_NOTE).orEmpty()
        val category = intent.getStringExtra(MainActivity.EXTRA_CATEGORY).orEmpty()

        when (action) {
            MainActivity.ACTION_ALLOW -> {
                // Dismiss the decision notification
                NotificationManagerCompat.from(context).cancel(MainActivity.NOTIF_ID_DECISION)

                // Show success notification
                val success = NotificationCompat.Builder(context, MainActivity.NOTE_CHANNEL_ID)
                    .setSmallIcon(R.drawable.checkbox_on_background)
                    .setContentTitle("Task added successfully")
                    .setContentText("$category: $note")
                    .setStyle(NotificationCompat.BigTextStyle().bigText("$category: $note"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build()

                safeNotify(context, MainActivity.NOTIF_ID_SUCCESS,
                    androidx.core.app.NotificationCompat.Builder(context, MainActivity.NOTE_CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.checkbox_on_background)
                        .setContentTitle("Task added successfully")
                        .setContentText("$category: $note")
                        .setStyle(androidx.core.app.NotificationCompat.BigTextStyle().bigText("$category: $note"))
                        .setPriority(androidx.core.app.NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                )
            }

            MainActivity.ACTION_DENY -> {
                // Just dismiss the decision notification
                NotificationManagerCompat.from(context)
                    .cancel(MainActivity.NOTIF_ID_DECISION)
            }
        }
    }
}
