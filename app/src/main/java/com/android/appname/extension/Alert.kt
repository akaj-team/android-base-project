package com.android.appname.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.appname.R
import kotlinx.android.synthetic.main.alert_dialog.view.*

inline fun Activity.alert(
    title: CharSequence? = null,
    message: CharSequence? = null,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Activity.alert(
    titleResource: Int = 0,
    messageResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this, title, message).apply {
        func()
    }.create()
}

inline fun Fragment.alert(
    title: CharSequence? = null,
    message: CharSequence? = null,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this.requireContext(), title, message).apply {
        func()
    }.create()
}

inline fun Fragment.alert(
    titleResource: Int = 0,
    messageResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val title = if (titleResource == 0) null else getString(titleResource)
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this.requireContext(), title, message).apply {
        func()
    }.create()
}

@SuppressLint("InflateParams")
class AlertDialogHelper(
    context: Context,
    private var title: CharSequence?,
    private var message: CharSequence?
) {

    private val dialogView: View by lazyFast {
        LayoutInflater.from(context).inflate(R.layout.alert_dialog, null).apply {
            tvAlertTitle.text = title
            tvAlertMessage.text = message
        }
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(dialogView)

    private var dialog: AlertDialog? = null

    var cancelable: Boolean = true

    fun positiveButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        with(dialogView.btnAlertPositive) {
            text = builder.context.getString(textResource)
            setClickListenerToDialogButton(func)
        }
    }

    fun positiveButton(text: CharSequence, func: (() -> Unit)? = null) {
        with(dialogView.btnAlertPositive) {
            this.text = text
            setClickListenerToDialogButton(func)
        }
    }

    fun negativeButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        with(dialogView.btnAlertNegative) {
            text = builder.context.getString(textResource)
            setClickListenerToDialogButton(func)
        }
    }

    fun negativeButton(text: CharSequence, func: (() -> Unit)? = null) {
        with(dialogView.btnAlertNegative) {
            this.text = text
            setClickListenerToDialogButton(func)
        }
    }

    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog {
        dialogView.tvAlertTitle.goneIfTextEmpty()
        dialogView.tvAlertMessage.goneIfTextEmpty()
        dialogView.btnAlertPositive.goneIfTextEmpty()
        dialogView.btnAlertNegative.goneIfTextEmpty()

        dialog = builder
            .setCancelable(cancelable)
            .create()
        return dialog!!
    }

    private fun TextView.goneIfTextEmpty() {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}

/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}