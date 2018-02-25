package org.hedgehog.onetouchalarmnew.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.hedgehog.onetouchalarmnew.utils.AlarmUtils

/**
 * Created by hedgehog on 15.11.17.
 */

class BootDeviceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("REBOOT RECEIVER", "IT RECEIVED")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            Log.i("REBOOT RECEIVER", "BOOT COMPLETED")
            AlarmUtils.setAlarm(context)
        }
    }

}