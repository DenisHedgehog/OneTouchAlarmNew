package org.hedgehog.onetouchalarmnew.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.hedgehog.onetouchalarmnew.activities.AlarmActivity

/**
 * Created by hedgehog on 15.11.17.
 */

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("receiver", "was started")
        context.startActivity(Intent(context, AlarmActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}