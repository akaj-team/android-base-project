package com.android.appname.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

internal fun EditText.onTextChangeListener(
    beforeTextChanged: (CharSequence?) -> Unit = {},
    onTextChanged: (CharSequence?) -> Unit = {},
    afterTextChanged: (Editable?) -> Unit = {}
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeTextChanged.invoke(p0)
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0)
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0)
        }
    })
}
