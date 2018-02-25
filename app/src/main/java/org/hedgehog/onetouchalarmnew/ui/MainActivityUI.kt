package org.hedgehog.onetouchalarmnew.ui

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.widget.Button
import android.widget.TimePicker
import org.hedgehog.onetouchalarmnew.activities.MainActivity
import org.hedgehog.onetouchalarmnew.R
import org.hedgehog.onetouchalarmnew.utils.AlarmUtils
import org.hedgehog.onetouchalarmnew.utils.SharedPreferencesUtils
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hedgehog on 06.11.17.
 */

class MainActivityUI : AnkoComponent<MainActivity> {

    private lateinit var tp: TimePicker
    lateinit var button: Button

    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {

        relativeLayout {
            button = button(R.string.set_time_button) {
                backgroundResource = R.drawable.round_button
                textSize = 24.0f
                onClick {
                    when (button.text) {
                        getContext().getString(R.string.set_time_button) -> {
                            alert(R.string.set_alarm_time) {
                                customView {
                                    tp = timePicker()
                                }

                                positiveButton(R.string.set_alarm_button) {
                                    SharedPreferencesUtils.setAlarmDateSharedPreferences(getContext(),
                                            AlarmUtils.getAlarmDate(tp.currentHour, tp.currentMinute).time)
                                    AlarmUtils.setAlarm(getContext())
                                    if (AlarmUtils.isNullVolume(getContext())) {
                                        longSnackbar(view, "Kappa", "Click for fix!") {
                                            val audioManager = getContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
                                            audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                                                    (audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM) * 0.7).toInt(),
                                                    0)
                                            toast(getContext().getString(R.string.fixed_alarm_volume_message))
                                        }
                                    }
                                    button.text = getContext().getString(R.string.cancel_alarm_button)
                                }

                                noButton {

                                }
                            }.show()
                        }
                        getContext().getString(R.string.cancel_alarm_button) -> {
                            AlarmUtils.cancelAlarm(getContext())
                            button.text = getContext().getString(R.string.set_time_button)
                        }
                    }
                }
            }.lparams(width = dip(200), height = dip(200)) {
                centerInParent()
                margin = dip(16)
            }
        }

    }.view

}