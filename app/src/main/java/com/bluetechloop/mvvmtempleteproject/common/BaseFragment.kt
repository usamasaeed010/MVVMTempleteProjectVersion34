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
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bluetechloop.mvvmtempleteproject.R
import com.bluetechloop.mvvmtempleteproject.utlis.ArtiPreferenceManager
import com.bluetechloop.mvvmtempleteproject.utlis.CheckNetwork
import com.bluetechloop.mvvmtempleteproject.utlis.NetworkConnected

open class BaseFragment : Fragment(), View.OnClickListener, NetworkConnected {

    private lateinit var dialog : Dialog
    private var broadcastReceiver: BroadcastReceiver?=null
    private lateinit var noInternetDialog : Dialog
    var isNetworkConnected=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** init dialog  ***
        dialog = Dialog(requireContext())
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.progress_dialog)
        installInternetListener()
    }

    fun showToast(msg : String?){
        if(isAdded)
            requireActivity().runOnUiThread(Runnable {

            Toast.makeText(requireContext(), msg?:"", Toast.LENGTH_LONG).show()
//            Toasty.info(requireContext(), msg?:"", Toast.LENGTH_LONG).show()
        })
    }

    public fun showProgress(){
        if (!dialog.isShowing){
            dialog.show()
        }
    }

    public fun closeProgress(){
        if (dialog.isShowing){
            dialog.dismiss()
        }
    }
    fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)

    }

    fun <T> Context.openActivityWithClearTask(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun <T> Context.openActivityAndClearApp(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)

    }
    fun isTokenEmpty(): Boolean {
        return ArtiPreferenceManager.getToken().equals("") && ArtiPreferenceManager.getToken()!!
            .isEmpty()
    }
    override fun onClick(v: View?) {

    }

    private fun installInternetListener() {
        if (broadcastReceiver == null) {
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {


                    if (CheckNetwork().isNetworkAvailable(context)) {
                        isNetworkConnected=true
                        onNetworkConnect(true);
                    }else{

                        isNetworkConnected=false
                        onNetworkConnect(false)
                    }

                }
            }
            val intentFilter = IntentFilter()
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            requireContext().registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    override fun onNetworkConnect(isNetworkConnected: Boolean) {

    }

}