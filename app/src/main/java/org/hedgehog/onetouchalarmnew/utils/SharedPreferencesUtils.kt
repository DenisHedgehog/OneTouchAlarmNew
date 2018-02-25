package org.hedgehog.onetouchalarmnew.utils

import android.content.Context
import android.support.v7.app.AppCompatActivity
import org.hedgehog.onetouchalarmnew.R
import java.util.*

/**
 * Created by hedgehog on 14.11.17.
 */

class SharedPreferencesUtils {

    companion object {

        //Alarm time preferences

        fun setAlarmDateSharedPreferences(context: Context, time: Long) {
            val sPref = context.getSharedPreferences(context.getString(R.string.my_shared_preferences), AppCompatActivity.MODE_PRIVATE)
            val sPrefEditor = sPref.edit()
            sPrefEditor.putLong(context.getString(R.string.shared_preferences_alarm_time), time)
            sPrefEditor.apply()
        }

        fun getAlarmDateSharedPreferences(context: Context): Long {
            return context.getSharedPreferences(context.getString(R.string.my_shared_preferences), AppCompatActivity.MODE_PRIVATE)
                    .getLong(context.getString(R.string.shared_preferences_alarm_time), 0L)
        }

        fun deleteAlarmDateSharedPreferences(context: Context) {
            context.getSharedPreferences(context.getString(R.string.my_shared_preferences), AppCompatActivity.MODE_PRIVATE)
                    .edit().remove(context.getString(R.string.shared_preferences_alarm_time)).apply()
        }

        //Alarm state preferences

        fun getAlarmState(context: Context): Boolean {
            return context.getSharedPreferences(context.getString(R.string.my_shared_preferences), Context.MODE_PRIVATE)
                    .getBoolean(context.getString(R.string.shared_preferences_alarm_active), false)
        }

        fun setAlarmActivityPreferences(context: Context, boolean: Boolean) {
            val sPref = context.getSharedPreferences(context.getString(R.string.my_shared_preferences), AppCompatActivity.MODE_PRIVATE)
            val sPrefEditor = sPref.edit()
            sPrefEditor.putBoolean(context.getString(R.string.shared_preferences_alarm_active), boolean)
            sPrefEditor.apply()
        }

        fun deleteAlarmActivitySharedPreferences(context: Context) {
            context.getSharedPreferences(context.getString(R.string.my_shared_preferences), AppCompatActivity.MODE_PRIVATE)
                    .edit().remove(context.getString(R.string.shared_preferences_alarm_active)).apply()
        }

    }

}

