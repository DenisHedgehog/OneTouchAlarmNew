package org.hedgehog.onetouchalarmnew.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import org.hedgehog.onetouchalarmnew.R
import org.hedgehog.onetouchalarmnew.ui.MainActivityUI
import org.hedgehog.onetouchalarmnew.utils.SharedPreferencesUtils
import org.jetbrains.anko.setContentView

/**
 * Created by hedgehog on 06.11.17.
 */

class MainActivity : Activity() {

    private var mainActivityUI: MainActivityUI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityUI = MainActivityUI()
        mainActivityUI?.setContentView(this)
    }

    override fun onResume() {
        super.onResume()
        if (SharedPreferencesUtils.getAlarmState(this)) {
            mainActivityUI?.button?.text = getString(R.string.cancel_alarm_button)

        } else {
            mainActivityUI?.button?.text = getString(R.string.set_time_button)
        }
        Log.i("MainActitity onResume", "AlarmActivity count = ${AlarmActivity.count}, text = ${mainActivityUI?.button?.text}")
        Log.i("kek", "android.resource://$packageName/${R.raw.my_alarm}")
    }

}