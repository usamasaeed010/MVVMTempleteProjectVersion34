package com.move.zoom.ui.common

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.bluetechloop.mvvmtempleteproject.R
import com.bluetechloop.mvvmtempleteproject.utlis.ArtiPreferenceManager
import com.bluetechloop.mvvmtempleteproject.utlis.CheckNetwork
import com.bluetechloop.mvvmtempleteproject.utlis.NetworkConnected
import java.util.*
import kotlin.concurrent.timerTask

open class BaseActivity : LocalizationActivity(), View.OnClickListener, NetworkConnected {

    private var broadcastReceiver: BroadcastReceiver? = null
    private lateinit var dialog: Dialog
    private lateinit var noInternetDialog: Dialog
    var isNetworkConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** init dialog  ***
        dialog = Dialog(this)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.progress_dialog)

        noInternetDialog = Dialog(this)
        noInternetDialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        noInternetDialog.setCancelable(true)
        noInternetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        noInternetDialog.setContentView(R.layout.no_internet_dialog)

        installInternetListener()

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (currentFocus != null) {
            var imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0);
        }

        return super.dispatchTouchEvent(ev)
    }

    fun showToast(msg: String?) {
        runOnUiThread(Runnable {
            Toast.makeText(baseContext, msg ?: "", Toast.LENGTH_LONG).show();
        })
    }
    fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun <T> Context.openActivityWithClearTask(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    fun <T> Context.openActivityAndClearApp(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    public fun showProgress() {
        if (!dialog.isShowing && !isFinishing) {
            dialog.show()
        }
        Timer().schedule(timerTask
        {
            if (!isFinishing && dialog.isShowing)
                dialog.dismiss()
        }, 60 * 1000
        )
    }

    fun closeProgress() {
        if (!isFinishing && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun showNoInternetDialog() {
        if (!noInternetDialog.isShowing && !isFinishing) {
            try {
                noInternetDialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isTokenEmpty(): Boolean {
        return ArtiPreferenceManager.getToken().equals("") && ArtiPreferenceManager.getToken()!!.isEmpty()
    }
    fun closeNoInternetDialog() {
        if (noInternetDialog.isShowing) {
            noInternetDialog.dismiss()
        }
    }

    fun isEmpty(textView: TextView): Boolean {
        return textView.text.toString().trim().isEmpty()
    }

    fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString().trim().isEmpty()
    }

    override fun onClick(v: View?) {

    }

    private fun installInternetListener() {
        if (broadcastReceiver == null) {
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
//                    val extras = intent.extras
//                    val info: NetworkInfo? = extras
//                        ?.getParcelable<Parcelable>("networkInfo") as NetworkInfo?
//                    val state: NetworkInfo.State = info!!.getState()
//                    Log.d(
//                        "InternalBroadcastReceiver", info.toString().toString() + " "
//                                + state.toString()
//                    )
//                    if (state === NetworkInfo.State.CONNECTED) {
//                        isNetworkConnected=true
//                        onNetworkConnect(true);
//                    } else {
//                        isNetworkConnected=false
//                        onNetworkConnect(false)
//                    }


                    if (CheckNetwork().isNetworkAvailable(context)) {
                        isNetworkConnected = true
                        onNetworkConnect(true);
                    } else {

                        isNetworkConnected = false
                        onNetworkConnect(false)
                    }
                }
            }
            val intentFilter = IntentFilter()
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    override fun onNetworkConnect(isNetworkConnected: Boolean) {
        this.isNetworkConnected = isNetworkConnected
    }
}