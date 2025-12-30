package com.example.myapplication.services
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PushNotificationService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.IO).launch {
            val repo = FcmRepositoryProvider.provideRepository(applicationContext)
            repo.registerToken(token)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Extract message from payload if available
        var title = message.notification?.title ?: "New notification"
        var body = message.notification?.body ?: "You have been invited to a new group"

        Log.d("FCM", "onMessageReceived: ${message.data} ${message.notification}")

        // Check if there's custom data in the data payload
        val dataTitle = message.data["title"] // Example custom data field
        val dataBody = message.data["body"] // Example custom data field

        // If custom data exists, override notification content
        if (!dataTitle.isNullOrEmpty()) {
            title = dataTitle
        }
        if (!dataBody.isNullOrEmpty()) {
            body = dataBody
        }

        showNotification(title, body)
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "default_channel"

        // Create the channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        // Build the notification
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.outline_adb_24)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.logo))
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .build()

        // Show it
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }

}