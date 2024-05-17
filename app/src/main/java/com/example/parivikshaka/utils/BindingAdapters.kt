package com.example.parivikshaka.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:mobileNumber")
    fun setMobileNumber(editText: EditText, value: String?) {
        if (editText.text.toString() != value) {
            editText.setText(value)
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:mobileNumber")
    fun getMobileNumber(editText: EditText): String {
        return editText.text.toString()
    }

    @JvmStatic
    @BindingAdapter("app:mobileNumberAttrChanged")
    fun setMobileNumberChangeListener(editText: EditText, listener: InverseBindingListener?) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                listener?.onChange()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })
    }
}
