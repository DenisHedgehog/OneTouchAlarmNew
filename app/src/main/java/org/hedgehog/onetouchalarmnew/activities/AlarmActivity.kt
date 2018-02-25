package org.hedgehog.onetouchalarmnew.activities

import android.app.Activity
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import org.hedgehog.onetouchalarmnew.R
import org.hedgehog.onetouchalarmnew.utils.AlarmUtils
import org.hedgehog.onetouchalarmnew.utils.SharedPreferencesUtils
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

/**
 * Created by hedgehog on 15.11.17.
 */

class AlarmActivity : Activity() {

    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            button(R.string.stop_alarm_button) {
                backgroundResource = R.drawable.round_button
                textSize = 24.0f
                onClick {
                    SharedPreferencesUtils.deleteAlarmDateSharedPreferences(this@AlarmActivity)
                    SharedPreferencesUtils.deleteAlarmActivitySharedPreferences(this@AlarmActivity)
                    count = 0
                    finish()
                }
            }.lparams(width = dip(200), height = dip(200)) {
                centerInParent()
                margin = dip(16)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        actionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

        player = MediaPlayer()
        if (Build.VERSION.SDK_INT >= 21) {
            val attribute = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build()
            player.setAudioAttributes(attribute)
        } else {
            player.setAudioStreamType(AudioManager.STREAM_ALARM)
        }

        player.setDataSource(this, Uri.parse("android.resource://$packageName/${R.raw.my_alarm}"))



        player.prepare()
        player.start()
        player.setOnCompletionListener {
            if (player.currentPosition < player.duration) {
                SharedPreferencesUtils.deleteAlarmDateSharedPreferences(this@AlarmActivity)
                SharedPreferencesUtils.deleteAlarmActivitySharedPreferences(this@AlarmActivity)
            } else {
                if (SharedPreferencesUtils.getAlarmState(this@AlarmActivity)) {
                    SharedPreferencesUtils.deleteAlarmDateSharedPreferences(this@AlarmActivity)
                    SharedPreferencesUtils.deleteAlarmActivitySharedPreferences(this@AlarmActivity)
                    SharedPreferencesUtils.setAlarmDateSharedPreferences(this, Date().time + 60*1000)
                    SharedPreferencesUtils.setAlarmActivityPreferences(this, true)
                    count++
                    if (count >= 3) {
                        SharedPreferencesUtils.deleteAlarmDateSharedPreferences(this@AlarmActivity)
                        SharedPreferencesUtils.deleteAlarmActivitySharedPreferences(this@AlarmActivity)
                    }
                }
            }
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        player.stop()
        if (SharedPreferencesUtils.getAlarmDateSharedPreferences(this) != 0L) {
            AlarmUtils.setAlarm(this)
        }
    }


    companion object {
        var count = 0
    }

}