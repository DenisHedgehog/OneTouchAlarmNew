package org.hedgehog.onetouchalarmnew.ui

import org.hedgehog.onetouchalarmnew.R
import org.hedgehog.onetouchalarmnew.activities.AlarmActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hedgehog on 15.11.17.
 */

class AlarmActivityUI : AnkoComponent<AlarmActivity> {

    override fun createView(ui: AnkoContext<AlarmActivity>) = ui.apply {

        relativeLayout {
            button(R.string.stop_alarm_button) {
                backgroundResource = R.drawable.round_button
                textSize = 24.0f
                onClick {

                }
            }.lparams(width = dip(200), height = dip(200)) {
                centerInParent()
                margin = dip(16)
            }
        }
    }.view

}