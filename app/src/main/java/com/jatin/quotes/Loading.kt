package com.jatin.quotes

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat

class Loading(private val mActivity: Activity) {
    private lateinit var isDialog: AlertDialog
    @SuppressLint("InflateParams", "ResourceAsColor")
    fun startLoading() {

        val dialogView = mActivity.layoutInflater.inflate(R.layout.loading,null)
        val builder = AlertDialog.Builder(mActivity)
        val progressBar3 = dialogView.findViewById<ProgressBar>(R.id.progressBar3)
        val progressBar2 = dialogView.findViewById<ProgressBar>(R.id.progressBar2)
        val progressBar1 = dialogView.findViewById<ProgressBar>(R.id.progressBar1)
        val progressColor = ContextCompat.getColor(mActivity, R.color.primaryColour)
        progressBar3.indeterminateDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN)
        progressBar2.indeterminateDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN)
        progressBar1.indeterminateDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN)
        dialogView.setBackgroundColor(Color.TRANSPARENT)

        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun dismiss(){
        isDialog.dismiss()
    }
}