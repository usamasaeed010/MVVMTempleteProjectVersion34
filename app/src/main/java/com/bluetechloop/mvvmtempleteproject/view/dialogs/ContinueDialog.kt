package com.example.zameedar.view.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bluetechloop.mvvmtempleteproject.R
import com.example.zameedar.view.Interfaces.DialogItemClick
import com.google.android.material.bottomsheet.BottomSheetDialog

class ContinueDialog  (
    var pContext: Context,
    var pMessage:String,
    var pItemClick: DialogItemClick
){
    private var context=pContext
    private var itemClick=pItemClick
//    var btn_continue: TextView
    var dialog= BottomSheetDialog(context)

    init
    {
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setContentView(R.layout.item_continue_bottom_sheet)
//        btn_continue= dialog.findViewById(R.id.okay)!!;
        //message?.setText(pMessage)
//        btn_continue.setOnClickListener(View.OnClickListener {
//            itemClick.onOkClick()
//        })
    }

    fun showDialog()
    {
        Log.e("times","created")
        if(!dialog.isShowing)
            dialog.show();
    }
    fun closeDialog()
    {
        if(dialog.isShowing)
            dialog.dismiss()
    }
}