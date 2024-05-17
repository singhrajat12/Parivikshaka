package com.example.parivikshaka.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

fun Context.showToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun configOtpEditText(vararg etList: EditText) {
    val afterTextChanged = { index: Int, e: Editable? ->
        val view = etList[index]
        val text = e.toString()

        when (view.id) {
            // first text changed
            etList[0].id -> {
                if (text.isNotEmpty()) etList[index + 1].requestFocus()
            }

            // las text changed
            etList[etList.size - 1].id -> {
                if (text.isEmpty()) etList[index - 1].requestFocus()
                else etList[index].clearFocus()
            }

            // middle text changes
            else -> {
                if (text.isNotEmpty()) etList[index + 1].requestFocus()
                else etList[index - 1].requestFocus()
            }
        }
        false
    }
    etList.forEachIndexed { index, editText ->
        editText.doAfterTextChanged { afterTextChanged(index, it) }
    }
}

//Loader

private var customDialog: AlertDialog? = null

fun Context.showProgress() {
    hideProgress()
    val customAlertBuilder = AlertDialog.Builder(this)
//    val customAlertView =
//        ProgressLayoutBinding.inflate(LayoutInflater.from(this), null, false)
//    customAlertBuilder.setView(customAlertView.root)
    customAlertBuilder.setCancelable(false)
    customDialog = customAlertBuilder.create()
    customDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    customDialog!!.show()
}

fun hideProgress() {
    if (customDialog != null && customDialog?.isShowing!!) {
        customDialog?.dismiss()
    }
}
