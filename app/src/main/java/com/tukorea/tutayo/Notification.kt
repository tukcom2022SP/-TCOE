package com.tukorea.tutayo

import android.app.Notification.VISIBILITY_PRIVATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE

class Notification(base: Context?) : ContextWrapper(base) {
    //채널 변수 만들기
    private val channelID: String = "channelID"
    private val channelName : String = "channelName"

    init{
        createChannel()
    }

    private fun createChannel(){
        //객체 생성하기
        val channel : NotificationChannel =
            NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.BLUE

        //잠금화면에서도 팝업이 보이게 하는 기능인것 같은데,,, import를 해도 오류가 나서 일단 주석처리
        //channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        //생성
        getManager().createNotificationChannel(channel)
    }

    //NotificationManager 생성

    fun getManager(): NotificationManager {
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getChannelNotification(title: String, message: String): NotificationCompat.Builder{
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background) //아이콘 설정, 나중에 임의로 변경 가능
    }
}