package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationButton.setOnClickListener {

            val notificationChannel: NotificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel("1", "test", NotificationManager.IMPORTANCE_DEFAULT)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            notificationChannel.description = "test"
            notificationChannel.setShowBadge(true) //by default it is true


            val notificationManagerCompat: NotificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManagerCompat.createNotificationChannel(notificationChannel)

            //creating pending intent
            val intent = Intent(this, AfterClickNotification::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            //creating notification builder
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "1")
            //setting notification properties
            builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            builder.setContentIntent(pendingIntent)
            builder.setContentTitle("Title")
            builder.setContentText("Hello This is Notification")
            builder.setChannelId("1")

            //triggering notification
            notificationManagerCompat.notify(0, builder.build())
            Log.d("Clicked", "Clicked")
        }
    }
}