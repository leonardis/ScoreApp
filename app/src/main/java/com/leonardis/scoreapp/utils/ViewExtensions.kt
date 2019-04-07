package com.leonardis.scoreapp.utils

import android.widget.ImageView

fun ImageView.setImageResourceWithSufix(sufix: String) {
    val imageHome = resources.getIdentifier("com.leonardis.scoreapp:drawable/$sufix", null, null)
    this.setImageResource(imageHome)
}