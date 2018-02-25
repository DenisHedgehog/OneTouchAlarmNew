package org.hedgehog.onetouchalarmnew.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.hedgehog.onetouchalarmnew.receivers.AlarmReceiver
import java.util.*

/**
 * Created by hedgehog on 14.11.17.
 */

class AlarmUtils {

    companion object {

        fun setAlarm(context: Context) {
            val pendingIntent = PendingIntent.getBroadcast(context, 0,
                    Intent(context, AlarmReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= 23) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                        SharedPreferencesUtils.getAlarmDateSharedPreferences(context),
                        pendingIntent)
            } else {
                if (Build.VERSION.SDK_INT >= 19) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                            SharedPreferencesUtils.getAlarmDateSharedPreferences(context),
                            pendingIntent)
                } else {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            SharedPreferencesUtils.getAlarmDateSharedPreferences(context),
                            pendingIntent)
                }
            }
            SharedPreferencesUtils.setAlarmActivityPreferences(context, true)
        }

        fun cancelAlarm(context: Context) {
            val pendingIntent = PendingIntent.getBroadcast(context, 0,
                    Intent(context, AlarmReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }

        fun isNullVolume(context: Context): Boolean {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            return (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0)
        }

        fun getAlarmDate(hour: Int, minute: Int): Date {
            var year = Calendar.getInstance().get(Calendar.YEAR) - 1900
            var month = Calendar.getInstance().get(Calendar.MONTH)
            var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

            if (hour < Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ||
                    ((hour == Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) &&
                            (minute < Calendar.getInstance().get(Calendar.MINUTE)))) {
                Log.i("CHECK", "year end")
                if (day == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    if (month == Calendar.getInstance().getActualMaximum(Calendar.MONTH)) {
                        year++
                        month = 0
                        day = 1
                        Log.i("Month end", "year end")
                    } else {
                        month++
                        day = 1
                        Log.i("Month end", "year is not end")
                    }
                } else {
                    day++
                }
            }

            return Date(year, month, day, hour, minute)
        }

    }

    fun showWarningShackbar() {
        //TODO: Snackbar for zero volume level with ability to change it
    }

}